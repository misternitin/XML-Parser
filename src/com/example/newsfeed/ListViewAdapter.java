package com.example.newsfeed;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	Context context;
	ArrayList<HashMap<String, String>> data;
	HashMap<String, String> result;
	ImageLoader loader;
	LayoutInflater inflater;
	public ListViewAdapter(MainActivity mainActivity,
			ArrayList<HashMap<String, String>> arraylist) {
		// TODO Auto-generated constructor stub
		this.context=mainActivity;
		this.data=arraylist;
		Log.d("Nitin", "ArrayList" + data.toString());
		//loader=new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position , View converView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView title;
		TextView desc;
		//ImageView img;
		Log.d("Nitin","Dhinkachika");
		inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View itemView=inflater.inflate(R.layout.list_item, parent, false);
		result=data.get(position);
		Log.d("Nitin", "Result" + result.toString());
		title=(TextView)itemView.findViewById(R.id.text);
		title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		desc=(TextView)itemView.findViewById(R.id.desc);
		//img=(ImageView)itemView.findViewById(R.id.image);
		title.setText(result.get(MainActivity.TITLE));
		desc.setText(result.get(MainActivity.DESCRIPTION));
		Log.d("Nitin", "title "+result.get(MainActivity.TITLE));
		//loader.DisplayImage(result.get(MainActivity.IMAGE), img);
		
		return itemView;
	}

}
