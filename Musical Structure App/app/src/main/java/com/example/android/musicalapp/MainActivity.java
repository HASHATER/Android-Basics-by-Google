package com.example.android.musicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = (Button) findViewById(R.id.search);
        if (searchButton != null) {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(searchIntent);
                }
            });
        }

        Button helpButton = (Button) findViewById(R.id.help);
        if (helpButton != null) {
            helpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent helpIntent = new Intent(MainActivity.this, HelpActivity.class);
                    startActivity(helpIntent);
                }
            });
        }

        Button myLibraryButton = (Button) findViewById(R.id.my_library);
        if (myLibraryButton != null) {
            myLibraryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myLibrayIntent = new Intent(MainActivity.this, MyLibraryActivity.class);
                    startActivity(myLibrayIntent);
                }
            });
        }

        Button nowPlayingButton = (Button) findViewById(R.id.now_playing);
        if (nowPlayingButton != null) {
            nowPlayingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nowPlayingIntent = new Intent(MainActivity.this, NowPlayingActivity.class);
                    startActivity(nowPlayingIntent);
                }
            });
        }
    }
}
