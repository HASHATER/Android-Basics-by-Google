/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.booklist;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    public static final String LOG_TAG = BookListActivity.class.getName();
    Context context;
    TextView defaultTextView;
    private String searchKey = "";
    private String BOOKS_REQUEST_URL;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        context = getApplicationContext();
        defaultTextView = (TextView) findViewById(R.id.default_text_view);
        final EditText editText = (EditText) findViewById(R.id.search_edit_text);
        Button btn = (Button) findViewById(R.id.search_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey = editText.getText().toString();
                BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
                BOOKS_REQUEST_URL += searchKey;

                if (isNetworkAvailable()) {
                    new BookAsyncTask().execute();
                } else
                    Toast.makeText(context, "There is no internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first book in the response.
     */
    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Book>> {
        ProgressDialog progDailog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog = new ProgressDialog(BookListActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }

        protected ArrayList<Book> doInBackground(URL... urls) {
            // Create URL object

            URL url = createUrl(BOOKS_REQUEST_URL.replaceAll("\\s", ""));
            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Book> books = extractFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            ListView bookListView = (ListView) findViewById(R.id.list);
            if (books.size() == 0) {
                defaultTextView.setVisibility(View.VISIBLE);
                progDailog.dismiss();
                bookListView.setVisibility(View.INVISIBLE);
                return;
            }
            bookListView.setVisibility(View.VISIBLE);
            defaultTextView.setVisibility(View.GONE);

            // Create a new {@link ArrayAdapter} of books
            BookAdapter adapter = new BookAdapter(BookListActivity.this, books);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            bookListView.setAdapter(adapter);
            progDailog.dismiss();
        }

        private URL createUrl(String stringUrl) {
            URL url = null;

            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error with creating URL", e);
                return null;
            }

            return url;
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        private String makeHttpRequest(URL url) throws IOException {

            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // convert the responce into a string
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Return a {@link Book} object by parsing out information
         * about the first book from the input BookJSON string.
         */
        private ArrayList<Book> extractFromJson(String BookJSON) {
            final ArrayList<Book> books = new ArrayList<Book>();
            try {
                JSONObject baseJsonResponse = new JSONObject(BookJSON);
                JSONArray itemsArray = baseJsonResponse.getJSONArray("items");


                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject currentBook = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                    String title = volumeInfo.getString("title");

                    JSONArray authorsJSONArray = volumeInfo.getJSONArray("authors");
                    String[] authorsArray = new String[authorsJSONArray.length()];
                    for (int j = 0; j < authorsJSONArray.length(); j++) {
                        authorsArray[j] = authorsJSONArray.getString(j);
                    }
                    books.add(new Book(authorsArray, title));
                    // Create a new {@link Book} object
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
            }

            return books;

        }
    }

}