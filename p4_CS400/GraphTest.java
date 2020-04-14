///////////////////////////////////////////////////////////////////////////////
//Title: GraphTest
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class tests the implementation of the Graph class
//
////////////////////////////////////////////////////////////////////////////

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the Graph class implementation.
 * @author Siddharth
 *
 */
class GraphTest {
  
  /**
   * Graph for testing.
   */
  private Graph graph;
  
  @BeforeEach
  public void setUp() throws Exception {
       graph = new Graph();
  }
  
  @AfterEach
  public void tearDown() throws Exception {
      graph = null;
  }
  
  @Test
  void test01_initializes_empty_graph() {
    try {
      if(graph.size() != 0) {
        fail("graph should have 0 edges");
      }
      if(graph.order() != 0) {
        fail("graph should have 0 vertices");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test02_add_one_vertex() {
    try {
      graph.addVertex("sid");
      if(graph.size() != 0) {
        fail("graph should have 0 edges");
      }
      if(graph.order() != 1) {
        fail("graph should have 1 vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test03_add_multiple_not_connected_vertices() {
    try {
      graph.addVertex("sid");
      graph.addVertex("alyssa");
      graph.addVertex("matt");
      graph.addVertex("grant");
      graph.addVertex("chef");
      if(graph.size() != 0) {
        fail("graph should have 0 edges");
      }
      if(graph.order() != 5) {
        fail("graph should have 5 vertices");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test04_add_one_remove_one() {
    try {
      graph.addVertex("sid");
      graph.removeVertex("sid");
      if(graph.size() != 0) {
        fail("graph should have 0 edges");
      }
      if(graph.order() != 0) {
        fail("graph should have 0 vertices");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test05_add_two_vertex_add_edge() {
    try {
      graph.addVertex("sid");
      graph.addVertex("alyssa");
      graph.addEdge("sid", "alyssa");
      if(graph.size() != 1) {
        fail("graph should have 1 edges");
      }
      if(graph.order() != 2) {
        fail("graph should have 2 vertices");
      }
      List<String> neighbor = graph.getAdjacentVerticesOf("sid");
      if(neighbor.size() != 1) {
        fail("vertex should have 1 adjacent vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test06_addEdge_for_edges_that_do_not_exist() {
    try {
      graph.addEdge("sid","alyssa");
      if(graph.size() != 1) {
        fail("graph should have 1 edges");
      }
      if(graph.order() != 2) {
        fail("graph should have 2 vertices but is " + graph.order() );
      }
      List<String> neighbor = graph.getAdjacentVerticesOf("sid");
      if(neighbor.size() != 1) {
        fail("vertex should have 1 adjacent vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test07_removeEdge_for_two_vertices() {
    try {
      graph.addEdge("sid", "alyssa");
      graph.removeEdge("sid", "alyssa");
      if(graph.size() != 0) {
        fail("graph should have 0 edges");
      }
      if(graph.order() != 2) {
        fail("graph should have 2 vertices but is " + graph.order() );
      }
      List<String> neighbor = graph.getAdjacentVerticesOf("sid");
      if(neighbor.size() != 0) {
        fail("vertex should have 0 adjacent vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test08_get_all_vertices() {
    try {
      graph.addEdge("sid", "alyssa");
      graph.addVertex("matt");
      graph.addVertex("grant");
      graph.addVertex("chef");
      Set<String> vertices = graph.getAllVertices();
      if(vertices.size() != 5) {
        fail("graph should contain 5 vertices");
      }
      if(!vertices.contains("sid")) {
        fail("set should contain sid");
      }
      if(!vertices.contains("alyssa")) {
        fail("set should contain alyssa");
      }
      if(!vertices.contains("matt")) {
        fail("set should contain matt");
      }
      if(!vertices.contains("grant")) {
        fail("set should contain grant");
      }
      if(!vertices.contains("chef")) {
        fail("set should contain chef");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test09_adding_multiple_edges_to_vertex() {
    try {
      graph.addEdge("sid", "alyssa");
      graph.addEdge("matt", "sid");
      graph.addEdge("grant", "sid");
      if(graph.size() != 3) {
        fail("graph should have 3 edges");
      }
      if(graph.order() != 4) {
        fail("graph should have 4 vertices but is " + graph.order() );
      }
      List<String> neighbor = graph.getAdjacentVerticesOf("sid");
      if(neighbor.size() != 1) {
        fail("vertex sid should have 1 adjacent vertex");
      }
      neighbor = graph.getAdjacentVerticesOf("matt");
      if(neighbor.size() != 1) {
        fail("vertex matt should have 1 adjacent vertex");
      }
      neighbor = graph.getAdjacentVerticesOf("grant");
      if(neighbor.size() != 1) {
        fail("vertex grant should have 1 adjacent vertex");
      }
      neighbor = graph.getAdjacentVerticesOf("alyssa");
      if(neighbor.size() != 0) {
        fail("vertex alyssa should have 0 adjacent vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test10_connect_2_vertices_and_remove_one() {
    try {
      graph.addEdge("sid", "alyssa");
      graph.removeVertex("alyssa");
      List<String> neighbor = graph.getAdjacentVerticesOf("sid");
      if(neighbor.size() != 0) {
        fail("sid should not have a deleted node as an adjacent vertex");
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
}
