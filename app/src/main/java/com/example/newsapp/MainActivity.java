package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView categoryItemRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the references
        categoryItemRootView = findViewById(R.id.allCategoryRootview);

        //list containing different categories of news
        List<String> categoryList = new ArrayList<>();
        categoryList.add("National");
        categoryList.add("Politics");
        categoryList.add("Sports");
        categoryList.add("Business");
        categoryList.add("Editorials");
        categoryList.add("International");
        categoryList.add("Social");
        categoryList.add("Tech");
        categoryList.add("Bollywood");

        //initializing default arrayAdapter for displaying different news categories
        ArrayAdapter<String> categoryListAdapter = new ArrayAdapter<String>(this, R.layout.category_list_item, categoryList);

        //setting the adapter to the  category ListView
        categoryItemRootView.setAdapter(categoryListAdapter);

        //setting onItemClickListener on each news category
        categoryItemRootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView currentView = (TextView) view;
                String categoryName = currentView.getText().toString();
                Intent intent = new Intent(MainActivity.this, NewsCategory.class);

                //put category name as extra field so that later it can be used to set the title of new activity and also populate the news of that category only
                intent.putExtra("CategoryName", categoryName);
                startActivity(intent);
            }
        });
    }
}
