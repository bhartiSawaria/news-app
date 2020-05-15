package com.example.newsapp;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewsCategory extends AppCompatActivity implements LoaderCallbacks<List<NewsReport>> {
    private static String mCategoryName;
    private static String mStringUrl;
    private static URL mUrl;
    private static String API_KEY = "baed4fd9-9968-4375-8893-fa809341ca36";
    private ArrayAdapter<NewsReport> mNewsAdapter;
    private ListView mSingleCategoryRootview;
    private LoaderManager loaderManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the information from the intent
        Intent intent = getIntent();
        mCategoryName = intent.getStringExtra("CategoryName");

        setContentView(R.layout.specific_category);
        setTitle(mCategoryName);

        mStringUrl = "https://content.guardianapis.com/search?page-size=15&q=" + mCategoryName + "&api-key=" + API_KEY;
        mUrl = convertStringToUrl(mStringUrl);

        mNewsAdapter = new NewsAdapter(this, new ArrayList<NewsReport>());
        mSingleCategoryRootview = findViewById(R.id.singleCategoryRootview);
        mSingleCategoryRootview.setAdapter(mNewsAdapter);

        mSingleCategoryRootview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsReport currentReport = (NewsReport) parent.getItemAtPosition(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentReport.getWebUrl()));
                startActivity(browserIntent);
            }
        });

        loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<NewsReport>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsReport>> loader, List<NewsReport> data) {
        mNewsAdapter.clear();
        if( data != null && ! data.isEmpty()){
            mNewsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsReport>> loader) {
        mNewsAdapter.clear();
    }

    private URL convertStringToUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
