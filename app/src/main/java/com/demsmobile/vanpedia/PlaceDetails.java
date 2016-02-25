package com.demsmobile.vanpedia;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.demsmobile.vanpedia.R;
import com.demsmobile.vanpedia.places.JSONfunctions;
import com.demsmobile.vanpedia.places.MapSinglePoint;
import com.demsmobile.vanpedia.places.SearchSingle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaceDetails extends ListActivity{

    /**
     * Once we select an item on the list, it will send the ref id (standard for google places)
     * It will query more specific detail
     * still not sure which key to use in the URL string...
    * */

    String ref= SearchSingle.getInstance().getter();
    String temp,phoneno,webpage1;
    public String lat;
    public  String log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        String url="https://maps.googleapis.com/maps/api/place/details/json?reference="+ref+"&sensor=true&key=AIzaSyC9fny8B5GVZVLjPTQe24WAzq1znEkPSuk";

        JSONObject json = JSONfunctions.getJSONfromURL(url);
        try {
            JSONObject menuObject = json.getJSONObject("result");
            phoneno=menuObject.getString("formatted_phone_number");
            webpage1=menuObject.getString("url");
            JSONObject menuObject2 = menuObject.getJSONObject("geometry");
            JSONObject menuObject3 = menuObject2.getJSONObject("location");
            lat=menuObject3.getString("lat");
            log=menuObject3.getString("lng");
            MapSinglePoint.getInstance().setlog(log);
            MapSinglePoint.getInstance().setlat(lat);
            phoneno=menuObject.getString("formatted_phone_number");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String yelpurl="http://api.yelp.com/phone_search?phone="+phoneno+"&ywsid=DyR-8d1CkX8BK14X29TYjA";
        yelpurl=yelpurl.replace(" ", "%20");
        json = JSONfunctions.getJSONfromURL(yelpurl);

        try {
            JSONArray me = json.getJSONArray("businesses");
            JSONArray m2=me.getJSONObject(0).getJSONArray("reviews");
            //temp=m2.getJSONObject(0).getString("text_excerpt").toString();
            for(int i=0;i<m2.length();i++){
                HashMap<String, String> map = new HashMap<String, String>();
                //JSONObject e = m2.getJSONObject(i);

                map.put("id",  String.valueOf(i));
                map.put("name", "Review:" + m2.getJSONObject(i).getString("text_excerpt"));
                map.put("magnitude", "By : "+m2.getJSONObject(i).getString("user_name"));
                mylist.add(map);
            }
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.details2,
                new String[] { "name", "magnitude"},
                new int[] { R.id.item_title, R.id.item_subtitle });

        setListAdapter(adapter);

        Button bsearch1 = (Button) findViewById(R.id.button1);

        bsearch1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phoneno));
                    startActivity(callIntent);
                } catch (ActivityNotFoundException e) {
                    Log.e("hello dialing example", "Call failed", e);
                }

            }
        });
        Button map1 = (Button) findViewById(R.id.button2);
        map1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent("com.domain.M12"));

            }
        });
        Button webpage = (Button) findViewById(R.id.button3);
        webpage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpage1));
                startActivity(browserIntent);

            }
        });
    }

}


