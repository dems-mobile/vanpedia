package com.demsmobile.vanpedia;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.demsmobile.vanpedia.data.Channel;
import com.demsmobile.vanpedia.places.JSONfunctions;
import com.demsmobile.vanpedia.places.SearchSingle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchPlacesList extends ListActivity{


    protected  String s=null;
    String reference[]=new String[20];
    String keyWord = "restaurant";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_list);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        // if we want within 500meters --> change location to location=-33.8670522,151.1957362&radius=500
        String url="https://maps.googleapis.com/maps/api/place/search/json?location=41.8584,-87.6307&radius=5000&types="+keyWord+"&sensor=false&key=AIzaSyAY5H67_Nuvb40ISHt21LGHGN60SJcXN4c";
        JSONObject json = JSONfunctions.getJSONfromURL(url);

        try{

            JSONArray searchResult = json.getJSONArray("results");

            for(int i=1;i< searchResult.length();i++){
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject e = searchResult.getJSONObject(i);
                map.put("id",  String.valueOf(i));
                map.put("name", "Name:" + e.getString("name"));
                map.put("magnitude", "Rating: " +  e.getString("rating"));
                map.put("vicinity", "Vicinity: "+ e.getString("vicinity"));
                reference[i]=e.getString("reference");
                mylist.add(map);

            }
        }catch(JSONException e)        {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.httpex,
                new String[] { "name", "magnitude","vicinity" },
                new int[] { R.id.item_title, R.id.item_subtitle,R.id.item_subtitle2 });

        setListAdapter(adapter);

        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                @SuppressWarnings("unchecked")
                HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
                Toast.makeText(SearchPlacesList.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_SHORT).show();
                s=reference[position];
                startActivity(new Intent(SearchPlacesList.this, PlaceDetails.class));
                SearchSingle.getInstance().setter(s);
            }
        });
    }

}