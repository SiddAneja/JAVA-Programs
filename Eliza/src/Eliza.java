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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that collects user input
 * and responds appropriately. Eliza is based off of a computer program written at MIT in the 1960's
 * by Joseph Weizenbaum. Eliza uses keyword matching to respond to users in a way that displays
 * interest in the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /*
     * This method does input and output with the user. It calls supporting methods to read and
     * write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        String therapistName = ""; // initializes the therapists name
        String preparedResponse = ""; // initialize the therapist's response
        ArrayList<String> dialog = new ArrayList<String>(); // initializes the dialog arraylist

        if (args.length == 0) { // if no arguments, therapist is Eliza
            therapistName = "Eliza";
        } else if (args.length == 1) { // if one argument, therapist is that name
            therapistName = args[0];
        } else { // if more than one arguments, it asks the use for a choice
            System.out.print("Would you like to speak with");
            for (int i = 0; i < args.length; i++) {
                if (i != args.length - 1) {
                    System.out.print(" " + args[i] + ",");
                } else {
                    System.out.print(" or " + args[i] + "?");
                }
            }
            therapistName = sc.next();
        }
        // use loadResponseTable and the therapist name to generate a response table
        ArrayList<ArrayList<String>> responseTable =
            loadResponseTable(therapistName + Config.RESPONSE_FILE_EXTENSION);

        // name prompt
        System.out.println("Hi I'm " + therapistName + ", what is your name?");
        String userName = sc.nextLine();
        dialog.add("Hi I'm " + therapistName + ", what is your name?");

        // welcome prompt
        System.out.println("Nice to meet you " + userName + ". What is on your mind?");
        dialog.add("Nice to meet you " + userName + ". What is on your mind?");

        // begin conversation loop
        do {
            // obtain user input
            String userInput = sc.nextLine();
            dialog.add(userInput);
            // prepareInput
            String[] preparedInput = prepareInput(userInput);
            // if no quit words then prepareResponse
            if (preparedInput != null) {
                preparedResponse = prepareResponse(preparedInput, rand, responseTable);
                System.out.println(preparedResponse);
                dialog.add(preparedResponse);
            }
            // end loop if quit word
            else {
                break;
            }
        } while (true);
        // ending prompt
        System.out.println("Goodbye " + userName);
        dialog.add("Goodbye " + userName);

        String checkSave = ""; // initialize a string that stores the users answer if then want to
                               // save or not
        do { // starts loop to prompt user to save dialog
            System.out.print("Would you like to have a record of our conversation (y/n): ");
            checkSave = sc.next().substring(0, 1);
            // if the user enters y or n(with or without caps), break out of the prompt loop
            if (checkSave.equalsIgnoreCase("y") || checkSave.equalsIgnoreCase("n")) {
                break;
            }
        } while (true);
        // if the user entered y, enter branch
        if (checkSave.equalsIgnoreCase("y")) {
            // prompt for file name
            System.out.print("Enter filename: ");
            String saveFileName = sc.next();
            // start try block to save file
            try {
                // use saveDialog, if failed it will throw IOException
                saveDialog(dialog, saveFileName);
                // if successful, print :
                System.out.println(
                    "Thanks again for talking! Our conversation is saved in: " + saveFileName);
            } catch (IOException e) { // if fails, prints:
                System.out.println("Unable to save conversation to: " + saveFileName);
            }
        }
        sc.close();

    }

    /**
     * This method processes the user input, returning an ArrayList containing Strings, where each
     * String is a phrase from the user's input. This is done by removing leading and trailing
     * whitespace, making the user's input all lower case, then going through each character of the
     * user's input. When going through each character this keeps all digits, alphabetic characters
     * and ' (single quote). The characters ? ! , . signal the end of a phrase, and possibly the
     * beginning of the next phrase, but are not included in the result. All other characters such
     * as ( ) - " ] etc. should be replaced with a space. This method makes sure that every phrase
     * has some visible characters but no leading or trailing whitespace and only a single space
     * between words of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and phrases with
     * just invalid/whitespace characters should NOT be added to the list.
     * 
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i am", "a big fun
     * robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {
        int i; // initialize a variable for the loops
        ArrayList<String> returnPhrases = new ArrayList<String>(); // create an ArrayList to return
        userInput = userInput.trim().toLowerCase(); // converts the input into lowercase and trims
                                                    // it
        if (userInput == null) { // if the input is null it returns null
            return null;
        }
        if (userInput.length() == 0) { // if the input has no characters it returns a 0 length list
            return new ArrayList<String>(0);
        }
        String phrase = ""; // initializes the String phrase to be added to the list
        // a for loop to iterate through each character in the input string
        for (i = 0; i < userInput.length(); i++) {
            // if the is a digit, a letter or \ then it is added as such to the phrase
            if (Character.isDigit(userInput.charAt(i)) || Character.isLetter(userInput.charAt(i))
                || userInput.charAt(i) == '\'') {
                phrase += userInput.charAt(i);
                if (i == userInput.length() - 1) { // if the reaches the last letter and there is no
                                                   // character this branch executes
                    returnPhrases.add(phrase);
                    break;
                }
                continue;
            }
            // if there are multiple whitespaces then only one is added to the phrase
            if (userInput.charAt(i - 1) == ' ' && userInput.charAt(i) == ' ') {
                continue;
            }
            // if the character is one of the cases then the phrase is ended and a new one is
            // started
            else {
                switch (userInput.charAt(i)) {
                    case '?':
                    case '!':
                    case ',':
                    case '.':
                        break;
                    default: // for any other character a space is added to the phrase
                        if (phrase.length() > 0) { // avoids multiple whitespaces in the phrase
                            if (!(phrase.charAt(phrase.length() - 1) == ' ')) {
                                phrase += " ";
                            }
                        }
                        continue;
                }
            }
            phrase = phrase.trim(); // removes the trailing and leading spaces
            if (phrase.length() == 0) { // if the length of the phrases is 0 then it is not added to
                                        // the list
                phrase = "";
                continue;
            }
            returnPhrases.add(phrase);
            phrase = ""; // resets the phrase
        }
        return returnPhrases;
    }

    /**
     * Checks whether any of the phrases in the parameter match a quit word from Config.QUIT_WORDS.
     * Note: complete phrases are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        for (int i = 0; i < phrases.size(); i++) { // for loop to iterate through all phrases
            for (int j = 0; j < Config.QUIT_WORDS.length; j++) { // for loop to iterate through all
                                                                 // quitwords
                if (phrases.get(i).equals(Config.QUIT_WORDS[j])) { // if any phrases match a quit
                                                                   // word, return true
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding the longest phrase to which to
     * respond. If two phrases are the same length, returns whichever has the lower index in the
     * list. If phrases parameter is null or size 0 then return "" (Update 11/15/18).
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {
        if (phrases == null || phrases.size() == 0) { // if phrases is null or empty, return a blank
                                                      // string
            return "";
        }
        String longestPhrase = phrases.get(0); // sets longest phrase equal to index 0
        for (int i = 1; i < phrases.size(); i++) { // iterates through phrases
            if (longestPhrase.length() < phrases.get(i).length()) { // if length of longest phrase
                                                                    // is less than the phrase at i
                                                                    // make longest phrase equal to
                                                                    // phrase at i
                longestPhrase = phrases.get(i);
            }
        }
        return longestPhrase;
    }

    /**
     * Looks for a replacement word for the word parameter and if found, returns the replacement
     * word. Otherwise if the word parameter is not found then the word parameter itself is
     * returned. The wordMap parameter contains rows of match and replacement strings. On a row, the
     * element at the 0 index is the word to match and if it matches return the string at index 1 in
     * the same row. Some example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     * 
     * If word is null return null. If wordMap is null or wordMap length is 0 simply return word
     * parameter. For this implementation it is reasonable to assume that if wordMap length is >= 1
     * then the number of elements in each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap otherwise the
     *         word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        String replacementString = ""; // initialize replacement string
        if (word == null) { // if word is null, return null
            return null;
        }
        if (wordMap == null || wordMap.length == 0) { // if wordMap is null or empty, return null
            return word;
        }
        for (int i = 0; i < wordMap.length; i++) { // iterate through the wordMap
            if (word.equals(wordMap[i][0])) { // if the phrase at index 0 at index i, is equal to a
                                              // word from the wordMap
                                              // replacement string is equal to index 1 at index i
                replacementString = wordMap[i][1];
                break;
            }
        }
        if (replacementString.length() != 0) { // if the replacement string is not empty, return it
            return replacementString;
        } else { // else return the word parameter
            return word;
        }
    }

    /**
     * Concatenates the elements in words parameter into a string with a single space between each
     * array element. Does not change any of the strings in the words array. There are no leading or
     * trailing spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String returnString = ""; // initializes the phrase to be returned
        for (int i = 0; i < words.length; i++) { // iterates through the words array and keeps
                                                 // adding the string
                                                 // and every index to the initialized string
            returnString += words[i] + " ";
        }
        returnString = returnString.trim(); // remove leading and trailing whitespaces
        return returnString;
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord parameter. A
     * word at a time from phrase parameter is looked for in wordMap which may result in more than
     * one word. For example: i'm => i am Uses the replaceWord and assemblePhrase methods. Example
     * wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will itself be a key to
     * another entry. In other words, only one pass through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {
        if (wordMap == null) { // if wordMap is null, return phrase parameter
            return phrase;
        }
        String[] phraseArray = phrase.split(" "); // makes a new array where each index is a word
                                                  // from the initial phrase
        for (int i = 0; i < phraseArray.length; i++) { // iterates through every word in the new
                                                       // array and uses replaceWord method with
                                                       // wordMap
            phraseArray[i] = replaceWord(phraseArray[i], wordMap);
        }
        phrase = assemblePhrase(phraseArray); // phrase is then equal to the string formed from the
                                              // new array
        return phrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using separatePhrases).
     * If a phrase is a quit word (foundQuitWord) then return null. Otherwise, select a phrase
     * (selectPhrase), swap input words (swapWords with Config.INPUT_WORD_MAP) and return an array
     * with each word its own element in the array.
     * 
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        ArrayList<String> returnPhrases = separatePhrases(input); // creates an arraylist of the
                                                                  // phrases using separatePhrases
                                                                  // method
        if (foundQuitWord(returnPhrases)) { // foundQuitWord returns true if the phrases in the
                                            // array list has a quit word
            return null; // return null if quit word is found
        }
        String selectedPhrase = selectPhrase(returnPhrases); // if no quit word, use selectPhrase
                                                             // method to select a phrase from the
                                                             // arraylist
        selectedPhrase = swapWords(selectedPhrase, Config.INPUT_WORD_MAP); // use swapWords on the
                                                                           // selected phrase with
                                                                           // Config.INPUT_WORD_MAP
        String[] returnArray = selectedPhrase.split(" "); // use spilt to form an array of the
                                                          // selected phrase
        return returnArray;
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line is
     * ignored. A keyword line begins with "keywords" with all the following tokens on the line, the
     * keywords. Each line that follows a keyword line that is not blank is a possible response for
     * the keywords. For example (the numbers are for our description purposes here and are not in
     * the file):
     * 
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers? 4 5 keywords i
     * dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while you were awake? 8 9 Have you ever
     * dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses on lines 2 and 3.
     * Line 4 and 8 are ignored since they are blank (contain only whitespace). Line 5 begins new
     * keywords that are the words "i" and "dreamed". This keyword list is followed by three
     * possible responses on lines 6, 7 and 9.
     * 
     * The keywords and associated responses are each stored in their own ArrayList. The response
     * table is an ArrayList of the keyword and responses lists. For every keywords list there is an
     * associated response list. They are added in pairs into the list that is returned. There will
     * always be an even number of items in the returned list.
     * 
     * Note that in the event an IOException occurs when trying to read the file then an error
     * message "Error reading <fileName>", where <fileName> is the parameter, is printed and a
     * non-null reference is returned, which may or may not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        ArrayList<ArrayList<String>> returnArrayList = new ArrayList<ArrayList<String>>(); // initializes
                                                                                           // the
                                                                                           // response
                                                                                           // table

        File file = new File(fileName); // calls in the File and assigns it to the parameter - file
        int i; // initializes a loop variable i
        // start of try block
        try {
            Scanner sc = new Scanner(file); // initializes scanner for the file
            String checkNewKeyword = sc.next(); // creates a variable that scans for the word
                                                // "keywords"
            // start of loop to create table
            while (true) {
                ArrayList<String> keywordsArrayList = new ArrayList<String>(); // initializes the
                                                                               // keyword arraylist
                                                                               // which is added to
                                                                               // the response table
                ArrayList<String> responsesArrayList = new ArrayList<String>();// initializes the
                                                                               // response arraylist
                                                                               // which is also
                                                                               // added to the
                                                                               // response table

                // if there are words in the file, enter branch

                if (sc.hasNext()) {
                    // loops as long as the variable checkNewKeyword does not equal "keywords"
                    // breaks out when checkNewKeyword is equal to keywords
                    while (!checkNewKeyword.equals("keywords")) {
                        checkNewKeyword = sc.next();
                        continue;
                    }
                    String keyWords = sc.nextLine(); // takes the entire phrase after
                                                     // checkNewKeyword
                    String[] keywordArray = keyWords.trim().split(" "); // breaks up the phrase into
                                                                        // separate words and puts
                                                                        // it into an array
                    for (i = 0; i < keywordArray.length; i++) { // iterates through the keywordArray
                                                                // and adds every word to the
                                                                // keyword arraylist
                        keywordsArrayList.add(keywordArray[i]);
                    }
                    checkNewKeyword = sc.next(); // checks for string "keyWord again"
                    // loops as long as checkNewKeyword is not keyword and more lines exist after it
                    while (!checkNewKeyword.equals("keywords") && sc.hasNext()) {
                        String responses = checkNewKeyword + sc.nextLine(); // String response is a
                                                                            // response that follows
                                                                            // the keywords in the
                                                                            // file
                        responsesArrayList.add(responses); // adds the response to the
                                                           // responseArrayList
                        if (sc.hasNext()) { // if another line after the response line exists
                            checkNewKeyword = sc.next(); // scan for the word Keywords again
                            continue; // repeat adding responses
                        }
                    }
                    returnArrayList.add(keywordsArrayList);// add the keywords arraylist to response
                                                           // table
                    returnArrayList.add(responsesArrayList);// add the response arraylist to
                                                            // response table
                    continue;
                }
                // break out of the while loop once you reach the end of the file
                else {
                    break;
                }
            }
            sc.close();
        }
        // end of try block
        // catch block
        catch (IOException e) {
            System.out.println("Error reading " + fileName);
            return returnArrayList;
        }
        return returnArrayList;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords
     * match then this method returns an array with the unmatched words before, between and after
     * the keywords. If the keywords do not match then null is returned.
     * 
     * When the phrase contains elements before, between, and after the keywords, each set of the
     * three is returned in its own element String[] keywords = {"i", "dreamed"}; String[] phrase =
     * {"do", "you", "know", that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
     * 
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being an astronaut"
     * 
     * In an example where there is a single keyword, the resulting List's first element will be the
     * the pre-sequence element and the second element will be everything after the keyword, in the
     * phrase String[] keywords = {"always"}; String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I" toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"}; String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     *         are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        String[] returnPhrases = new String[keywords.size() + 1]; // initialize an array
        String unmatchedPhrases = ""; // initialize a string
        // initialize variable used for looping
        int i = 0;
        int j = 0;
        int k;
        int index = -1; // index of each keyword while looping through a loop

        // a for loop to check if the phrase contains all the keywords and they are in order
        for (k = 0; k < keywords.size(); k++) { // iterate through keywords
            for (int z = 0; z <= phrase.length; z++) { // iterate through phrase
                if (z == phrase.length) { // if z reaches the end of the phrase without finding the
                                          // keyword, return null
                    return null;
                }
                if (!phrase[z].equalsIgnoreCase(keywords.get(k))) { // if the phrase at z is not
                                                                    // equal to the keyword, keep
                                                                    // looping
                    continue;
                }
                if (phrase[z].equalsIgnoreCase(keywords.get(k))) { // if a keyword is found in
                                                                   // phrase, enter branch
                    if (index != -1 && index > z) {// if the index of the keyword(z) is less than
                                                   // the index of the previous keyword
                                                   // that is, the keywords are not in order, return
                                                   // null. Index is initially -1
                        return null;
                    }
                    index = z; // set index equal to index of keyword
                    break; // breaks out of the phrases loop and starts it again for the next
                           // keyword
                }
            }
        }
        index = 0; // makes index 0 again
        // start a while loop to create the phrase to be returned
        while (i < phrase.length && j <= keywords.size()) {
            // if the phrase at index i does not equal a keyword, or we reach the end of the
            // keywords arraylist(j), enter branch
            if (j == keywords.size() || !phrase[i].equalsIgnoreCase(keywords.get(j))) {
                // add the phrase at i to the string
                unmatchedPhrases = unmatchedPhrases + " " + phrase[i];
                unmatchedPhrases = unmatchedPhrases.trim();

                if (i == phrase.length - 1) {// if you reach the end of phrases, add the unmatched
                                             // phrase to index "index" of returnPhrases
                                             // otherwise increment i and continue
                    returnPhrases[index] = unmatchedPhrases;
                }
                i++;
                continue;
            }
            // if we find a keyword, enter branch
            else {
                returnPhrases[index] = unmatchedPhrases;// add unmatched phrase to returnPhrases
                unmatchedPhrases = ""; // make unmatchedPhrases empty again
                i++;// increment i
                j++;// increment j
                index++; // increment index
            }
        }
        // end of loop
        // for loop to replace any null values in the returnPhrases with empty strings
        for (k = 0; k < returnPhrases.length; k++) {
            if (returnPhrases[k] == null) {
                returnPhrases[k] = "";
            }
        }

        return returnPhrases;
    }

    /**
     * Selects a randomly generated response within the list of possible responses using the
     * provided random number generator where the number generated corresponds to the index of the
     * selected response. Use Random nextInt( responseList.size()) to generate the random number. If
     * responseList is null or 0 length then return null.
     * 
     * @param rand A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        if (responseList == null || responseList.size() == 0) {
            return null;
        }
        int randomNumber = rand.nextInt(responseList.size());
        String response = responseList.get(randomNumber);
        return response;
    }

    /**
     * This method takes processed user input and forms a response. This looks through the response
     * table in order checking to see if each keyword pattern matches the userWords. The first
     * matching keyword pattern found determines the list of responses to choose from. A keyword
     * pattern matches the userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See the
     * findKeyWordsInPhrase algorithm in the Eliza.pdf.
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned. Otherwise one of
     * possible responses for the matched keywords is selected with selectResponse method. The
     * response selected is checked for the replacement symbol <n> where n is 1 to the length of
     * unmatchedWords array returned by findKeyWordsInPhrase. For each replacement symbol the
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.) has its pronouns
     * swapped with swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol in
     * the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable A table containing a list of keywords and response pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand,
        ArrayList<ArrayList<String>> responseTable) {
        String response = ""; // initializes response
        int i; // loop variable
        int index = 0;
        String[] unmatchedWords = null; // unmatchedWords array returned by findKeyWordsInPhrase

        // Iterate through the response table.
        for (i = 0; i <= responseTable.size(); i = i + 2) {
            // if the end of the response table is reached and no keyword has been found, then
            // return Config.NO_MATCH_RESPONSE
            if (i == responseTable.size()) {
                return Config.NO_MATCH_RESPONSE;
            }
            ArrayList<String> keywords = responseTable.get(i);
            // loops and checks to see if the current keywords match the user's words using
            // findKeyWordsInPhrase.
            unmatchedWords = findKeyWordsInPhrase(keywords, userWords);
            // if unMatchedWords is not null then keywords have been found, else loop again
            if (unmatchedWords != null) {
                response = selectResponse(rand, responseTable.get(i + 1)); // using selectResponse,
                                                                           // and the corresponding
                                                                           // responses (i+1)
                                                                           // of the responseTable,
                                                                           // create a response
                index = i; // set index equal to index i
                break; // break out of the keyword checking loop
            }
        }
        unmatchedWords = findKeyWordsInPhrase(responseTable.get(index), userWords); // uses index to
                                                                                    // get the array
                                                                                    // of unmatched
                                                                                    // words

        String unmatchedWordsPhrase = ""; // initialize a new String used to swap the <n> in the
                                          // response selected
        // start loop to replace <n>
        while (true) {
            int findN = response.indexOf("<"); // returns index of <
            if (findN > -1) { // if it exists it will have a positive index, enter branch
                char n = response.charAt(findN + 1); // the char at the next index is the value of n
                int valueOfN = Character.getNumericValue(n); // get the int value of the n
                unmatchedWordsPhrase = unmatchedWords[valueOfN - 1]; // the phrase to swap <n> is at
                                                                     // index (n-1) of of the
                                                                     // unmatchedwords array
                unmatchedWordsPhrase = swapWords(unmatchedWordsPhrase, Config.PRONOUN_MAP); // use
                                                                                            // swapwords
                                                                                            // on
                                                                                            // the
                                                                                            // String
                                                                                            // with
                                                                                            // Config.PRONOUN_MAP
                response = response.replace(("<" + n + ">"), unmatchedWordsPhrase); // replace the
                                                                                    // <n> with the
                                                                                    // String
            } else { // if < does not exist, break out of the loop
                break;
            }
        }

        return response; // return the created response
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the tracked
     * conversation. Every line ends with a newline. Throws an IOException if a writing error
     * occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
        // start try block
        try {
            File file = new File(fileName); // initializes file
            PrintWriter pWriter = new PrintWriter(file);// initializes printWriter
            for (int i = 0; i < dialog.size(); i++) { // uses a for loop to print every line of the
                                                      // dialog on the file
                pWriter.write(dialog.get(i) + "\n");
            }
            pWriter.flush();
            pWriter.close();
        } catch (IOException e) { // throws IOException if fails
            throw e;
        }
    }
}
