package com.demsmobile.vanpedia;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.demsmobile.vanpedia.service.Globals;

public class SubcategoryActivity extends MainActivity {

    GridView gridView;
    Globals g = Globals.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);


        setHeaderName();
        
        gridView = (GridView) findViewById(R.id.gridview);

        gridView.setAdapter(new ImageAdapter(this, getSubcategoriesNames()));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String subCategoryName = ((TextView) v.findViewById(R.id.grid_item_label)).getText().toString();
                g.setSubCategoryName(subCategoryName);
                g.setSearchKeys(g.getCategoryName() + " " + subCategoryName);
                Toast.makeText(
                        getApplicationContext(),subCategoryName
                        , Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setHeaderName(){
        String categoryName = g.getCategoryName();
        TextView text_view = (TextView)findViewById(R.id.subCategoryNmeTextView);
        text_view.setText(categoryName.toString());
    }

    public String [] getSubcategoriesNames(){
        String categoryName = g.getCategoryName();
        String[] subCategories = {};

        if (categoryName.equals("eat")) {
            subCategories = new String[] {"Fine", "Casual","Pub", "Breakfast", "Bistro", "Coffee" };
        } else if (categoryName.equals("explore")) {
            subCategories = new String[] {"Concert","Night Life", "Beach", "Sport", "Bike", "Hike", "Mountain" };
        } else if (categoryName.equals("stay")) {
            subCategories = new String[] {"Hotel", "B&B","Hostel", "Rent" };
        }
        return subCategories;
    }

}