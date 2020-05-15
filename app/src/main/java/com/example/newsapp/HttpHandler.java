package com.example.newsapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private static String TAG = HttpHandler.class.getSimpleName();
    private static HttpURLConnection mConnection;
    private static InputStream mInputStream;

    private HttpHandler(){};

    public static String makeHttpRequest(URL url) throws IOException{
        String jsonString = null;
        try {
            if (url == null){
                return jsonString;
            }
            mConnection = (HttpURLConnection) url.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setReadTimeout(10000);
            mConnection.setConnectTimeout(15000);
            mInputStream = mConnection.getInputStream();
            jsonString = convertStreamToString(mInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (mConnection != null){
                mConnection.disconnect();
            }
            if (mInputStream != null){
                mInputStream.close();
            }
        }
        return jsonString;
    }

    private static String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            line = bufferedReader.readLine();
            while (line != null){
                result.append(line);
                result.append("\n");
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if( inputStream != null ){
                inputStream.close();
            }
        }
        return result.toString();
    }
}
