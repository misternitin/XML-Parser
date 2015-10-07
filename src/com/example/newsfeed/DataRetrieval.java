package com.example.newsfeed;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;


public class DataRetrieval{

	//private static final String URL = "http://ibnlive.in.com/ibnrss/top.xml";

	
	public InputStream downloadUrl(String urlString, Context mContext) throws IOException {
	    //URL url = new URL("http://ibnlive.in.com/ibnrss/top.xml");
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(urlString);
	    HttpResponse response;
	    response = httpClient.execute(httpget);
	    int x = response.getStatusLine().getStatusCode();
	    
	    
	    /*if(entity != null){
	    	InputStream iStream = entity.getContent();
	    }*/
	   /* HttpClient conn = (HttpClient)url.openConnection();
	   // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setReadTimeout(10000  milliseconds );
	    conn.setConnectTimeout(15000  milliseconds );
	    conn.setRequestMethod("GET");
	    conn.setDoInput(true);
	    // Starts the query
	    conn.connect();
	    int response = conn.getResponseCode();*/
	    if(x == 200){
	    	 HttpEntity entity = response.getEntity();
	    	return entity.getContent();
	    	//Toast.makeText(mContext, "network not available", Toast.LENGTH_SHORT).show();
	    }
	    else{
	    	Log.i("tag"," network not not available");
	    	/*Toast.makeText(mContext, "network not available", Toast.LENGTH_SHORT).show(); */
	    	return null;
	    }
	    
	}
	
}




