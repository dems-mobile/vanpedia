package com.demsmobile.vanpedia;

import android.app.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demsmobile.vanpedia.service.Destination;
import com.demsmobile.vanpedia.service.Globals;

import java.util.ArrayList;


public class FavouriteActivity extends android.app.ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String [] favourites = {"Add Favourite", "Add Favourite", "Add Favourite"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_favourite, R.id.favList,favourites));
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setBackgroundResource(R.drawable.favoritebackground);
        lv.setCacheColorHint(0);
    }

    protected void onListItemClick(ListView l, View v, int position, long id){

      //open favourite place details

    }




}
