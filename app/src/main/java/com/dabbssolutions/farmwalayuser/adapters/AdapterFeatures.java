package com.dabbssolutions.farmwalayuser.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.featuresDao;
import com.dabbssolutions.farmwalayuser.model.features;

import java.util.ArrayList;

public class AdapterFeatures extends BaseAdapter {
    ArrayList<features> features;
    Context context;
    int view;
    public AdapterFeatures(ArrayList<features> features, Context context, int view){
        this.features=features;
        this.context=context;
        this.view=view;
    }

    @Override
    public int getCount() {
        return features.size();
    }

    @Override
    public Object getItem(int position) {
        return features.get(position);
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
        features a =(features) getItem(position);
        TextView txtFeatureName= convertView.findViewById(R.id.lblFeatureName);
        txtFeatureName.setText(a.getFeatureName());
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Please wait");

        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        


        return convertView;
    }
}
