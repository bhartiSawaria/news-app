package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter extends ArrayAdapter<NewsReport> {
    private TextView mNewsHeadline;
    private TextView mNewsDate;

    public NewsAdapter(@NonNull Context context, @NonNull List<NewsReport> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if( currentView == null ){
            currentView = LayoutInflater.from(getContext()).inflate( R.layout.specific_category_list_item, parent,false);
        }
        NewsReport currentNewsReport = getItem(position);

        mNewsHeadline = currentView.findViewById(R.id.newsHeadline);
        mNewsDate = currentView.findViewById(R.id.newsDate);

        mNewsHeadline.setText(currentNewsReport.getHeadline());
        mNewsDate.setText(currentNewsReport.getDate());

        return currentView;
    }
}
