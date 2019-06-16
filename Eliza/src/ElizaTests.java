//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: descriptive title of the program making use of this file
// Files: a list of all source files used by that program
// Course: course number, term, and year
//
// Author: Siddharth Aneja
// Email: saneja@wisc.edu
// Lecturer's Name: Marc Renault
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as they are developed.
 * These methods are private since they are only intended for use within this class.
 * 
 * @author Jim Williams
 * @author Siddharth Aneja
 *
 */
public class ElizaTests {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {

        // Milestone 1: Process User Input
        // M1: main nothing to do
        testSeparatePhrases();
        testFoundQuitWord();
        testSelectPhrase();
        testReplaceWord();
        testAssemblePhrase();

        // Milestone 2:
        // M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();

        // Milestone 3:
        // main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();
        testSelectResponse();
        testInputAndResponse();
        testSaveDialog();
    }

    /**
     * This runs some tests on the separatePhrases method. 1. Test 1 uses the input phrase "No. I'm
     * talking to my dog! Bye." to generate 3 phrases. 2. Test 2 uses the phrase "Why()( ) - \\\" ]
     * ? I caN't_-pass the Test... ?!" to check if the method can detect different characters. 3.
     * Test 3 uses the phrase "Thank you, but no, I have to go. Goodbye!!!" to generate 4 phrases.
     * 4. Test 4 uses a length 0 phrase to test the method
     */
    private static void testSeparatePhrases() {
        boolean error = false;

        // TEST 1 - No. I'm talking to my dog! Bye.

        ArrayList<String> phrases1 = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
        ArrayList<String> expected1 = new ArrayList<>();
        expected1.add("no");
        expected1.add("i'm talking to my dog");
        expected1.add("bye");

        if (phrases1.size() != expected1.size()) {
            error = true;
            System.out.println("testSeparatePhrases: expected " + expected1.size() + " but found "
                + phrases1.size() + " phrases.");
        }

        for (int i = 0; i < expected1.size(); i++) {
            if (!expected1.get(i).equals(phrases1.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected1.get(i));
                System.out.println("  " + phrases1.get(i));
            }
        }


        // TEST 2 - Why()( ) - \\\" ] ? I caN't_-pass the Test... ?!

        ArrayList<String> phrases2 =
            Eliza.separatePhrases("Why()( ) - \\\" ]  ?  I   caN't_-pass the Test...  ?!");
        ArrayList<String> expected2 = new ArrayList<>();
        expected2.add("why");
        expected2.add("i can't pass the test");

        if (phrases2.size() != expected2.size()) {
            error = true;
            System.out.println("testSeparatePhrases: expected " + expected2.size() + " but found "
                + phrases2.size() + " phrases.");
        }

        for (int i = 0; i < expected2.size(); i++) {
            if (!expected2.get(i).equals(phrases2.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected2.get(i));
                System.out.println("  " + phrases2.get(i));
            }
        }

        // TEST 3 - Thank you, but no, I have to go. Goodbye!!!

        ArrayList<String> phrases3 =
            Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
        ArrayList<String> expected3 = new ArrayList<>();
        expected3.add("thank you");
        expected3.add("but no");
        expected3.add("i have to go");
        expected3.add("goodbye");

        if (phrases3.size() != expected3.size()) {
            error = true;
            System.out.println("testSeparatePhrases: expected " + expected3.size() + " but found "
                + phrases3.size() + " phrases.");
        }

        for (int i = 0; i < expected3.size(); i++) {
            if (!expected3.get(i).equals(phrases3.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected3.get(i));
                System.out.println("  " + phrases3.get(i));
            }
        }

        // Test 4 - Using a length 0 phrase
        ArrayList<String> phrases4 = Eliza.separatePhrases("");
        ArrayList<String> expected4 = new ArrayList<>();
        if (phrases4.size() != expected4.size()) {
            error = true;
            System.out.println("testSeparatePhrases: expected " + expected4.size() + " but found "
                + phrases4.size() + " phrases.");
        }

        for (int i = 0; i < expected4.size(); i++) {
            if (!expected4.get(i).equals(phrases4.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases: phrases not the same");
                System.out.println("  " + expected4.get(i));
                System.out.println("  " + phrases4.get(i));
            }
        }

        // Error printing
        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 1. Test 1 - It checks whether the method
     * can detect a quit-word when it is a separate phrase 2. Test 2 - It tests the method by
     * including a quit-word in a phrase 3. Test 3 - Tests the method with no quit words as any of
     * the phrases
     */
    // TODO should QUIT_WORDS be embedded within foundQuitWord?
    private static void testFoundQuitWord() {
        boolean error = false;

        // TEST 1 - Quit word as a separate phrase
        ArrayList<String> phrases1 = new ArrayList<>();
        phrases1.add("thank you for talking");
        phrases1.add("bye");

        boolean quit1 = Eliza.foundQuitWord(phrases1);
        if (!quit1) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }

        // TEST 2 - Quit-word is a part of the phrase
        ArrayList<String> phrases2 = new ArrayList<>();
        phrases2.add("bye and thank you for talking to me");

        boolean quit2 = Eliza.foundQuitWord(phrases2); // this will return false as quit-word should
                                                       // be a separate phrase
        if (quit2) {
            error = true;
            System.out.println("testFoundQuitWord 2: failed");
        }

        // Test 3 - No Quit-words in the phrases list
        ArrayList<String> phrases3 = new ArrayList<>();
        phrases3.add("thank you so much");
        phrases3.add("catch you later");

        boolean quit3 = Eliza.foundQuitWord(phrases3); // this will return false as quit-word
                                                       // doesn't exist
        if (quit3) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }

        // Error printing
        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 1. Test 1 - Gives 3 different length inputs
     * to check if the method returns the longest expected phrase 2. Test 2 - Tests whether the
     * method returns the lower index phrase if two phrases are of the same length 3. Test 3 - Tests
     * the method for when an empty list is passed in it
     */
    private static void testSelectPhrase() {
        boolean error = false;

        // TEST 1 - choose the longest
        ArrayList<String> phrases1 = new ArrayList<>();
        phrases1.add("no");
        phrases1.add("sometimes I remember being on a boat");
        phrases1.add("not often");
        String choice1 = Eliza.selectPhrase(phrases1);
        if (!choice1.equals("sometimes I remember being on a boat")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }

        // TEST 2 - Two choices of same length
        ArrayList<String> phrases2 = new ArrayList<>();
        phrases2.add("I love eating");
        phrases2.add("sometimes");
        phrases2.add("i like to eat");
        String choice2 = Eliza.selectPhrase(phrases2);
        if (!choice2.equals("I love eating")) {
            error = true;
            System.out.println("testSelectPhrase 2: failed");
        }

        // TEST 3 - empty list is passed
        ArrayList<String> phrases3 = new ArrayList<>();
        String choice3 = Eliza.selectPhrase(phrases3);
        if (!choice3.equals("")) {
            error = true;
            System.out.println("testSelectPhrase 3: failed");
        }

        // Error printing
        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 1. Test 1 - Checks the output of the
     * method with 3 phrases given in the list 2. Test 2 - Tests the method's output for an array
     * with no phrases 3. Test 3 - Checks the method when a list of words with no internal spaces is
     * passed
     */
    private static void testAssemblePhrase() {
        boolean error = false;

        // TEST 1.
        String[] words1 = {"This", "is a", "sentence"};
        String sentence1 = Eliza.assemblePhrase(words1);
        String expectedSentence1 = "This is a sentence";

        if (!sentence1.equals(expectedSentence1)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence1 + "'");
        }

        // TEST 2 - Array with no strings is passed
        String[] words2 = {};
        String sentence2 = Eliza.assemblePhrase(words2);
        String expectedSentence2 = "";

        if (!sentence2.equals(expectedSentence2)) {
            error = true;
            System.out.println("testAssembleSentence 2 failed '" + sentence2 + "'");
        }

        // TEST 3 - A list of words with no internal spaces is passed
        String[] words3 = {"This", "is", "a", "sentence", "please"};
        String sentence3 = Eliza.assemblePhrase(words3);
        String expectedSentence3 = "This is a sentence please";

        if (!sentence3.equals(expectedSentence3)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence3 + "'");
        }
        // Error Printing
        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method. 1. Test 1 - Tests the method on a
     * phrase for keyword "computer" present a the end of the phrase 2. Test 2 - Tests the method on
     * a phrase for keyword "computer" present a the start of the phrase 3. Test 3 - Tests the
     * method on a phrase for keyword "computer" present in the middle of the phrase 4. Test 4 -
     * Tests the method for two keywords "you" and "me" within a phrase 5. Test 5 - Tests the method
     * when there are two keywords, but in the wrong order in the phrase 6. Test 6 - Tests the
     * method when there are two keywords, but the second one is not present in the phrase
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {// block so each test has its own variable scope.
         // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"are", "you", "a", "computer"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a")
                || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"computer", "are", "you"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("")
                || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"does", "that", "computer", "on", "your", "desk", "work"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] phrase = {"why", "don't", "you", "like", "me"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't")
                || !matches[1].equals("like") || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = {"me", "don't", "like", "you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 6.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("like");
            keywords.add("me");
            String[] sentence = {"why", "don't", "you", "like", "this"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 6 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }

    /**
     * This runs some tests on the saveDialog method. 1. Test 1 - This tests if the method can save
     * a dialog with one line 2. Test 2 - This tests if the method can save a dialog with multiple
     * lines
     */
    private static void testSaveDialog() {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // 2. test multiple lines of output written to the file.
        {
            ArrayList<String> list = new ArrayList<>();
            list.add("this is the first line.");
            list.add("This is the second line");
            list.add("That means there are multiple lines");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile
                    .equals(list.get(0) + "\n" + list.get(1) + "\n" + list.get(2) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // Error printing
        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }

    /**
     * Supporting method for testSaveDialog
     * 
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * 
     * @param filename file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This runs some tests on the replaceword method. 1. Test 1 and 2 - Test the method by
     * comparing the values returned by the method and the expected value 2. Test 3 - Tests the
     * return value of the method when the wordMap is length 0
     */
    private static void testReplaceWord() {
        boolean error = false;

        { // TEST 1.
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // TEST 2.
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // TEST 3 - Null wordMap
            String word = "yourself";
            String[][] wordMap = new String[][] {};
            String result = Eliza.replaceWord(word, wordMap);
            if (result == null || !result.equals(word)) {
                error = true;
                System.out.println(
                    "testReplaceWord 3 failed. result:'" + result + "' expected:'" + word + "'");
            }
        }
        // Error printing
        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }

    /**
     * This runs some tests on the swapWords method. 1. Test 1 - Using INPUT_WORD_MAP,"i'm" should
     * be replaced "i am" and "cant" should be replaced by "cannot" 2. Test 2 - Using PRONOUN_MAP,
     * "i'm" should be replaced by "you are" 3. Test 3 - Using PRONOUN_MAP, "my" should be replaced
     * by "your" 4. Test 4 - Using a null wordMap, the phrase parameter is returned 5. Test 5 -
     * Using INPUT_WORD_MAP, "need" should be replace by "desire"
     */
    private static void testSwapWords() {
        boolean error = false;

        { // Test 1 - "i'm" should be replaced "i am" and "cant" should be replaced by "cannot"
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // Test 2 - "i'm" should be replaced by "you are"
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // Test 3 - "my" should be replaced by "your"
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // Test 4 - Phrase parameter is returned if wordMap is null
            String someWords = "Siddharth loves Eliza";
            String expected = someWords;
            String[][] wordMap = null;
            String result = Eliza.swapWords(someWords, wordMap);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 4 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // Test 5 - "need" should be replaced by "desire"
            String someWords = "i need points";
            String expected = "i desire points";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 5 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }
    }

    /**
     * This runs some tests on the selectResponse method. 1. Test 1 - Tests the method for whether
     * or not it chooses a random string from the given arraylist 2. Test 2 - Tests if the method is
     * called a 1000 times, are the response chosen completely random 3. Test 3 - Tests if the
     * method returns null for a null input
     */
    private static void testSelectResponse() {
        boolean error = false;

        { // 1.
          // is one of the 3 strings chosen?
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        { // 2.
          // if called 1000 times, are the choices approximately random?
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int[] actualCount = new int[strList.size()];
            int[] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if (choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }
            // since we set a seed on the random number generator we should know the expected
            // outcome
            for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if (actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString(actualCount));
                }
            }

        }
        { // 3.
          // null is passed to selectResponse
            Random randGen = new Random();
            ArrayList<String> strList = new ArrayList<>();
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice == null))
                System.out.println("testSelectResponse 1 failed.");
        }



        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }
    }

    /**
     * This runs some tests on the prepareInput method. 1. Test 1 - "bye" should result not result
     * in null 2. Test 2 - "I said goodbye" should result in "i", "said", "goodbye" 3. Test 3 - "I
     * can't do that" should result in "i", "cannot", "do", "that"
     */
    private static void testPrepareInput() {
        boolean error = false;

        { // Test 1 - "bye" should not return a null array
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }

        { // Test 2 - "I said goodbye" should result in "i", "said", "goodbye"
            String input = "I said goodbye";
            String[] result = null;
            result = Eliza.prepareInput("I said goodbye");
            String[] expectedResult = {"i", "said", "goodbye"};
            for (int i = 0; i < result.length; i++) {
                if (!result[i].equals(expectedResult[i])) {
                    error = true;
                    System.out.println("testPrepareInput 2: failed");
                    System.out.println("  input: " + input);
                    System.out.println("  result: " + Arrays.toString(result));
                }
            }
        }

        { // Test 3 - "I can't do that" should result in "i", "cannot", "do", "that"
            String input = "I can't do that";
            String[] result = null;
            result = Eliza.prepareInput("I can't do that");
            String[] expectedResult = {"i", "cannot", "do", "that"};
            for (int i = 0; i < result.length; i++) {
                if (!result[i].equals(expectedResult[i])) {
                    error = true;
                    System.out.println("testPrepareInput 3: failed");
                    System.out.println("  input: " + input);
                    System.out.println("  result: " + Arrays.toString(result));
                }
            }
        }

        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }
    }

