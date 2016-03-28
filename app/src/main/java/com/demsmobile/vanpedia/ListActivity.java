package com.demsmobile.vanpedia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.demsmobile.vanpedia.places.Place;
import com.demsmobile.vanpedia.service.Globals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ListActivity extends Activity {

    Globals g = Globals.getInstance();
    List place_list_array;
    List<String> place_list = new ArrayList<String>();
    List<String> place_list_ref = new ArrayList<String>();
    ListView lv;
    TextView tv;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle();
        setBackground();

        place_list_array = (List<Place>)getIntent().getSerializableExtra("PlacesArray");

        for(Iterator<Place> i = place_list_array.iterator(); i.hasNext(); ) {
            Place p = i.next();
            place_list.add(p.name);
            place_list_ref.add(p.reference);
        }

        lv = (ListView) findViewById(R.id.listViewSubCategoryPlacesResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.custom_textview, place_list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                g.setSelectedPlaceId(place_list_ref.get(position));
                Intent i = new Intent(ListActivity.this, SinglePlaceActivity.class);
                startActivity(i);
            };
        });
    }


    public void setTitle(){
        tv = (TextView)findViewById(R.id.listViewSubCategoryPlacesResultTitle);
        String categoryName = g.getCategoryName();
        tv.setText("Let's " + categoryName + " " + g.getSubCategoryName());
        tv.setTextColor(Color.parseColor(g.getCategoryColor()));

        icon = (ImageView)findViewById(R.id.activityListHolderIcon);
        icon.setImageResource(g.getSubCategoryIcon(g.getSubCategoryName()));

    }


    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(){

        LinearLayout layout =(LinearLayout)findViewById(R.id.subCategoryListPlaces);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        String categoryName = g.getCategoryName();
        if (categoryName.equals("eat")) {
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackgroundDrawable( getResources().getDrawable(R.drawable.saltblurimg) );
            } else {
                layout.setBackground( getResources().getDrawable(R.drawable.saltblurimg));
            }
        } else if (categoryName.equals("explore")) {
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackgroundDrawable( getResources().getDrawable(R.drawable.mountainsblurimg) );
            } else {
                layout.setBackground( getResources().getDrawable(R.drawable.mountainsblurimg));
            }
        } else if (categoryName.equals("stay")) {
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackgroundDrawable( getResources().getDrawable(R.drawable.hotelblurimg) );
            } else {
                layout.setBackground( getResources().getDrawable(R.drawable.hotelblurimg));
            }
        }
    }

}
