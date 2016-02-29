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

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(subcategoriesNames[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String subcategoryName = subcategoriesNames[position];

            switch(categoryName){
                case "eat":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#db3424")));
                    break;
                case "explore":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0eaee1")));
                    break;
                case "stay":
                    imageView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#71d300")));
                    break;
            }


            switch(subcategoryName){
                case "Windows":
                    imageView.setImageResource(R.drawable.glyphicons_476_eat);
                    break;
                case "iOS":
                    imageView.setImageResource(R.drawable.glyphicons_497_stay);
                    break;
                case "Blackberry":
                    imageView.setImageResource(R.drawable.glyphicons_503_explore);
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