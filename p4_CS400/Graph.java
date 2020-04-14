///////////////////////////////////////////////////////////////////////////////
//Title: Graph
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class that provides the implementation of the Graph used in Package Manager
//
////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	
    /**
     * Inner class that stores the vertex data and neighbors.
     * @author Siddharth
     *
     * @param <T>
     */
    class GraphNode<T>{
      
      /**
       * Name of the Vertex.
       */
      private T data;
      
      /**
       * List of graph nodes that stores the neighbors that this vertex points to.
       */
      private List<GraphNode<T>> neighbors;
      
      /**
       * List of strings that stores the neighbors that this vertex points to.
       */
      private List<String> stringNeighbors;
      
      /**
       * Contructor of the Node class.
       * @param vertex - name of vertex
       */
      private GraphNode(T vertex){
        data = vertex;
        neighbors = new ArrayList<GraphNode<T>>();
        stringNeighbors = new ArrayList<String>();
      }
      
      /**
       * Method to add a new neighbor to the vertex
       * @param vertex
       */
      private void addNeighbor(GraphNode<T> vertex) {
        this.neighbors.add(vertex);
        this.stringNeighbors.add((String) vertex.data);
      }
    }
  
    
    /**
     * List in the graph class that stores all the vertices(Graph Nodes).
     */
    private List<GraphNode<String>> vertexListSuc;
    
    /**
     * A set of vertices.
     */
    private Set<String> vertices;
    
	/**
	 * Contructor of the Graph class.
	 */
	public Graph() {
		vertexListSuc = new ArrayList<>();
		vertices = new HashSet<String>();
	}
	
	/**
	 * Private method that takes the name of a vertex and returns the node in the graph with that
	 * vertex stored as data.
	 * @param vertex - to find node of
	 * @return GraphNode that stores the param vertex
	 */
	private GraphNode<String> getNode(String vertex) {
	  //Loops through the vertex list and returns the node if it finds it, else null.
	  for(int i=0; i < vertexListSuc.size(); i++) {
	    GraphNode<String> temp = vertexListSuc.get(i);
	    if(temp.data.equals(vertex)) {
	      return temp;
	    }
	  }
	  return null;
	}
	
	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {
	  //if vertex is null or already in teh graph do nothing and exit method.
		if(vertex == null || vertices.contains(vertex)) {
		  return;
		}
		//Add vertex to both Vertex List and Set
		vertexListSuc.add(new GraphNode<String>(vertex));
		vertices.add(vertex);
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {
	  //if vertex is null or not in the graph, do nothing and exit method.
	  if(vertex == null || !vertices.contains(vertex)) {
        return;
      }
	  //Remove vertex from both Vertex list and Set
	  GraphNode<String> v = this.getNode(vertex);
	  vertexListSuc.remove(v);
      vertices.remove(vertex);
      //Iterates through remaining nodes in the graph and deletes any edge pointing to the deleted node.
      for(int i = 0; i < vertexListSuc.size(); i++) {
        if(vertexListSuc.get(i).neighbors.contains(v)) {
          vertexListSuc.get(i).neighbors.remove(v);
          vertexListSuc.get(i).stringNeighbors.remove(vertex);
        }
      }
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
	  //if either of the vertices are null, do nothing and exit.
	    if(vertex1 == null || vertex2 == null) {
	      return;
	    }
	    //if graph does not contain first vertex, add it
		if(!vertices.contains(vertex1)) {
		  this.addVertex(vertex1);
		}
		//if graph does not contain second vertex, add it too
		if(!vertices.contains(vertex2)) {
          this.addVertex(vertex2);
        }
		//if graph now has both vertices, but does not already have a edge from vertex1 to vertex2
		//add an edge by adding vertex2 to the neighbors list of vertex1
		if(!(this.getNode(vertex1).neighbors.contains(this.getNode(vertex2)))) {
		  ((GraphNode<String>)this.getNode(vertex1)).neighbors.add((GraphNode<String>)this.getNode(vertex2));
		  ((GraphNode<String>)this.getNode(vertex1)).stringNeighbors.add(vertex2);
		}
	}
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {
	  //if either vertices are null, do nothing and exit
	  if(this.getNode(vertex1) == null || this.getNode(vertex2) == null) {
	    return;
	  }
	  //Using private getNode method, get the GraphNode from the String vertices
	  GraphNode<String> v1 = this.getNode(vertex1);
	  GraphNode<String> v2 = this.getNode(vertex2);
	  //check if the neighbors list of vertex1 contains vertex2, if it does -> delete vertex2 from it
	  //if not do nothing and exit
	  if(v1.neighbors.contains(v2)) {
	    v1.neighbors.remove(v2);
	    v1.stringNeighbors.remove(vertex2);
	  }
	  else {
	    return;
	  }
	}	

	/**
     * Returns a Set that contains all the vertices
     * 
	 */
	public Set<String> getAllVertices() {
		return this.vertices;
	}

	/**
     * Get all the neighbor (adjacent) vertices of a vertex
     *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		return this.getNode(vertex).stringNeighbors;
	}
	
	/**
     * Returns the number of edges in this graph.
     */
    public int size() {
      int edges = 0;
      for(int i = 0; i < vertexListSuc.size(); i++) {
        GraphNode<String> temp = vertexListSuc.get(i);
        edges += temp.neighbors.size();
      }
        return edges;
    }

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
        return vertices.size();
    }
}
