package com.dabbssolutions.farmwalayuser.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.model.guesthousepictures;

import java.util.ArrayList;

public class AdapterGuesthousePictures extends BaseAdapter {
    ArrayList<guesthousepictures> farmhouses;
    Context context;
    int view;
    String s;
    public AdapterGuesthousePictures(ArrayList<guesthousepictures> farmhouses, Context context, int view){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_farmhousepictures_single,parent,false);


        }
        guesthousepictures a =(guesthousepictures) getItem(position);
        ImageView imgFarmPic= convertView.findViewById(R.id.farmpic);
        byte[] decodedString = Base64.decode(a.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
         imgFarmPic.setImageBitmap(decodedByte);


        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        


            return convertView;
    }

}
