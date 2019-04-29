package com.example.foodroulette;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestTask implements Runnable{

    private static final String TAG = RequestTask.class.getSimpleName();
    private String API_KEY = "AIzaSyBO2mCHBeTBZn5XRtMI6e5Ua0pKk_zdGog";
    private String keywords;
    private ArrayList<Restaurant> items;
    private double[] latlng;
    private int radius;

    public RequestTask(String keywords, double[] latlng, int radius, ArrayList<Restaurant> items){
        this.keywords = keywords;
        this.latlng = latlng;
        this.radius = radius;
        this.items = items;
    }


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }


    @Override
    public void run() {
        try {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                    "location="+latlng[0]+","+latlng[1]+"&" +
                    "radius="+radius+"&" +
                    "type=restaurant&" +
                    "keyword="+keywords+"&" +
                    "key="+API_KEY;
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (Throwable t) {
            Log.e(TAG, "Error in DownloadTask", t);
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private void parseItems(List<Restaurant> items, JSONObject jsonBody)
            throws IOException, JSONException {
        JSONArray res = jsonBody.getJSONArray("results");
        for(int i = 0; i < res.length(); i++){
            Restaurant item = new Restaurant(
                    res.getJSONObject(i).getString("name"), res.getJSONObject(i).getJSONArray("photos").getJSONObject(0).getString("photo_reference"), 0);
            items.add(item);
            Log.v("Restaurant",res.getJSONObject(i).getString("name"));
        }


    }
}
