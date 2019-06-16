//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: Sokoban.java
// Course: CS200 Fall 2018
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Marc Renault
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
import java.util.Random;

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        // Starts a loop that continues to prompt the user for input until the required criteria are
        // met
        do {
            System.out.print(prompt);
            int userInput;
            if (sc.hasNextInt()) { // Check whether the input is a int
                userInput = sc.nextInt();
                sc.nextLine();
                if ((userInput >= min) && (userInput <= max)) { // checks if the values are b/w min
                                                                // and max
                    return userInput;
                }
            } else {
                System.out.println("Invalid value.");
                sc.nextLine();
            }

        } while (true);

    }

    /**
     * Prompts the user for a char value by displaying prompt. Note: This method should not be a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        System.out.print(prompt); // Prints out the prompt
        String userInput = sc.nextLine().trim().toLowerCase(); // Takes an input from the user
                                                               // Trims it and converts it to lower
                                                               // case
        char userInputChar = userInput.charAt(0); // Only accepts the first characters as the return
                                                  // value
        if (!Character.isWhitespace(userInputChar)) { // if the value is not a whitespace then it
                                                      // returns the value otherwise it return the
                                                      // null character
            return userInput.charAt(0);
        } else {
            return '\0';
        }
    }

    /**
     * Prompts the user for a string value by displaying prompt. Note: This method should not be a
     * new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, remove any leading
     * and trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and
     *         trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        System.out.print(prompt); // Prints out the prompt
        String userInput = sc.nextLine().trim(); // takes a String input from the user and trims it
        userInput = userInput.toLowerCase(); // converts the user input to lower case
        return userInput;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. a - For each row, copy the values from the
     * corresponding row in the 2-d array contained at index lvl in levels. b - When the worker is
     * located, it's position should be recorded in the pos parameter. 2 - For each goal described
     * in the array at index lvl of goals, convert the character at the goal coordinate to: -
     * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it contains a box -
     * Config.GOAL_CHAR otherwise
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and
     *        index 1 is the column.
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        int i;
        int j; // initializes two variables for the for loop
        char[][] board = new char[levels[lvl].length][]; // initializes board to the 2D array at
                                                         // index lvl at levels

        // iterates through levels and assigns the position of the worker to the array pos
        for (i = 0; i < levels[lvl].length; i++) {
            board[i] = new char[levels[lvl][i].length];
            for (j = 0; j < levels[lvl][i].length; j++) {
                board[i][j] = levels[lvl][i][j]; // creates the board from the Config file
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // For each goal described in the array at index lvl of goals, converts the character at the
        // goal coordinates
        for (i = 0; i < goals[lvl].length - 1; i += 2) {
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WORKER_CHAR) {
                board[goals[lvl][i]][goals[lvl][i + 1]] = Config.WORK_GOAL_CHAR;
            } else if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.BOX_CHAR) {
                board[goals[lvl][i]][goals[lvl][i + 1]] = Config.BOX_GOAL_CHAR;
            } else {
                board[goals[lvl][i]][goals[lvl][i + 1]] = Config.GOAL_CHAR;
            }
        }

        return board;
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer wall
     * to the left and the right. 2 - For each row in board, print out a Config.WALL_CHAR, followed
     * by the contents of the row, followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence
     * of Config.WALL_CHAR with a length equal to that of the last row of board, plus the outer wall
     * to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        int i;
        int j;// initialize two variables for the for loop

        for (i = -1; i <= board[0].length; i++) { // prints the top wall characters of the board
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();

        // prints out the actual Sokoban board
        for (i = 0; i < board.length; i++) {
            for (j = -1; j <= board[i].length; j++) {
                if (j == -1 || j == board[i].length) {
                    System.out.print(Config.WALL_CHAR);
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }

        for (i = -1; i <= board[board.length - 1].length; i++) { // prints the bottom wall
                                                                 // characters of the board
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
    }

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is a valid index in
     * levels, that the 2-d array at index lvl exists and that it contains at least 1 row. 3 - lvl
     * is a valid index in goals, the 1-d array at index lvl exists and that it contains an even
     * number of cells. 4 - the number of boxes is more than 0. 5 - the number of boxes equals the
     * number of goals. 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check for duplicate
     * goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @author Siddharth Aneja
     * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2 fails: -1 - Test 3
     *         fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 - Test 6 fails: -5 - Test 7 fails: -6
     *         - Test 8 fails: -7
     * 
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {
        // Test 1 -- Checks whether lvl is a valid input greater than 0
        if (lvl < 0) {
            return 0;
        }

        // Test 2 -- Tests if lvl is a valid index in levels and the array that exists at lvl has at
        // least one row
        if ((lvl > levels.length) || (levels[lvl] == null) || (levels[lvl].length < 1)) {
            return -1;
        }


        // Test 3 -- Tests if lvl is a valid index in goals and the array that exists at lvl has
        // even number of cells
        if ((lvl > goals.length) || (goals[lvl] == null) || (goals[lvl].length % 2 == 1)) {
            return -2;
        }
        // Test 4 -- Tests if the number of boxes is more than 0 by counting the number of boxes in
        // the lvl
        int boxes = 0; // Variable for number of boxes

        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == Config.BOX_CHAR) {
                    boxes++;
                }
            }
        }
        if (boxes < 1) {
            return -3;
        }

        // Test 5 -- checks whether the number of boxes is equal to the number of goals
        if (boxes != (goals[lvl].length / 2)) {
            return -4;
        }

        // Test 6 -- checks if the coordinate of each goal is valid for a lvl and it is not a wall
        for (int i = 0; i < goals[lvl].length - 1; i = i + 2) {
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WALL_CHAR) {
                return -5;
            }
        }


        // Test 7 -- checks whether the number of workers is exactly one or not
        int workers = 0;
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    workers++;
                }
            }
        }
        if (workers != 1) {
            return -6;
        }


        // Test 8 -- Tests for duplicate goals
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }

        return 1; // returns 1 if all the test's pass
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows: - The 1st character of the String
     * represents the direction. - The remaining characters (if there are any) are interpreted as
     * integer and represent the magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would represent moving up
     * by one character. - If the parameter moveStr is "65": An array {0, 5} would represent moving
     * right by 5 characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        int[] delta = new int[] {0, 0};
        int direction;
        int magnitude; // variable declaration for the direction and magnitude for delta

        direction = (moveStr.charAt(0)); // gets the first char value to determine the movement
                                         // vector
        moveStr = moveStr.substring(1);
        Scanner scnr = new Scanner(moveStr);
        // gets the integer value of the magnitude
        if (scnr.hasNextInt()) {
            magnitude = Integer.valueOf(moveStr);
        } else {
            magnitude = 1;
        }
        // checks whether the direction variable is equal to any direction variable in Config
        // then creates delta with the magnitude
        if (direction == Config.UP_CHAR) {
            delta[0] = -1 * magnitude;
        } else if (direction == Config.DOWN_CHAR) {
            delta[0] = magnitude;
        } else if (direction == Config.LEFT_CHAR) {
            delta[1] = -1 * magnitude;
        } else if (direction == Config.RIGHT_CHAR) {
            delta[1] = magnitude;
        }


        return delta;
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check: 1 - that pos is valid. 2 - that the
     * character at pos in board is in the valid array. 3 - that the delta is valid. 4 - that the
     * new position is valid and not a wall character. 5 - that the new position is not a box
     * character For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and index 1
     *        is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2, or not on the
     *         board. -2 : if the character at pos is not valid (not in the valid array). -3 : if
     *         delta is null or not length 2. -4 : if the new position is off the board or a wall
     *         character -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        // Test 1
        // checks if position is not of length 2
        if (pos == null || pos.length != 2) {
            return -1;
        }
        // checks if position is off the board
        if (pos[0] < 0 || pos[1] < 0 || pos[0] >= board.length || pos[1] >= board[pos[0]].length) {
            return -1;
        }

        // Test 2
        // checks if char at the position coordinates is in the valid array using boolean value
        boolean isValid = false;
        for (int i = 0; i < valid.length; ++i) {
            if (board[pos[0]][pos[1]] == valid[i]) {
                isValid = true;
            }
        }
        if (!isValid) {
            return -2;
        }

        // Test 3
        // checks if delta is null or has an invalid length
        if ((delta == null) || (delta.length != 2)) {
            return -3;
        }

        // Test 4
        // creates new array with coordinates of new position
        int[] newPos = new int[2];
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];

        // checks if new position if off the board
        if ((newPos[0] < 0) || (newPos[1] < 0) || (newPos[0] >= board.length)
            || (newPos[1] >= board[newPos[0]].length)) {
            return -4;
        }

        // checks if new position is a wall character
        if (board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
            return -4;
        }

        // Test 5
        // checks if new position is a box character
        if (board[newPos[0]][newPos[1]] == Config.BOX_CHAR
            || board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
            return -5;
        }

        return 1;

    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        if (board[pos[0]][pos[1]] == val) { // checks if the character at pos is equal to val
            board[pos[0]][pos[1]] = opt1; // then changes the character at pos to opt1
        } else {
            board[pos[0]][pos[1]] = opt2; // changes the character at pos to opt2 if the character
                                          // at pos is not equal to val
        }
    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent a box. Step 2: Use your togglePos method to correctly change
     * the character at the new position to the appropriate box character. Step 3: Again use your
     * togglePos method to correctly change the character at pos to the the appropriate character
     * without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        char[] valid = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR}; // create a valid array which
                                                                // contains both box characters
        int check = checkDelta(board, pos, delta, valid); // this variable contains the variable
                                                          // returned by checkDleta
        if (check < 1) { // if the value returned by checkDelta is less than one, it returns that
                         // number
            return check;
        }
        if (check == 1) { // if the value of checkDelta is 1, this branch uses togglePos method to
                          // move the box
            int[] newPos = new int[] {pos[0] + delta[0], pos[1] + delta[1]}; // A new array with the
                                                                             // newPos of the box
                                                                             // after moving acc. to
                                                                             // delta
            togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);
            togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
        }
        return 1; // return 1 if successful
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. That is, if the delta is {0,
     * -3}, your method should call doMove three times with an argument of {0, -1} for the delta
     * parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove six times with an
     * argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0. If the call to doMove returns a value
     *         less than 1, return that value. Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        int upNDown = delta[0]; // the value for the up and down movement
        int leftNRight = delta[1]; // the value for the left and right movement
        int size; // This variable with contain the absolute value of the value in delta that is not
                  // 0
        if (upNDown != 0 || leftNRight != 0) { // this branch executes if neither of the indexes in
                                               // delta are 0
            size = upNDown + leftNRight;
            if (size < 0) {
                size = -1 * size;
            }
            upNDown = upNDown / size; // getting the value for the single Up and Down movement array
            leftNRight = leftNRight / size; // getting the value for the single Left and Right
                                            // movement array
            int[] doMoveDelta = new int[] {upNDown, leftNRight}; // creates the single move array
                                                                 // for each doMove
            for (int i = 0; i < size; ++i) { // call doMove for one step multiple times
                int check = doMove(board, pos, doMoveDelta);
                if ((check) < 1) {
                    return check;
                }
            }
        } else {
            return 0; // returns 0 if both indexes in delta are 0
        }
        return 1; // return 1 if successful
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent the worker. Step 2: If checkDelta returns -5, use your shiftBox
     * method to move the box by delta before moving the worker. Step 3: Use your togglePos method
     * to correctly change the character at the new position to the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the the
     * appropriate character without a worker. Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. If
     *         checkDelta returns -5 and shiftBox returns a value less than 0, return 0. Otherwise,
     *         return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        char[] valid = new char[] {Config.WORK_GOAL_CHAR, Config.WORKER_CHAR}; // Creates a valid
                                                                               // array with both
                                                                               // Worker characters
        int check = checkDelta(board, pos, delta, valid); // Assigns the value returned by
                                                          // checkLevels to check
        if (check < 1 && check != -5) { // if the value returned by checkLevels is less than 1 and
                                        // not -5
                                        // return that value
            return check;
        }
        int[] newPos = {pos[0] + delta[0], pos[1] + delta[1]}; // creates a new array with the new
                                                               // pos after moving delta
        // if the newPos is a box then this branch executes
        if (check == -5) {
            int checkNegative = shiftBox(board, newPos, delta); // Shifts the box by delta before
                                                                // moving the worker
                                                                // assigns the value returned to
                                                                // checkNegative
            if (checkNegative < 0) {
                return 0;
            }
            // moves the worker by delta and changes the value of pos
            togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
            togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
            pos[0] = newPos[0];
            pos[1] = newPos[1];
        }
        if (check == 1) { // moves the worker
            togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
            togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
            pos[0] = newPos[0];
            pos[1] = newPos[1];
        }
        return 1;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        int i;
        int j; // initializes two variables
        // iterates through the loop to check if all the goals are covered and if not, it returns
        // false
        // else true
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                if (board[i][j] == Config.GOAL_CHAR) {
                    return false;
                }
                if (board[i][j] == Config.WORK_GOAL_CHAR) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // initializes Scanner
        Random rand = new Random(Config.SEED); // Initializes Random with the seed
        System.out.println("Welcome to Sokoban!");
        int maxLvl = Config.LEVELS.length - 1; // contains the maximum number of levels in Config
        String moveStr = null; // initializes the String variable for calcDelta
        do {
            int[] pos = new int[] {0, 0}; // initializes the pos of the worker
            int lvl = promptInt(sc, "Choose a level between 0 and " + maxLvl + ": ", -1, maxLvl);
            if (lvl == -1) { // if the user enters -1 then randomly chooses a lvl between 0 and
                             // maxLvl
                lvl = rand.nextInt(maxLvl + 1);
            }
            int checkLevelPass = checkLevel(lvl, Config.LEVELS, Config.GOALS); // checkLevel passes
                                                                               // 1 when it passes
                                                                               // all the tests
            if (checkLevelPass == 1) {
                char[][] board = initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // initializes
                                                                                   // the board and
                                                                                   // assigns it to
                                                                                   // the variable
                System.out.println("Sokoban Level " + lvl);
                int movesCount = 0; // variable to count the number of moves the user takes to
                                    // finsih the game
                // starts game loop till the checkWin condition is true
                do {
                    printBoard(board); // prints out the board
                    moveStr = promptString(sc, ": "); // prompts the user for the movement input for
                                                      // calcDelta
                    if (moveStr.equals(null) || moveStr.equals("")) { // if there is no input,
                                                                      // prompt again
                        continue;
                    }
                    if (moveStr.charAt(0) == (Config.QUIT_CHAR)) {// if input is the quit char then
                                                                  // stop the game loop
                        break;
                    } else {
                        int[] delta = calcDelta(moveStr); // create delta with the movement input
                        char[] valid = {Config.WORKER_CHAR, Config.WORK_GOAL_CHAR};
                        processMove(board, pos, delta); // call process move to step by step move
                                                        // the worker
                        movesCount += Math.abs(delta[0]) + Math.abs(delta[1]); // increments the
                                                                               // moves

                    }
                } while (!checkWin(board));

                if (checkWin(board)) {
                    System.out.println("Congratulations! You won in " + movesCount + " moves!");
                    printBoard(board);
                }
                char playAgain = promptChar(sc, "Play again? (y/n) "); // prompts the user to play
                                                                       // again and restarts the
                                                                       // game if 'y' is entered
                if (playAgain != 'y') {
                    break;
                }
            } else { // if the checkLevels does not return 1, then the following switch statements
                     // execute
                System.out.println("“Error loading level!");
                switch (lvl) {
                    case 0:
                        System.out.print("Level" + lvl + " must be 0 or greater!");
                        break;
                    case -1:
                        System.out.print("Error with Config.LEVELS");
                        break;
                    case -2:
                        System.out.print("Error with Config.GOALS");
                        break;
                    case -3:
                        System.out.print("Level " + lvl + " does not contain any boxes.");
                        break;
                    case -4:
                        System.out.print("Level " + lvl
                            + " does not have the same number of boxes as\r\n" + "goals.");
                        break;
                    case -5:
                        System.out.print("Level " + lvl + " has a goal location that is a wall");
                        break;
                    case -6:
                        System.out.print("Level " + lvl + " has 0 or more than 1 worker(s).");
                        break;
                    case -7:
                        System.out.print("Level " + lvl + " contains duplicate goals.");
                        break;
                    default:
                        System.out.print("Unknown Error");
                }
            }
        } while (true);
        System.out.println("Thanks for playing!");
    }
}
