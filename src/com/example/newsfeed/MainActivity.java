package com.example.newsfeed;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	public static final String ITEM = "item";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	
//	public static final String IMAGE = "flag";
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String url="http://www.thehindu.com/news/national/?service=rss";
		new DownloadXmlTask(MainActivity.this, url).execute();
	}

	public class DownloadXmlTask extends AsyncTask<Void, Void, Void> {
		private Context context;
		ProgressDialog progressDialog;
		ListView listView;
		ArrayList<HashMap<String, String>> arraylist;
		MainActivity activity;
		String url;

		public DownloadXmlTask(MainActivity mainActivity, String url) {
			this.context = mainActivity;
			this.activity = mainActivity;
			this.url=url;

		}

		private void loadXmlFromNetwork() throws XmlPullParserException,
				IOException {

			DataRetrieval objDate = new DataRetrieval();
			arraylist = new ArrayList<HashMap<String, String>>();
			InputStream iStream = null;
			GenericXmlParser xmlParse = new GenericXmlParser();
			try {
				iStream = objDate
						.downloadUrl(url
								,
								context);
				// Log.d("Nitin","iStream:    "+iStream.toString());
				if (iStream != null)
					arraylist = xmlParse.parse(iStream);
			} finally {
				if (iStream != null)
					iStream.close();
			}

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub

			try {
				ConnectivityManager connMgr = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected())
					loadXmlFromNetwork();
				else {
					/*
					 * Intent myIntent = new Intent(Intent.action);
					 * mContext.sendBroadcast(Intent.action);
					 */
					Toast.makeText(context.getApplicationContext(),
							"Network Not available", Toast.LENGTH_SHORT).show();
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			listView = (ListView) findViewById(R.id.listView);
			//Log.d("Nitin", "ArrayList" + arraylist.toString());
			ListViewAdapter adapter = new ListViewAdapter(activity, arraylist);

			listView.setAdapter(adapter);

			progressDialog.dismiss();

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Loading");
			progressDialog.show();

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Select your category");
		builder.setSingleChoiceItems(NewsURL.newsItem, -1 , new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				new DownloadXmlTask(MainActivity.this,NewsURL.newsList[which]).execute();
				dialog.dismiss();
				
			}
		});
		 
		    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int whichButton) {
		        }
		    });

			 
		builder.show();
			  
		return super.onOptionsItemSelected(item);
		
	}

}
