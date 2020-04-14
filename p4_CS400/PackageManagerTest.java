///////////////////////////////////////////////////////////////////////////////
//Title: PackageManagerTest
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class tests the implementation of the PackageManager class
//
////////////////////////////////////////////////////////////////////////////

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageManagerTest {
  
private PackageManager pManager;
  
  @BeforeEach
  public void setUp() throws Exception {
       pManager = new PackageManager();
       pManager.constructGraph("valid.json");
  }
  
  @AfterEach
  public void tearDown() throws Exception {
      pManager = null;
  }
  
  @Test
  void test01_installtion_order() {
    try {
      List<String> test = pManager.getInstallationOrder("A");
      List<String> correct = new ArrayList<>();
      correct.add("C");
      correct.add("D");
      correct.add("B");
      correct.add("A");
      for(int i = 0; i < correct.size(); i++) {
        if(!test.get(i).equals(correct.get(i))) {
          fail("values do not match");
        }
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test02_to_install() {
    try {
      List<String> test = pManager.toInstall("A", "E");
      List<String> correct = new ArrayList<>();
      correct.add("A");
      for(int i = 0; i < correct.size(); i++) {
        if(!test.get(i).equals(correct.get(i))) {
          fail("values do not match");
        }
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test03_get_all_packages() {
    try {
      List<String> test = pManager.getInstallationOrderForAllPackages();
      List<String> correct = new ArrayList<>();
      correct.add("C");
      correct.add("D");
      correct.add("B");
      correct.add("E");
      correct.add("A");
      for(int i = 0; i < correct.size(); i++) {
        if(!test.get(i).equals(correct.get(i))) {
          fail("values do not match");
        }
      }
    }catch(Exception e) {
      fail("unexpected exception");
    }
  }
  
  @Test
  void test04_max_dependencies() {
    try {
      String test = pManager.getPackageWithMaxDependencies();
      if(!test.equals("A")) {
        fail("values do not match");
      }
    }catch(Exception e) {
      fail("unexcepted exception");
    }
  }
}
