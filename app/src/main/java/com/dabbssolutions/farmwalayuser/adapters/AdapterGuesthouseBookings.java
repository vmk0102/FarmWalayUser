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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.model.bookings;

import java.util.ArrayList;

public class AdapterGuesthouseBookings extends BaseAdapter {
    ArrayList<bookings> bookings;
    Context context;

    int view;
    String s;
    public AdapterGuesthouseBookings(ArrayList<bookings> bookings, Context context,int view){
        this.bookings=bookings;
        this.context=context;
        this.view=view;
    }

    @Override
    public int getCount() {
        return bookings.size();
    }

    @Override
    public Object getItem(int position) {
        return bookings.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_booking_single,parent,false);


        }
        if(position%2==0){

            convertView.setBackgroundResource(R.color.lightgreenapp);
        }else {
            convertView.setBackgroundResource(R.color.white);
        }
        TextView FullName = convertView.findViewById(R.id.txtUserName);
        TextView FarmName = convertView.findViewById(R.id.txtFarmName);
        TextView FarmPrice= convertView.findViewById(R.id.txtPrice);
        TextView Location = convertView.findViewById(R.id.txtLocation);
        TextView Phone = convertView.findViewById(R.id.txtPhone);
        TextView CheckInDate = convertView.findViewById(R.id.txtCheckIn);
        TextView CheckOutDate = convertView.findViewById(R.id.txtCheckOut);
        final ProgressDialog pd= new ProgressDialog(context);
        pd.setMessage("Please wait");
        bookings a =(bookings) getItem(position);
        RelativeLayout btnDelete=convertView.findViewById(R.id.btnDelete);
        if(view==0){
            btnDelete.setVisibility(View.GONE);
        }else{
            btnDelete.setOnClickListener(new View.OnClickListener() {
                Activity act = (Activity)context;

                @Override
                public void onClick(View v) {
                    pd.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String s = new bookingDao().deleteBooking(a,context);
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.cancel();
                                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                                    if(s.toLowerCase().contains("true")){
                                        ab.setMessage("Booking deleted Successfully");
                                    }else{
                                        ab.setMessage("There was an error deleting the booking");

                                    }
                                    ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                                }
                            });

                        }
                    }).start();
                }
            });
        }
        if(a.getFullName()!=null) {
            FullName.setText(a.getFullName());
        }else{
            FullName.setText(a.getStaticCustomer());
        }

            FarmName.setText(a.getGuesthouseName());

        FarmPrice.setText(String.valueOf(a.getPrice()));
        Location.setText(a.getLocation());
        if(a.getPhone()!=null) {
            Phone.setText(a.getPhone());
        }else{
            Phone.setText(a.getStaticPhone());
        }
        CheckInDate.setText(a.getCheckinDate());

        CheckOutDate.setText(a.getCheckoutDate());





            return convertView;
    }

}
