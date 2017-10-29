package com.example.android.booklist;

/**
 * Created by n3515756 on 9/26/2016.
 */

public class Book {

    private String[] author;
    private String title;

    public Book(String[] author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        if(author.length > 0)
        return author[0];
        else return "Author N/A";
    }

    public String getTitle() {
        return title;
    }
}
