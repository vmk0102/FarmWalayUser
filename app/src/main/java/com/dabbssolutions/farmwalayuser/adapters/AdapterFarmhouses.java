package com.dabbssolutions.farmwalayuser.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.activities.ActivityAddUsers;
import com.dabbssolutions.farmwalayuser.activities.ActivityViewDetails;
import com.dabbssolutions.farmwalayuser.activities.FarmHouseDetailsActivity;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseFeaturesDao;
import com.dabbssolutions.farmwalayuser.model.bookings;
import com.dabbssolutions.farmwalayuser.model.farmhousefeatures;
import com.dabbssolutions.farmwalayuser.model.farmhouses;
import com.dabbssolutions.farmwalayuser.model.features;
import com.google.gson.Gson;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        RelativeLayout btnBookNow=convertView.findViewById(R.id.btnBookNow);
        RelativeLayout btnViewFeatures=convertView.findViewById(R.id.btnViewFeatures);

        if(a.getFarmpic()!=null) {
            byte[] decodedString = Base64.decode(a.getFarmpic(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgv.setImageBitmap(decodedByte);
        }else{
            imgv.setImageResource(R.drawable.nopic);

        }
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
            btnBookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final bookings b = new bookings();
                    SharedPreferences sharedPreferences=context.getSharedPreferences("mypref",Context.MODE_PRIVATE);
                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Toast.makeText(context, "Select CheckIn Date", Toast.LENGTH_SHORT).show();
                    new SpinnerDatePickerDialogBuilder()
                            .context(context)
                            .callback(new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    b.setCheckinDate(year+"-"+monthOfYear+1+"-"+dayOfMonth);
                                    Toast.makeText(context, "Set Checkout Date", Toast.LENGTH_SHORT).show();
                                    new SpinnerDatePickerDialogBuilder()
                                            .context(context)
                                            .callback(new DatePickerDialog.OnDateSetListener() {
                                                @Override
                                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                    b.setCheckoutDate(year+"-"+monthOfYear+1+"-"+dayOfMonth);
                                                    pd.show();
                                                    try {

                                                        Date date1 = myFormat.parse(b.getCheckinDate());
                                                        Date date2 = myFormat.parse(b.getCheckoutDate());
                                                        long diff = date2.getTime() - date1.getTime();
                                                        b.setBookingprice((TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1) * Double.parseDouble(a.getFarmprice()));
                                                        b.setIsConfirmed(1);

                                                        int uid=Integer.parseInt(sharedPreferences.getString("uid","0"));
                                                        b.setUid(uid);
                                                        b.setFarmhouseid(a.getFarmhouseid());

                                                        new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {

                                                                String s = new bookingDao().insertBookings(b,context);
                                                                Activity act = (Activity)context;
                                                                act.runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        try {
                                                                            pd.cancel();
                                                                            androidx.appcompat.app.AlertDialog.Builder ab = new androidx.appcompat.app.AlertDialog.Builder(context);
                                                                            if (s.toLowerCase().contains("true")) {

                                                                                ab.setMessage("Your booking has been confirmed. Thank You for chosing farmwalay. Your total bill is Rs." + b.getBookingprice());
                                                                            }else{
                                                                                ab.setMessage("Error confirming your booking. Please try again");
                                                                            }
                                                                            ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    dialog.cancel();
                                                                                }
                                                                            }).show();

                                                                        }catch (Exception e){
                                                                            Toast.makeText(context, "Please try again."+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                    }catch (Exception e){
                                                        Log.v("error formatting date: ",e.getMessage());
                                                    }
                                                }
                                            })

                                            .spinnerTheme(R.style.NumberPickerStyle)
                                            .showTitle(true)
                                            .showDaySpinner(true)
                                            .defaultDate(2021, 0, 1)
                                            .maxDate(2050, 0, 1)
                                            .minDate(2021, 0, 1)
                                            .build()
                                            .show();

                                }
                            })

                            .spinnerTheme(R.style.NumberPickerStyle)
                            .showTitle(true)
                            .showDaySpinner(true)
                            .defaultDate(2017, 0, 1)
                            .maxDate(2050, 0, 1)
                            .minDate(2000, 0, 1)
                            .build()
                            .show();
                }
            });

        }

            return convertView;
    }
}
