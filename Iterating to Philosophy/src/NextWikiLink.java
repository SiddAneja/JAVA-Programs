/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: NextWikiLink.java
// Files: core.jar
// Course: CS300 Spring 2019
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: NA
// Partner Lecturer's Name: NA
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _x__ Write-up states that pair programming is allowed for this assignment.
// _x__ We have both read and understand the course Pair Programming Policy.
// _x__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
 
/**
 * This class implements the Function interface and runs the wikipedia scrapper using
 * Generator.
 * @author Siddharth
 *
 */
public class NextWikiLink implements Function<String, String> {
  /**
   * Applies this function to the given argument.
   * @returns the new String value
  */
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }
 
  /**
   * The main method for the scrapper which iterates through wikipedia links and prints them.
   * @param args
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // prompts the user to enter a topic name and number of iterations to follow
    System.out.print("Enter a wikipedia page topic: ");
    String topic = sc.nextLine();
    System.out.print("Enter the number of pages you'd like to step through: ");
    int iterations = sc.nextInt();
    // prepends "/wiki/" to the user's input, and replace spaces with underscores 
    topic = "/wiki/" + topic;
    topic.replaceAll(" ", "_");
    //Creates a Generator using the topic, number of iterations and NextWikiLink() method
    Generator<String> generate = new Generator<>(topic, new NextWikiLink(), iterations);
    // uses a for-each loop to iterate through the number of links requested
    for(String links : generate) {
      //breaks out of the loop if any error is returned
      if(links.contains("FAILED to find")) {
        System.out.println(links);
        break;
      }
      //if no error, it prints out all the links
      else {
        System.out.println(links);
      }
    }
  }
}