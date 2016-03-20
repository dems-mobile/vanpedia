package com.demsmobile.vanpedia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demsmobile.vanpedia.places.Place;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Michelle on 2016-03-08.
 */
public class CustomListAdapter extends BaseAdapter {

    private List<Place> listData;
    private LayoutInflater layoutInflater;
 //   String MY_URL_STRING;

    public CustomListAdapter(Context aContext, List<Place> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_list, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.title);
           // holder.addressView = (TextView) convertView.findViewById(R.id.address);
           // holder.phoneView = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameView.setText(listData.get(position).name);
      //  MY_URL_STRING = listData.get(position).photos;
      //  holder.placeView = new DownloadImageTask((ImageView) convertView.findViewById(R.id.imgPlace))
      //          .execute(MY_URL_STRING);
      //  holder.placeImg.setImageResource
      // holder.addressView.setText(listData.get(position).formatted_address);
      // holder.phoneView.setText(listData.get(position).formatted_phone_number);
        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
       // AsyncTask<String, Void, Bitmap> placeView;
       // TextView addressView;
       // TextView phoneView;

    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}

