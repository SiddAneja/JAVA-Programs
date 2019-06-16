//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestSokoban
// Files:           TestSokoban.java
// Course:          CS200 Fall 2018
//
// Author:          Siddharth Aneja
// Email:           saneja@wisc.edu
// Lecturer's Name: Marc Renault
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as they are developed.
 * These methods are all private as they are only intended for use within this class.
 * 
 * @author Marc Renault
 * @author Siddharth Aneja
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }

    private static void testCheckLevel() {
        int numTests = 8; //variable for number of tests
        int passed = numTests; //variable for number of tests passed
        int res; //variable to store the value returned by checkLevels

        char[][][] failLvl = {{ //creates an array with levels intended to fail the checkLevel tests
            // {' ',' ',' '},
            // {' ',' ',' '},
            // {'@',' ',' '}
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}},
            {
                // {' ',' ','#',' ',' ',' '},
                // {' ',' ','#',' ',' '},
                // {'#',' ','#',' ',' ','#'},
                // {' ',' ',' ',' ',' ',' '},
                // {'#',' ','#',' ','=',' '},
                // {'@',' ',' ',' ',' '}
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.WALL_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.BOX_CHAR, Config.EMPTY_CHAR},
                {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR}},
            {
                // {' ',' ',' ','#',' ',' ',' '},
                // {' ',' ',' ', '#','=',' ',' '},
                // {' ',' ','#','#',' ',' ',' ',' '},
                // {' ','=',' ',' ',' ',' ',' ',' ','#'},
                // {'#',' ','#','#',' '},
                // {' ',' ','#',' ',' ',' ','@','#'},
                // {'#',' ',' ',' ',' ','#',' ',' '},
                // {'#','#',' ',' ','=',' ',' ',' ','#'},
                // {'#','#','#',' ',' ',' ',' ',' ',' ',' '}
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                    Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                    Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.WALL_CHAR},
                {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                    Config.EMPTY_CHAR, Config.EMPTY_CHAR}},
            {
                // {' ',' ',' '},
                // {' ','#',' '},
                // {' ',' ',' '}
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}},
            {
                // {' ',' ','#'},
                // {' ','#',' '},
                // {'@',' ',' '}
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}}

        };
        //creates an array with goals for each array
        int[][] failGoals = {{1, 2, 1, 2}, {0, 1, 2, 2}, {0, 3, 5, 5, 8, 8}, {1, 1}, {1, 2, 1, 2}}; 


        // Test 1 -- Testing whether checkLevel correctly checks whether lvl is a valid input greater than 0
        if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }

        // Test 2 -- Testing whether checkLevel correctly tests if lvl is a valid index in levels and the array that exists at lvl has at
        // least one row
        char[][][] lvl = new char[2][][];  //create a level which wont contain atleast one row 
        if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 3 -- Testing whether checkLevel correctly tests if lvl is a valid index in goals and the array that exists at lvl has
        // even number of cells
        int[][] goals = new int[3][]; //creates a goal array that doesn't exist
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, goals)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
            passed--;
        }

        // Test 4 -- Testing whether checkLevel correctly tests if the number of boxes is more than 0 by counting the number of boxes in
        // the lvl
        if ((res = Sokoban.checkLevel(0, failLvl, failGoals)) != -3) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
            passed--;
        }

        // Test 5 -- Testing whether checkLevel correctly checks whether the number of boxes is equal to the number of goals
        if ((res = Sokoban.checkLevel(1, failLvl, failGoals)) != -4) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned " + res);
            passed--;
        }

        // Test 6 -- Testing whether checkLevel correctly checks if the coordinate of each goal is valid for a lvl and it is not a wall
        if ((res = Sokoban.checkLevel(2, failLvl, failGoals)) != -5) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 6. Expected -5, but value returned " + res);
            passed--;
        }

        // Test 7 -- Testing whether checkLevel correctly checks whether the number of workers is exactly one or not
        if ((res = Sokoban.checkLevel(3, failLvl, failGoals)) != -6) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 7. Expected -6, but value returned " + res);
            passed--;
        }

        // Test 8 -- Testing whether checkLevel correctly tests for duplicate goals
        if ((res = Sokoban.checkLevel(4, failLvl, failGoals)) != -7) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 8. Expected -7, but value returned " + res);
            passed--;
        }



        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if (a == null || b == null) //if the arrays have no reference return false
            return false;
        if (a.length != b.length) //if the length of the arrays are not equal return false
            return false;
        for (int i = 0; i < a.length; i++) { //iterates through the array and compares each value
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    private static void testInitBoard() {
        int numTests = 2; //variable for number of tests
        int passed = numTests; // variables for number of tests passed

        // Test 1 -- Tests if the position of the worker is initialized correctly
        int[] pTest1 = new int[2]; //variable to store worker position
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1); //initializes the board and stores it in bTest1
        if (!Arrays.equals(pTest1, new int[] {4, 4})) { //compares the value of position of the worker returned to the known position of the worker
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest1));
        }
        char[][] bCompTest1 = new char[][] { //creates the expected board result from initBoard
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        if (!compBoards(bTest1, bCompTest1)) { //checks whether the board returned by initBoard is equal to the expected board
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);
            passed--;
        }
        // End of Test 1

        //Test 2 
        int[] pTest2 = new int[2]; //variable to store worker position
        char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2); //initializes the board and stores it in bTest1
        if (!Arrays.equals(pTest2, new int[] {7, 10})){  //compares the value of position of the worker returned to the known position
            System.out.println(
                "FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10} , but value after call "
                    + Arrays.toString(pTest1));
        }
        char[][] bCompTest2 = new char[][] { //creates the expected board result from initBoard
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}
        } ; 
        if (!compBoards(bTest2, bCompTest2)) { //checks whether the board returned by initBoard is equal to the expected board
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);
            passed--;
        }
       //End of test 2   
            
        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testCheckWin() {
        int numTests = 1; //variable for the number of tests
        int passed = numTests; //variable for the number of tests passed
        
        char[][] testBoard1 = new char[][] { //create a board which does not pass checkWin
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        
        if((Sokoban.checkWin(testBoard1))) { //checkWin should retuen false for this board
            System.out.println("FAILED: Sokoban.checkWin Test 1. Expected False but value returned:" + Sokoban.checkWin(testBoard1));
            passed--;
        }       
        
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testCalcDelta() {
        int numTests = 1; //variable for number of tests
        int passed = numTests; //variable for the number of tests passed
        
        int[] test1 = Sokoban.calcDelta("8400"); // stores the array returned by calcDelta for the input
        
        int[] expectedResult1 = new int[] {-400, 0}; //store out expected result from calcDelta
        
        if (!(test1[0] == expectedResult1[0] && test1[1] == expectedResult1[1])) { //compare the array returned and the expected array
            System.out.println("FAILED: Sokoban.calcDelta Test 1. Expected " + expectedResult1 + " but value returned:" + test1);
            passed--;}
        
        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
    }   
        
    private static void testCheckDelta() {
        int numTests = 1; //variable to store number of tests 
        int passed = numTests; //variable to store number of tests passed
        
        //Test 1 - if new pos is not on the board.
        char[][] testBoard1 = new char[][] { //create a board for this test
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        
         int[] pos = new int[] {4, 4}; //initializes the position of the worker
         int[] delta = new int[] {2, 0}; //the movement vector takes the worker outside the board
         
         char[] valid = new char[1];
         valid[0] = testBoard1[pos[0]][pos[1]]; //initializes the valid array to the worker char
         
         if(!(Sokoban.checkDelta(testBoard1, pos, delta, valid) == -4)) { //checkDelta should return -4 for a delta outside the board
             System.out.println("FAILED: Sokoban.checkDelta Test 1. Expected -1 but value returned:" + Sokoban.checkDelta(testBoard1, pos, delta, valid));
             passed--;
         }
         System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testTogglePos() {
        int numTests = 1; //variable to store the number of tests
        int passed = numTests; //variable to store the number of tests passed
        char[][] testBoard1 = new char[][] { //creates a board for this test
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        int[] pos = new int[] {0,0}; //initializes the position to 0,0
        char val = Config.GOAL_CHAR; //initializes the character to check for in TogglePos
        Sokoban.togglePos(testBoard1, pos, val, Config.WORK_GOAL_CHAR, Config.BOX_CHAR);
        if(testBoard1[0][0] != Config.BOX_CHAR) { //the character at 0,0 should be a box char after togglePos
            System.out.println("FAILED: Sokoban.togglePos Test 1. Expected " + Config.BOX_CHAR + " but value returned:" + testBoard1[0][0]);
            passed--;
        }
        System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testShiftBox() {
        int numTests = 1; //variable to store the number of tests
        int passed = numTests; // variable to store the number of tests passed
        char[][] testBoard1 = new char[][] { //creates a board for this test
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        int[] pos = new int[] {2,3}; //stores the pos of the box char
        int[] delta = new int[] {1,0}; //creates a delta for the movement of the box
        int[] newPos = new int[] {pos[0] + delta[0], pos[1] + delta[1]}; //creates an array that stores the new pos of the box after delta
        if(Sokoban.shiftBox(testBoard1, pos, delta) == 1) { //shiftBox should return 1 and the character at newPos should be a box char
            if(testBoard1[newPos[0]][newPos[1]] != Config.BOX_CHAR) {
                System.out.println("FAILED: Sokoban.shiftBox Test 1. Expected value at (3,3) " + Config.BOX_CHAR + " but value returned:" + testBoard1[newPos[0]][newPos[1]]);
                passed--;
            }
        }
        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testDoMove() {
        int numTests = 1; //variable to store number of tests
        int passed = numTests; //variable to store number of tests passed
        //creates a board to test the method
        char[][] testBoard1 = new char[][] {
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        int[] pos = new int[] {4,4}; //position of the worker on the board
        int[] delta = new int[] {-1,0}; //movement vector for the worker
        int[] newPos = new int[] {pos[0] + delta[0], pos[1] + delta[1]}; //initializes an array that stores the new position of the worker after it moves
        if(Sokoban.doMove(testBoard1, pos, delta) == 1) { 
            if(testBoard1[newPos[0]][newPos[1]] != Config.WORKER_CHAR) { //the newPos should contain the worker char after doMove
                System.out.println("FAILED: Sokoban.doMove Test 1. Expected value at (3,4) " + Config.WORKER_CHAR + " but value returned:" + testBoard1[newPos[0]][newPos[1]]);
                passed--;
            }
        }
        System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testProcessMove() {
        int numTests = 1; //variable to contain the number of tests
        int passed = numTests;//variable to contain the number of tests passed
        //creates a board to test the method
        char[][] testBoard1 = new char[][] {
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        int[] pos = new int[] {4,4}; //position of the worker on the board
        int[] delta = new int[] {0,0}; //movement vector for the worker which is 0,0 so should fail processMove
        if(Sokoban.processMove(testBoard1, pos, delta) != 0) {
            System.out.println("FAILED: Sokoban.processMove Test 1. Expected value 0, but value returned is " + Sokoban.processMove(testBoard1, pos, delta));
            passed--;
        }
        System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");
    }

}
