package com.demsmobile.vanpedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.demsmobile.vanpedia.service.Destination;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class TopDestination extends ActionBarActivity {

    ArrayList<Destination> place_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_destination);

        place_list = (ArrayList<Destination>)getIntent().getSerializableExtra("dest_Array");

        // 0    "Stanley Park",
        // 1    "735 Stanley Park Drive, Stanley Park, Vancouver, BC V6C 2T1",
        // 2    "604-681-5115",
        // 3    "www.stanleypark.com",
        // 4    d0,
        // 5    destImg[0]) --> {"d00","d01","d02"}

        String name = place_list.get(0).toString();
        String address = place_list.get(1).toString();
        String phone = place_list.get(2).toString();
        String website = place_list.get(3).toString();
        String description = place_list.get(4).toString();
        int [] images = place_list.get(5).images();



        ImageView lbl_mainImage = (ImageView) findViewById(R.id.imgMain);
        TextView lbl_name = (TextView) findViewById(R.id.name);
        TextView lbl_address = (TextView) findViewById(R.id.address);
        TextView lbl_phone = (TextView) findViewById(R.id.phone);
        TextView lbl_website = (TextView) findViewById(R.id.website);
        TextView lbl_description = (TextView) findViewById(R.id.description);


        lbl_mainImage.setImageResource(images[0]);
        lbl_name.setText(name);
        lbl_address.setText(address);
        lbl_website.setText(website);
        lbl_phone.setText(phone);
        lbl_description.setText(description);
    }


}
