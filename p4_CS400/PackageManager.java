///////////////////////////////////////////////////////////////////////////////
//Title: PackageManager
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class provides the implementation of the package manager that can parse JSON
//files and uses Graphs.
//
////////////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    Siddharth Aneja
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
    /**
     * Graph with edges from Dependencies to Vertices.
     */
    private Graph graphDependencyToVertex;
    
    /**
     * Graph with edges from Vertices to Dependencies.
     */
    private Graph graphVertexToDependency;
    
    /**
     * List of all vertices in the graph.
     */
    private ArrayList<String> listOfVertices;
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
      graphDependencyToVertex = new Graph();
      graphVertexToDependency = new Graph();
      listOfVertices = new ArrayList<>();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
        try {
          Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
          JSONObject jo = (JSONObject) obj;
          JSONArray packages = (JSONArray) jo.get("packages");
          
          for(int i = 0; i < packages.size(); i++) {
            JSONObject pack = (JSONObject) packages.get(i);
            JSONArray dependencies = (JSONArray) pack.get("dependencies");
            if(dependencies.size() == 0) {
              graphDependencyToVertex.addVertex((String) pack.get("name"));
              graphVertexToDependency.addVertex((String) pack.get("name"));
              listOfVertices.add((String) pack.get("name"));
            }else {
              for(int j = 0; j < dependencies.size(); j++) {
                graphDependencyToVertex.addEdge((String) dependencies.get(j), (String) pack.get("name"));
                graphVertexToDependency.addEdge((String) pack.get("name"), (String) dependencies.get(j));
                listOfVertices.add((String) pack.get("name"));
                listOfVertices.add((String) dependencies.get(j));
              }
            }
          }
        }catch(FileNotFoundException e) {
          throw new FileNotFoundException();
        }catch(IOException e) {
          throw new IOException();
        }catch(ParseException e) {
          throw new ParseException(0);
        }
    }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        return graphDependencyToVertex.getAllVertices();
    }
    
    private Set<String> visited;
    
    /**
     * The List that stores the installation order.
     */
    private List<String> verticesPath;
    
    /**
     * Stored vertices on the current path.
     */
    private Set<String> currPath;
    
    /**
     * Stores the index of the vertex with no dependencies.
     */
    private int index = -1;
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
      //Checks if graph null or does not contain the vertex pkg, then throws exception
      Set<String> vertices = graphDependencyToVertex.getAllVertices();
      if(graphDependencyToVertex == null || !vertices.contains(pkg)) {
        throw new PackageNotFoundException();
      }
      //Initialize all the variables we need
      this.visited = new HashSet<String>();
      this.verticesPath = new ArrayList<String>();
      this.currPath = new HashSet<String>();
      index = -1;
      //calls the recursive helper on pkg
      getInstallOrderHelper(pkg);
      currPath.remove(pkg); //Empty current path
      //getInstallOrderHelper - gets a list from vertex to dependency, but to get a list with dependencies
      //being before pkg, we will reverse the order
      Collections.reverse(this.verticesPath);
      return (List<String>)this.verticesPath;
    }
    
    /**
     * Recursive helper method for getting the installation order of a vertex.
     * 
     * @param pkg - the vertex whose installation order we need
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     */
    private void getInstallOrderHelper(String pkg) throws CycleException{
      //Checks if pkg has already been visited and is in the current path, if so throws CycleException
      if(visited.contains(pkg) && currPath.contains(pkg)) {
        throw new CycleException();
      }
      //Add pkg to the visited Set and Current path
      this.visited.add(pkg);
      this.currPath.add(pkg);
      //checks if the Installation Order List already contains pkg, if it does not it adds it.
      if(!verticesPath.contains(pkg)) {
        //if no vertices without any dependencies are added yet (index = -1) then add pkg to end
        //else add it to the index
        if(this.index == -1) {
          this.verticesPath.add(pkg);
        }
        else {
          this.verticesPath.add(index, pkg);
          index++;
        }
      }
      //Get the Dependencies of pkg
      List<String> neighbors = graphVertexToDependency.getAdjacentVerticesOf(pkg);
      //Iterate through each dependency of pkg
      for(int i = 0; i < neighbors.size(); i++) {
        String vertex = (String)neighbors.get(i);
        //if the dependency does not have any dependencies of its own enter branch
        if(graphVertexToDependency.getAdjacentVerticesOf(vertex).size() == 0) {
          //add it to visited
          this.visited.add(vertex);
          //Check if Installed Order List already contains it, if not, now add it
          if(!verticesPath.contains(vertex)) {
            //Again check if this will be the first vertex without any dependencies
            //or if there is already an index
            if(this.index == -1) {
              this.verticesPath.add(vertex);
              index = verticesPath.indexOf(vertex);
            }
            else {
              this.verticesPath.add(index, vertex);
            }
          } 
        }
        //if the dependency of pkg, has more dependencies of it own, make a recursive call on the 
        //dependency of pkg
        else {
          getInstallOrderHelper(vertex);
        }
        //after returning from recursive call and switching paths from pkg, remove dependency from current path
        this.currPath.remove(vertex);
      }
    }
    
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
      //Gets the installation order of the package already installed
      List<String> pathOfInstalledPkg = this.getInstallationOrder(installedPkg);
      //Get the installation order of the package to be installed
        List<String> listToInstall = new ArrayList<String>();
        listToInstall = this.getInstallationOrder(newPkg);
        //Then remove every value in the installation order of the newPkg, that already exists in 
        //the installation order of installedPkg
        for(int i = 0; i < pathOfInstalledPkg.size(); i++) {
          if(listToInstall.contains(pathOfInstalledPkg.get(i)) && !pathOfInstalledPkg.get(i).equals(newPkg)) {
            listToInstall.remove(pathOfInstalledPkg.get(i));
          }
        }
        return (List<String>) listToInstall;
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
      //Create a list 
      List<String> order = null;
      try {
        //Get the topological order of the graph using the helper method
        order = this.topologicalOrder();
        //return the list
        return (List<String>) order;
      } catch(CycleException e) {
        throw new CycleException();
      }catch(PackageNotFoundException e) {
      }
      return (List<String>) order;
    }
    
    /**
     * Method gets the topological order of the graph.
     * Used within the getInstallationOrderForAllPackages() method.
     * @return List<String> of graph in topological order
     * @throws CycleException if you encounter a cycle in the graph 
     * @throws PackageNotFoundException - if the pkg is not in the graph
     */
    private List<String> topologicalOrder() throws CycleException, PackageNotFoundException{
      //The list that will store the order
      List<String> orderedList = new ArrayList<>();
      //Set that stores all the visited vertices
      Set<String> visited = new HashSet<String>();
      //Stack used in the creation of topological order
      Stack<String> stack = new Stack<String>();
      String root = null;
      //List storing all the vertices in this graph
      ArrayList<String> temp0 = new ArrayList<>(this.getAllPackages());
      //for loop that adds all the vertices with no dependencies to the stack and marks them as visited
      for(int i = 0; i < temp0.size(); i++) {
        //package with no dependencies will only have 1 element in their installation order (themselves)
        if(this.getInstallationOrder(temp0.get(i)).size() == 1){ 
          root = temp0.get(i);
          stack.push(root);
          visited.add(root);
        }
      }
      while(!stack.isEmpty()) {
        String temp = (String) stack.peek();
        //Get successors of the vertex on top of the stack
        List<String> successors = graphDependencyToVertex.getAdjacentVerticesOf(temp);
        //set boolean value of all visited to true
        boolean allVisited = true;
        //Iterate through all successors of the top element, set allVisited to false, and if
        //the successor has not yet been visited -> mark it as visited and push it to the stack
        //break out of loop
        for(int i = 0; i < successors.size(); i++) {
           if(!visited.contains(successors.get(i))) {
             allVisited = false;
             visited.add(successors.get(i));
             stack.push(successors.get(i));
             break;
           }
        }
        //if all the successors of the top most element of the stack have been visited, pop it off
        if(allVisited == true) {
          orderedList.add(stack.pop());
        }
      }
      //At the end, reverse the list to get desired order and return
      Collections.reverse(orderedList);
      return (List<String>)orderedList;
    }
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
      //Set the first vertex as the one with max dependencies
      String highest = listOfVertices.get(0);
      try {
        //stores the number of dependencies of highest
        int value = this.getInstallationOrder(highest).size();
        //For each element in the graph, check if it has more dependencies than highest
        //if it does, replace highest with that vertex
        for(int i = 1; i < listOfVertices.size(); i++) {
          int temp = this.getInstallationOrder(listOfVertices.get(i)).size();
          if(temp > value) {
            value = temp;
            highest = listOfVertices.get(i);
          }
        }
      }catch(CycleException e) {
        throw new CycleException();
      }catch(PackageNotFoundException e) {
        e.printStackTrace();
      }
      //return highest -> vertex with max dependencies
      return highest;
    }

    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
    }
    
}
