package com.dabbssolutions.farmwalayuser.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.activities.ActivityAddUsers;
import com.dabbssolutions.farmwalayuser.activities.ActivityViewDetails;
import com.dabbssolutions.farmwalayuser.activities.FarmHouseDetailsActivity;
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
        ImageView imgv = convertView.findViewById(R.id.farmpic);
        txtFarmName.setText(a.getFarmname());
        txtFarmLocation.setText(a.getFarmlocation());
        txtFarmPrice.setText("Rs. "+String.valueOf(a.getFarmprice()));
        RelativeLayout btnUpdate=convertView.findViewById(R.id.btnBookNow);
        RelativeLayout btnViewFeatures=convertView.findViewById(R.id.btnViewFeatures);

        byte[] decodedString = Base64.decode(a.getFarmpic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgv.setImageBitmap(decodedByte);
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Please wait");

        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        
        if(view==0){

            btnViewFeatures.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    final LinearLayout ll = new LinearLayout(context);
                    Intent intent = new Intent(context, FarmHouseDetailsActivity.class);
                    intent.putExtra("id","f:"+String.valueOf(a.getFarmhouseid()));
                    intent.putExtra("location",String.valueOf(a.getFarmlocation()));
                    intent.putExtra("price",String.valueOf(a.getFarmprice()));
                    intent.putExtra("name",String.valueOf(a.getFarmname()));
                    context.startActivity(intent);



                }
            });

        }

            return convertView;
    }
}