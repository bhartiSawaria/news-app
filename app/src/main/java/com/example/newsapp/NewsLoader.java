package com.example.newsapp;

import java.net.URL;
import java.util.List;
import android.content.AsyncTaskLoader;
import android.content.Context;

public class NewsLoader extends AsyncTaskLoader<List<NewsReport>> {
    private URL mUrl;
    public NewsLoader(Context context, URL url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsReport> loadInBackground() {
        if( mUrl == null ){
            return null;
        }
        List<NewsReport> newsList = QueryUtil.getNewsData(mUrl);
        return newsList;
    }
}
