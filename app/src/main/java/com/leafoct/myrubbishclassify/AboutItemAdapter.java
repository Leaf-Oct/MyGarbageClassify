package com.leafoct.myrubbishclassify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AboutItemAdapter extends ArrayAdapter<AboutListItem> {
    private int resourceID;
    public AboutItemAdapter(@NonNull Context context, int resource, @NonNull List<AboutListItem> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AboutListItem item=getItem(position);
        View v= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView iv=(ImageView)v.findViewById(R.id.item_image);
        TextView tv=(TextView)v.findViewById(R.id.item_name);
        iv.setImageResource(item.ID);
        tv.setText(item.name);
        return v;
    }
}
