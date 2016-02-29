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
                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

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
            subCategories = new String[] {"Android", "iOS","Windows", "Blackberry" };
        } else if (categoryName.equals("explore")) {
            subCategories = new String[] {"explore1", "explore2","explore3", "explore4" };
        } else if (categoryName.equals("stay")) {
            subCategories = new String[] {"stay", "iOS","explore2", "Blackberry" };
        }
        return subCategories;
    }

}