///////////////////////////////////////////////////////////////////////////////
//Title: Book
//Semester: Fall 2020
//
//Author: Siddharth Aneja
//Email: saneja@wisc.edu
//CS Login: aneja@cs.wisc.edu
//Lecturer's Name: Deppeler
//Lecture Number: 002
//Description: The class that provides the implementation of the books for the hash table
//
////////////////////////////////////////////////////////////////////////////

import java.util.Objects;

public class Book {
  
    /**
     * Stores the ISBN number(key) of the book.
     */
    private String isbn13;
    
    /**
     * Stores the author of the book.
     */
    private String authors; 
    
    /**
     * Stores the original publication year of the book.
     */
    private String original_publication_year;
    
    /**
     * Stores the title of the book.
     */
    private String title; 
    
    /**
     * Stores the language code of the book.
     */
    private String language_code;
    
    /**
     * Stores the average rating of the book.
     */
    private String average_rating;
    
    /**
     * Stores the cover type of the book.
     */
    private String cover_type; 
    
    /**
     * Stores the pages of the book.
     */
    private String pages;
    
    /**
     * Contructor of the book.
     * @param isbn13 - Key of the book
     * @param authors - Author of the book
     * @param original_publication_year - publication year of the book
     * @param title - title of the book
     * @param language_code - language code of the book
     * @param average_rating - average rating of the book
     * @param cover_type - cover type of the book
     * @param pages - pages in the book
     */
    public Book(String isbn13, String authors, 
            String original_publication_year, String title,
            String language_code, String average_rating, 
            String cover_type, String pages){
        this.isbn13 = isbn13; 
        this.title = title;
        this.authors = authors; 
        this.original_publication_year = original_publication_year;
        this.language_code = language_code; 
        this.average_rating = average_rating;
        this.cover_type = cover_type; 
        this.pages = pages; 
    }

    /**
     * Get's the key if the specific book
     * @return the key (ISBN) of the book
     */
    public String getKey() {
        return this.isbn13;
    }
    
    /**
     * Set's the key to the given parameter key.
     * @param isbn13 -  the new key
     */
    public void setKey(String isbn13) {
        this.isbn13 = isbn13;
    }
    
    
    @Override
    public String toString() {
        return "ISBN13: "+this.isbn13+"; Book: "+ 
               this.title+", Author: "+this.authors+
               ", Original Publication Year: "+
               this.original_publication_year+
               ", Language: "+this.language_code+", Average Rating: "+
               this.average_rating+", Cover Type: "+this.cover_type+ 
               ", Pages: "+ this.pages;                
    }
}

