package com.dabbssolutions.farmwalayuser.dao;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.dabbssolutions.farmwalayuser.model.bookings;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class bookingDao {

    public String insertBookings(bookings b, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/bookingsAPI/addBookings.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("bookingdate", b.getBookingdate()));
            nameValuePairs.add(new BasicNameValuePair("bookingtime", b.getBookingtime()));
            nameValuePairs.add(new BasicNameValuePair("uid", String.valueOf(b.getUid())));
            nameValuePairs.add(new BasicNameValuePair("farmhouseid", String.valueOf(b.getFarmhouseid())));
            nameValuePairs.add(new BasicNameValuePair("guesthouseid", String.valueOf(b.getGuesthouseid())));
            nameValuePairs.add(new BasicNameValuePair("bookingprice", String.valueOf(b.getBookingprice())));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return responseStr;
                //return responseStr;

                // you can add an if statement here and do other actions based on the response
            }else{
                return "NO DATA";
            }
        } catch (Exception e) {
            return e.getMessage();

        }
    }







    public String updateBookings(bookings b, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/bookingsAPI/updateBookings.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("bid", String.valueOf(b.getBid())));
            nameValuePairs.add(new BasicNameValuePair("bookingdate", b.getBookingdate()));
            nameValuePairs.add(new BasicNameValuePair("bookingtime", b.getBookingtime()));
            nameValuePairs.add(new BasicNameValuePair("uid", String.valueOf(b.getUid())));
            nameValuePairs.add(new BasicNameValuePair("farmhouseid", String.valueOf(b.getFarmhouseid())));
            nameValuePairs.add(new BasicNameValuePair("guesthouseid", String.valueOf(b.getGuesthouseid())));
            nameValuePairs.add(new BasicNameValuePair("bookingprice", String.valueOf(b.getBookingprice())));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return responseStr;
                //return responseStr;

                // you can add an if statement here and do other actions based on the response
            }else{
                return "NO DATA";
            }
        } catch (Exception e) {
            return e.getMessage();

        }
    }






    public void deleteBooking(bookings b, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/bookingsAPI/deleteBookings.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("bid", String.valueOf(b.getBid())));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();


            if (resEntity != null) {
                Log.v("Hello code: ",String.valueOf(response.getStatusLine().getStatusCode()));
                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);

                // you can add an if statement here and do other actions based on the response
            } else {
                Log.v("","No Data");
            }
        } catch (Exception e) {
            Log.v("", e.getMessage());
        }
    }






//
//    public String getAllBookings(Context context){
//
//        try {
//
//            String getUrl = "http://dabbssolutions.org/api/bookingsAPI/getAllBookings.php";//https://www.pakistanscrabble.org/api.php";
//            Log.v("SOMEONE ONCE SAID", "postURL: " + getUrl);
//
//            HttpClient httpClient = new DefaultHttpClient();
//
//            HttpGet httpGet = new HttpGet(getUrl);
//
//
//            HttpResponse response = httpClient.execute(httpGet);
//            HttpEntity resEntity = response.getEntity();
//
//            if (resEntity != null) {
//
//                String responseStr = EntityUtils.toString(resEntity).trim();
//                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
//                return  responseStr;
//                //return responseStr;
//
//                // you can add an if statement here and do other actions based on the response
//            } else {
//                return "NO DATA";
//            }
//        } catch (Exception e) {
//            return e.getMessage();
//
//        }
//    }


}