    /**
     * This runs some tests on the loadResponseTable method. 1. Test 1 - Tests if the first row of
     * the response table is correct and that it has an even size 2. Test 2 - Checks if the right
     * number of keywords and responses are read for a keyword/response pair Each test also tests if
     * the right number of rows are read in, because they should be in pairs.
     */
    private static void testLoadResponseTable() {
        boolean error = false;

        { // Test 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals(expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));
            }

            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println(
                    "  expected an even number of elements in table but found: " + table.size());
            }
        }

        // Are the right number of keywords and responses read in for a keyword/response pair?
        { // Test 2.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            ArrayList<String> expected2ndRow = new ArrayList<String>();
            expected1stRow.add("computer");
            expected2ndRow.add("Do computers worry you?");
            expected2ndRow.add("Why do you mention computers?");
            expected2ndRow.add("What do you think machines have to do with your problem?");
            expected2ndRow.add("Don't you think computers can help people?");
            expected2ndRow.add("What about machines worries you?");
            expected2ndRow.add("What do you think about machines?");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals(expected1stRow) && !table.get(1).equals(expected2ndRow)) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));
                System.out.println("  expected2ndRow: " + expected2ndRow);
                System.out.println("  table2ndRow: " + table.get(1));
            }

            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println(
                    "  expected an even number of elements in table but found: " + table.size());
            }
        }

        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }
    }

    /*
     * Supporting method for testInputAndResponse. Returns 1 if the test passed and 0 if the test
     * failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand,
        ArrayList<ArrayList<String>> table) {

        String[] words = Eliza.prepareInput(input);
        if (words == null) {
            if (expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }

        String response = Eliza.prepareResponse(words, rand, table);
        if (!response.equals(expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response + "'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        // since we are using a seed in the random number generator, the responses should be fixed
        // for the provided response table.
        Random randGen = new Random(923);
        ArrayList<ArrayList<String>> responseTable =
            Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.",
            "What do you think machines have to do with your problem?", randGen, responseTable);

        numPassed += checkResponse("What is your name?",
            "I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.",
            "What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
            randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?",
            randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.",
            "Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.",
            "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.",
            "Why do you think you apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.",
            "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.",
            "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
            "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.",
            "Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen,
            responseTable);
        numPassed += checkResponse("Are you a Packers fan?",
            "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
            randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.",
            "Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.",
            "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.",
            "How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);

        numPassed +=
            checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);

        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }
    }

}
