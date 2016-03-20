package com.demsmobile.vanpedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demsmobile.vanpedia.places.Place;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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

        for(int i = 0; i < placesList.size(); i++) {
            Place placeData = new Place();
            placeData.reference = placesList.get(i).reference;  //needed for Single Place Activity
            placeData.name = placesList.get(i).name;
           // placeData.photos = placesList.get(i).photos;
           // placeData.formatted_address = placesList.get(i).formatted_address;
           // placeData.formatted_phone_number = placesList.get(i).formatted_phone_number;
            results.add(placeData);
        }
        return results;
    }
}