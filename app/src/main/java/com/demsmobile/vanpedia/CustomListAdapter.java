package com.demsmobile.vanpedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demsmobile.vanpedia.places.Place;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Michelle on 2016-03-08.
 */
public class CustomListAdapter extends BaseAdapter {

    private List<Place> listData;
    private LayoutInflater layoutInflater;
    private Bitmap bitmap;
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
            holder.placeIcon = (ImageView) convertView.findViewById(R.id.placeIcon);
           // holder.phoneView = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameView.setText(listData.get(position).name);
        Log.e("++++", listData.get(position).icon);
        bitmap = getBitmapFromURL(listData.get(position).icon);
        Log.e("---------", bitmap.toString());
        holder.placeIcon.setImageBitmap(bitmap);

       // holder.placeIcon = new LoadImage().execute("https://www.learn2crack.com/wp-content/uploads/2014/04/node-cover-720x340.png");

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
        ImageView placeIcon;
      //  AsyncTask<String, Void, Bitmap> placeView;
       // TextView addressView;
       // TextView phoneView;

    }

    public Bitmap getBitmapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input= connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

 /*   private class LoadImage extends AsyncTask<String, String, Bitmap> {
        ProgressDialog pDialog;
        Bitmap bitmap;
        ImageView img;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // pDialog = new ProgressDialog(MainActivity.this);
            //pDialog.setMessage("Loading Image ....");
            //pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
               // Toast.makeText(MainActivity.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }*/

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

