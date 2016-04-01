package com.example.carlos.kitcardtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.kitcardtest.R;
import com.example.carlos.kitcardtest.model.BikePoint;

import java.util.ArrayList;

/**
 * Created by Carlos2 on 01/04/2016.
 */
public class CustomAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private ArrayList<BikePoint> mItems;

    public CustomAdapter(Context pContext, ArrayList<BikePoint> items){
        this.mItems = new ArrayList<>(items);
        this.mInflater = LayoutInflater.from(pContext);
    }

    @Override
    public int getCount() {
        return this.mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.listview_item, null);
            holder.textViewName = (TextView) convertView.findViewById(R.id.list_item_name);
            holder.textViewEmpty = (TextView) convertView.findViewById(R.id.list_item_empty);
            holder.textViewDocks = (TextView) convertView.findViewById(R.id.list_item_docks);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BikePoint item = this.mItems.get(position);
        holder.textViewName.setText(item.getName());
        holder.textViewEmpty.setText(item.getEmptyDocks());
        holder.textViewDocks.setText(item.getDocks());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textViewName;
        public TextView textViewEmpty;
        public TextView textViewDocks;
    }
}
