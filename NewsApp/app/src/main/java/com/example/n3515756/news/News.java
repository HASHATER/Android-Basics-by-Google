package com.example.n3515756.news;

import java.util.ArrayList;

/**
 * Created by n3515756 on 10/16/2016.
 */

public class News {
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mAuthor;

    public News(String title, String mDate, String mUrl, String author) {
        this.mTitle = title;
        this.mDate = mDate;
        this.mUrl = mUrl;
        this.mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getmDate() {
        return mDate;
    }
    public String getmUrl() {
        return mUrl;
    }
    public String getAuthor() {
        if(mAuthor !=null)
            return mAuthor;
        else return "Author N/A";
    }
}
