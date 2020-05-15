package com.example.newsapp;

public class NewsReport {
    private String mHeadline;
    private String mDate;
    private String mWebUrl;
    public NewsReport(String headline, String date, String webUrl){
        mHeadline = headline;
        mDate = date;
        mWebUrl = webUrl;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public String getDate() {
        return mDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}
