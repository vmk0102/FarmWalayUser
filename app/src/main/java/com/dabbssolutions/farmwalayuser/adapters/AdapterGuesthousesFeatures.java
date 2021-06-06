package com.dabbssolutions.farmwalayuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.model.guesthousefeatures;

import java.util.ArrayList;

public class AdapterGuesthousesFeatures extends BaseAdapter {
    ArrayList<guesthousefeatures> guesthousefeatures;
    Context context;
    int view;
    String s;
    public AdapterGuesthousesFeatures(ArrayList<guesthousefeatures> guesthousefeatures, Context context, int view){
        this.guesthousefeatures=guesthousefeatures;
        this.context=context;
        this.view=view;
    }

    @Override
    public int getCount() {
        return guesthousefeatures.size();
    }

    @Override
    public Object getItem(int position) {
        return guesthousefeatures.get(position);
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
      convertView= LayoutInflater.from(context).inflate(R.layout.layout_features_single,parent,false);


        }
        guesthousefeatures a =(guesthousefeatures) getItem(position);
        TextView txtFarmName= convertView.findViewById(R.id.lblFeatureName);
        txtFarmName.setText(a.getFeaturename());

        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        


            return convertView;
    }
}
