package com.demsmobile.vanpedia;

import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.demsmobile.vanpedia.places.JSONfunctions;
import com.demsmobile.vanpedia.places.SearchSingle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Displays a list of relating places corresponding to the subcategory keyword when used hits a sub-category button
 * A onItemList() is created to listen for a click on the list...
 * The tap on the list willl trigger PlaceDetails() to start
 */


public class SearchPlacesList extends ListActivity{

    /**
     * possible solution --> replace the key= in the string URL to the server key credential -->AIzaSyACTV150tm2BH49XsFD91CX5dbbrMjnXns
     * The keyWord is hardcoded for now.... but will eventually be passed as an argument depending on the subcategory selection
     * */

    protected  String s=null;
    String reference[]=new String[20];
    String keyWord = "restaurant";

    /**
     * This would accept argument, but it doesn't work, I think it is out dated
     */
   // Bundle b = getIntent().getExtras();
   // String keyWord = b.getString("key");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_list);          //need to create this

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        //takes a key word
        // if we want within 500meters --> change location to location=-33.8670522,151.1957362&radius=500
        String url="https://maps.googleapis.com/maps/api/place/search/json?location=41.8584,-87.6307&radius=5000&types="+keyWord+"&sensor=false&key=AIzaSyC9fny8B5GVZVLjPTQe24WAzq1znEkPSuk";
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
