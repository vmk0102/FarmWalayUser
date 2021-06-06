package com.dabbssolutions.farmwalayuser.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.farmhouseDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseFeaturesDao;
import com.dabbssolutions.farmwalayuser.model.farmhousefeatures;
import com.dabbssolutions.farmwalayuser.model.farmhouses;
import com.dabbssolutions.farmwalayuser.model.features;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class AdapterFarmhouses extends BaseAdapter {
    ArrayList<farmhouses> farmhouses;
    Context context;
    int view;
    String s;
    public AdapterFarmhouses(ArrayList<farmhouses> farmhouses, Context context, int view){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_farmhouse_single,parent,false);


        }
        farmhouses a =(farmhouses) getItem(position);
        TextView txtFarmName= convertView.findViewById(R.id.txtFarmName);
        TextView txtFarmLocation= convertView.findViewById(R.id.txtLocation);
        TextView txtFarmPrice= convertView.findViewById(R.id.txtPrice);
        txtFarmName.setText(a.getFarmname());
        txtFarmLocation.setText(a.getFarmlocation());
        txtFarmPrice.setText(String.valueOf(a.getFarmprice()));
        RelativeLayout btnDelete=convertView.findViewById(R.id.btnDelete);
        RelativeLayout btnUpdate=convertView.findViewById(R.id.btnUpdate);
        RelativeLayout btnViewFeatures=convertView.findViewById(R.id.btnViewFeatures);
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Please wait");

        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        
        if(view==0){
            btnDelete.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnViewFeatures.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    final LinearLayout ll = new LinearLayout(context);
                    final ListView lv = new ListView(context);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String s= new farmhouseFeaturesDao().getAllFarmhouseFeatures(context,a);
                            if(s.contains("farmhouseid")){
                                Activity act = (Activity)context;
                                act.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        features[] f = new Gson().fromJson(s,features[].class);
                                        ArrayList<features> ff = new ArrayList<>();
                                        Collections.addAll(ff,f);
                                        AdapterFeatures aff = new AdapterFeatures(ff,context,0);
                                        lv.setAdapter(aff);
                                        ll.addView(lv);
                                        ab.setView(ll);
                                        ab.setTitle("Features");
                                        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        ab.show();
                                    }
                                });



                            }

                        }
                    }).start();
                }
            });
        }

            return convertView;
    }
}
