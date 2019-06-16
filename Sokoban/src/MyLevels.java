//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MyLevels
// Files:           MyLevels.java
// Course:          CS200 Fall 2018
//
// Author:          Siddharth Aneja
// Email:           saneja@wisc.edu
// Lecturer's Name: Marc Renault
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class contains the new levels used in the Sokoban program.
 * 
 * @author Siddharth Aneja
 */
public class MyLevels {
    
    /**
     * Initial configuration of the levels. Note that the location of the goals are defined in the 
     * GOALS array which is a parallel array to LEVELS.
     */
    public static final char[][][] LEVELS = {
        {
            //{' ',' ',' '},
            //{' ','=',' '},
            //{'@',' ',' '}
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR},
            {Config.WORKER_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR}
        },
        {
            //{' ',' ','#',' ',' ',' '},
            //{' ','=','#',' ',' '},
            //{'#',' ','#',' ',' ','#'},
            //{' ',' ',' ',' ',' ',' '},
            //{'#',' ','#',' ','=',' '},
            //{'@',' ',' ',' ',' '}
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.BOX_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR},
            {Config.WORKER_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR}
        },
        {
            //{' ',' ',' ','#',' ',' ',' '},
            //{' ',' ',' ', '#','=',' ',' '},
            //{' ',' ','#','#',' ',' ',' ',' '},
            //{' ','=',' ',' ',' ',' ',' ',' ','#'},
            //{'#',' ','#','#',' '},
            //{' ',' ','#',' ',' ',' ','@','#'},
            //{'#',' ',' ',' ',' ','#',' ',' '},
            //{'#','#',' ',' ','=',' ',' ',' ','#'},
            //{'#','#','#',' ',' ',' ',' ',' ',' ',' '}
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
            {Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WORKER_CHAR,Config.WALL_CHAR},
            {Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
            {Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
            {Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR}
        }
        
    };
    
    /**
     * Position of the goals for each level. Every pair of values represents the row and column of 
     * a goal. This is a parallel array to LEVELS.
     */
    public static final int[][] GOALS = {
        {1, 2}, {0, 1, 3, 5}, {2, 4, 5, 5, 8, 8}
    };

}
