package com.example.newsfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class GenericXmlParser {

	private static final String ns = null;
static Integer count=0;
String tagname;
	public ArrayList<HashMap<String, String>> parse(InputStream in)
			throws XmlPullParserException, IOException {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(in, null);
			return readFeed(xpp, MainActivity.ITEM);
			// return null;
		} finally {
			in.close();
		}
	}

	private ArrayList<HashMap<String, String>> readFeed(XmlPullParser parser,
			String item) throws XmlPullParserException, IOException {
		
		ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String, String>>();
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}

			String name = parser.getName();
			Log.d("tag", name);

			if (name.equals(item)) {
				count++;
				Log.d("Nitin", "Count "+count);
				entries.add(readEntry(parser));
				// return entries;
				
			} else {
				// skip(parser);
			}
		}
		if (entries.size() != 0 && entries != null) {
			return entries;
		} else
			return null;
	}

	private HashMap<String, String> readEntry(XmlPullParser parser)
			throws XmlPullParserException, IOException {

		HashMap<String, String> data = null;
		String text="";
//		while (parser.next() != XmlPullParser.END_TAG) {
//			Log.d("Nitin","TAG1 "+parser.getName());
//			if (parser.getEventType() != XmlPullParser.START_TAG) {
//				
//				continue;
//			}
//			String name = parser.getName();
//			Log.d("Nitin","TAG2 "+parser.getName());
//			if (name.equals(MainActivity.TITLE)) {
//				data.put(name, readText(parser, name));
//				
//				// obj.setMtitle(readText(parser,name));
//			} else if (name.equals(MainActivity.DESCRIPTION)) {
//				data.put(name, readText(parser, name));
//				//Log.d("Nitin","Data: "+name+" DESC: "+readText(parser, name));
//				// obj.setMdescription(readText(parser, name));
//			}
//			
//		}
//		
//		return data;
		int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
             tagname = parser.getName();

            switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tagname.equalsIgnoreCase("item")) {

                data = new HashMap<String, String>();
                }
                break;

            case XmlPullParser.TEXT:
                //grab the current text so we can use it in END_TAG event
                
                text=parser.getText();

                break;

            case XmlPullParser.END_TAG:

                if (tagname.equalsIgnoreCase("title")) {
                    // if </site> then we are done with current Site
                    // add it to the list.
                    data.put(tagname, text);
//                  Toast.makeText(ctx, curproduct+"",3000).show();

                } else if (tagname.equalsIgnoreCase("description")) {
                    // if </name> use setName() on curSite
                	data.put(tagname, text);

                }  else if (tagname.equalsIgnoreCase("item")) {
                    // if </name> use setName() on curSite
                	return data;

                }  

                break;

            default:
                break;
            }
            //move on to next iteration
            eventType = parser.next();
        }
		return data;
			
			
	}

	private String readText(XmlPullParser parser, String name)
			throws XmlPullParserException, IOException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			Log.d("Nitin","Data: "+name+" DESC: "+result);
			parser.nextTag();
		}
		return result;
	}

}
