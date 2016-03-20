package com.demsmobile.vanpedia;

import android.app.*;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DestinationActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] attractions = {"Stanley Park", "Science World", "Grouse Mountain", "Capilano Suspension Bridge", "Vancouver Art Gallery"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_destination, R.id.top5,attractions));
    }

    protected void onListItemClick(ListView l, View v, int position, long id){

        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }


}
