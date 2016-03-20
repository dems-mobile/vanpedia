package com.demsmobile.vanpedia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demsmobile.vanpedia.places.Place;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends Activity {


    List<Place> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list);
        placesList = (List<Place>)getIntent().getSerializableExtra("PlacesArray");
        List place_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setAdapter(new CustomListAdapter(this, place_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Place placeItem = (Place) o;
                String reference = placeItem.reference;
                Intent in = new Intent(getApplicationContext(), SinglePlaceActivity.class);
                in.putExtra("reference", reference);
                startActivity(in);
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<Place> results = new ArrayList<Place>();

        Bitmap bitmap;
        for(int i = 0; i < placesList.size(); i++) {
            Place placeData = new Place();
            placeData.reference = placesList.get(i).reference;  //needed for Single Place Activity
            placeData.name = placesList.get(i).name;
            placeData.icon = placesList.get(i).icon;

           // bitmap = getBitmapFromURL("http://images.apple.com/home/images/og.jpg?201603100913");
           // placeData.icon.setImageBitmap(bitmap);
           // placeData.photos = placesList.get(i).photos;
           // placeData.formatted_address = placesList.get(i).formatted_address;
           // placeData.formatted_phone_number = placesList.get(i).formatted_phone_number;
            results.add(placeData);
        }
        return results;
    }



    public Bitmap getBitmapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input= connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}