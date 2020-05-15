package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtil {
    private static String mJsonString;
    private static JSONObject mJsonObject1;
    private static JSONObject mJsonObject2;
    private static JSONArray mJsonArray;
    private static JSONObject mJsonSingleNewsObject;
    private static String mHeadline;
    private static String mDate;
    private static String mWebUrl;

    //no instances of this class will be created so the constructor is declared private
    private QueryUtil(){

    }

    public static List<NewsReport> getNewsData(URL url){
        List<NewsReport> newsList = new ArrayList<>();
        try {
            mJsonString = HttpHandler.makeHttpRequest(url);
            mJsonObject1 = new JSONObject(mJsonString);
            JSONObject mJsonObject2 = mJsonObject1.getJSONObject("response");
            mJsonArray = mJsonObject2.getJSONArray("results");

            for( int i=0; i<mJsonArray.length(); i++ ) {
                mJsonSingleNewsObject = mJsonArray.getJSONObject(i);
                mHeadline = mJsonSingleNewsObject.getString("webTitle");
                mDate = mJsonSingleNewsObject.getString("webPublicationDate");
                mWebUrl = mJsonSingleNewsObject.getString("webUrl");

                newsList.add(new NewsReport(mHeadline, mDate, mWebUrl));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
        return newsList;
    }
}
