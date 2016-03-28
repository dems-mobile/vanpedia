package com.demsmobile.vanpedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demsmobile.vanpedia.places.Place;

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
            convertView = layoutInflater.inflate(R.layout.activity_list2, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.title);
           // holder.addressView = (TextView) convertView.findViewById(R.id.address);
           // holder.phoneView = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameView.setText(listData.get(position).name);

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
    }
}

