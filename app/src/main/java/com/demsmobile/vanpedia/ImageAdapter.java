package com.demsmobile.vanpedia;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demsmobile.vanpedia.service.Globals;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[] subcategoriesNames;

    public ImageAdapter(Context context, String[] subcategoriesNames) {
        this.context = context;
        this.subcategoriesNames = subcategoriesNames;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Globals g = Globals.getInstance();
        String categoryName = g.getCategoryName();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(subcategoriesNames[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            String subcategoryName = subcategoriesNames[position];

            switch(categoryName){
                case "eat":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F44336")));
                    break;
                case "explore":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2196F3")));
                    break;
                case "stay":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                    break;
            }

            switch(subcategoryName){
                case "Fine":
                    imageView.setImageResource(R.drawable.waiter);
                    break;
                case "Casual":
                    imageView.setImageResource(R.drawable.cocktail);
                    break;
                case "Pub":
                    imageView.setImageResource(R.drawable.beer);
                    break;
                case "Breakfast":
                    imageView.setImageResource(R.drawable.cheese);
                    break;
                case "Bistro":
                    imageView.setImageResource(R.drawable.hamburger);
                    break;
                case "Coffee":
                    imageView.setImageResource(R.drawable.cafe);
                    break;

                case "Hotel":
                    imageView.setImageResource(R.drawable.towel);
                    break;
                case "B&B":
                    imageView.setImageResource(R.drawable.electric_teapot);
                    break;
                case "Hostel":
                    imageView.setImageResource(R.drawable.caretaker);
                    break;
                case "Rent":
                    imageView.setImageResource(R.drawable.cafe);
                    break;

                case "Bike":
                    imageView.setImageResource(R.drawable.mountain_biking);
                    break;
                case "Sport":
                    imageView.setImageResource(R.drawable.sport);
                    break;
                case "Concert":
                    imageView.setImageResource(R.drawable.music_conductor);
                    break;
                case "Night Life":
                    imageView.setImageResource(R.drawable.dance_with_devil);
                    break;
                case "Beach":
                    imageView.setImageResource(R.drawable.palm_tree);
                    break;
                case "Hike":
                    imageView.setImageResource(R.drawable.trekking);
                    break;
                case "Mountain":
                    imageView.setImageResource(R.drawable.climbing);
                    break;
                default:
                    imageView.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
            }


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return subcategoriesNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}