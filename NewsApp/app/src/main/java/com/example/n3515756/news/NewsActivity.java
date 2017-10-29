package com.example.n3515756.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int News_LOADER_ID = 1;
    private static String GUARDIAN_REQUEST_URL;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private String searchKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        final ListView newsListView = (ListView) findViewById(R.id.list);
        final EditText editText = (EditText) findViewById(R.id.search_edit_text);
        Button btn = (Button) findViewById(R.id.search_btn);
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey = editText.getText().toString();
                GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?q=" + searchKey + "&api-key=test&show-tags=contributor";
                newsListView.setEmptyView(mEmptyStateTextView);
                newsListView.setAdapter(mAdapter);
                ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    LoaderManager loaderManager = getLoaderManager();
                    if(loaderManager.getLoader(News_LOADER_ID) == null) {
                        loaderManager.initLoader(News_LOADER_ID, null, NewsActivity.this);
                    } else {
                        loaderManager.restartLoader(News_LOADER_ID, null, NewsActivity.this);
                    }
                } else {
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText("No internet connection");
                }
            }
        });
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText("No_News");
        mAdapter.clear();
        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
