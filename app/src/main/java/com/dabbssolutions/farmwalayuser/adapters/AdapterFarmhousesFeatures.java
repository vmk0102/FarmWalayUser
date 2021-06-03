package com.dabbssolutions.farmwalayuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dabbssolutions.farmwalay.R;
import com.dabbssolutions.farmwalay.model.farmhousefeatures;

import java.util.ArrayList;

public class AdapterFarmhousesFeatures extends BaseAdapter {
    ArrayList<farmhousefeatures> farmhouses;
    Context context;
    int view;
    String s;
    public AdapterFarmhousesFeatures(ArrayList<farmhousefeatures> farmhouses, Context context, int view){
        this.farmhouses=farmhouses;
        this.context=context;
        this.view=view;
    }

    @Override
    public int getCount() {
        return farmhouses.size();
    }

    @Override
    public Object getItem(int position) {
        return farmhouses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_featureforadd_single,parent,false);


        }
        farmhousefeatures a =(farmhousefeatures) getItem(position);
        CheckBox cb = convertView.findViewById(R.id.txtFeatureChecked);
        cb.setVisibility(View.GONE);
        TextView txtFarmName= convertView.findViewById(R.id.txtFeatureName);
        txtFarmName.setText(a.getFeatureName());

        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        


            return convertView;
    }
}
