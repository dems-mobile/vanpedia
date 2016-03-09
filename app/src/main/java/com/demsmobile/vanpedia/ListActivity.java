package com.demsmobile.vanpedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.demsmobile.vanpedia.places.Place;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //??????????? Need help here...... (Trying to show the name, address, and phone number... for now)
        ArrayList<Place> placesList = new ArrayList<Place>();
        placesList = (ArrayList<Place>)getIntent().getSerializableExtra("PlacesArray");

        ArrayList place_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setAdapter(new CustomListAdapter(this, place_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Place placeItem = (Place) o;
            }
        });
    }

    private ArrayList getListData() {
        ArrayList<Place> results = new ArrayList<Place>();
        Place placeData = new Place();
        String name = placeData.name;
        String address = placeData.formatted_address;
        String number = placeData.formatted_phone_number;
        results.add(placeData);
        return results;
    }

}
