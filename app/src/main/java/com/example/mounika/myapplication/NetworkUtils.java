package com.example.mounika.myapplication;

/**
 * Created by Mounika on 7/1/2017.
 */

import android.net.Uri;
import android.util.Log;

import com.example.mounika.myapplication.model.Newslist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class NetworkUtils {
    public static final String TAG = "NetworkUtils";
    public static final String BASE_URL = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=c51e779ea31f43b6accb7ccb61bf2766";
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_SORT = "sort";

    public static URL makeURL(String searchQuery, String sortBY) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBY).build();

        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "url:" + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {

            InputStream giveninput = urlConnection.getInputStream();
            Scanner input = new Scanner(giveninput);
            input.useDelimiter("\\A");
            boolean hasInput = input.hasNext();
            if (hasInput) {
                return input.next();
            } else {
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }
    public static ArrayList<Newslist> parseJSON(String json) throws JSONException {
        ArrayList<Newslist> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray items = main.getJSONArray("articles");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String author = item.getString("author");
            String description = item.getString("description");
            String date = item.getString("publishedAt");
            String url = item.getString("url");
            Newslist repo = new Newslist(author, description,date, url);
            result.add(repo);
        }
        return result;
    }






}
