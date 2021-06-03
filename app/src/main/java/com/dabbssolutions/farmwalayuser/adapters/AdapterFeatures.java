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

import com.dabbssolutions.farmwalay.R;
import com.dabbssolutions.farmwalay.activities.ActivityUpdateFeature;
import com.dabbssolutions.farmwalay.dao.featuresDao;
import com.dabbssolutions.farmwalay.model.features;

public class AdapterFeatures extends BaseAdapter {
    features[] features;
    Context context;
    int view;
    public AdapterFeatures(features[] features, Context context, int view){
        this.features=features;
        this.context=context;
        this.view=view;
    }

    @Override
    public int getCount() {
        return features.length;
    }

    @Override
    public Object getItem(int position) {
        return features[position];
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_feature_single,parent,false);


        }
        features a =(features) getItem(position);
        TextView txtFeatureName= convertView.findViewById(R.id.txtFeatureName);
        txtFeatureName.setText(a.getFeatureName());
        RelativeLayout btnDelete=convertView.findViewById(R.id.btnDelete);
        RelativeLayout btnUpdate=convertView.findViewById(R.id.btnUpdate);
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
        }else if(view==1){
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pd.show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String s=new featuresDao().deleteFeature(a,context);
                            Activity act=(Activity)context;
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.cancel();

                                    if(s.toLowerCase().contains("true")) {
                                        AlertDialog.Builder ab = new AlertDialog.Builder(context);
                                        ab.setMessage("Feature deleted successfully");
                                        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ((Activity) context).finish();
                                                dialog.cancel();
                                            }
                                        }).show();
                                    }else {
                                        AlertDialog.Builder ab = new AlertDialog.Builder(context);
                                        ab.setMessage("Error adding Feature");
                                        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        }).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }
            });
        }
        else if(view==2){
            btnDelete.setVisibility(View.GONE);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ActivityUpdateFeature.class);
                    i.putExtra("item",a);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }
            });
        }

            return convertView;
    }
}
