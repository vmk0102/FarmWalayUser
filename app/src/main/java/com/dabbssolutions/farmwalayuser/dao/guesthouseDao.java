package com.dabbssolutions.farmwalayuser.dao;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.dabbssolutions.farmwalayuser.model.guesthouses;

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

public class guesthouseDao {

    public String insertGuestHouses(guesthouses g, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/guestHousesAPI/addGuestHouses.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("guesthouselocation", g.getGuesthouselocation()));
            nameValuePairs.add(new BasicNameValuePair("guesthousename", g.getGuesthousename()));
            nameValuePairs.add(new BasicNameValuePair("guesthouseprice", String.valueOf(g.getGuesthouseprice())));
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(g.getAdminid())));

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




    public String getGuesthouseByDate(String CheckInDate,String CheckoutDate, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/guestHousesAPI/findAvailableGuesthousesByDates.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("startDate", CheckInDate));
            nameValuePairs.add(new BasicNameValuePair("endDate", CheckoutDate));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();


            if (resEntity != null) {
                Log.v("Hello code: ",String.valueOf(response.getStatusLine().getStatusCode()));
                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return responseStr;
                // you can add an if statement here and do other actions based on the response
            } else {
                Log.v("","No Data");
                return "No Data";
            }
        } catch (Exception e) {
            Log.v("", e.getMessage());
            return e.getMessage();
        }
    }





    public String updateGuestHouses(guesthouses g, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/guestHousesAPI/updateGuestHouses.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("guesthouseid", String.valueOf(g.getGuesthouseid())));
            nameValuePairs.add(new BasicNameValuePair("guesthouselocation", g.getGuesthouselocation()));
            nameValuePairs.add(new BasicNameValuePair("guesthousename", g.getGuesthousename()));
            nameValuePairs.add(new BasicNameValuePair("guesthouseprice", String.valueOf(g.getGuesthouseprice())));
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(g.getAdminid())));

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






    public String deleteGuestHouses(guesthouses g, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/guestHousesAPI/deleteGuestHouses.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("guesthouseid", String.valueOf(g.getGuesthouseid())));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();


            if (resEntity != null) {
                Log.v("Hello code: ",String.valueOf(response.getStatusLine().getStatusCode()));
                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return  responseStr;
                // you can add an if statement here and do other actions based on the response
            } else {
                Log.v("","No Data");
                return  "Error Deleting";
            }
        } catch (Exception e) {
            Log.v("", e.getMessage());
            return  "Error Deleting "+e.getMessage();
        }
    }







    public String getAllGuestHouses(Context context){

        try {

            //String getUrl = "http://dabbssolutions.org/api/guestHousesAPI/getAllGuestHouses.php";//https://www.pakistanscrabble.org/api.php";
            String getUrl = "http://dabbssolutions.org/api/guestHousesAPI/getguesthousewithpics.php"; ////https://www.pakistanscrabble.org/api.php";//https://www.pakistanscrabble.org/api.php";

            Log.v("SOMEONE ONCE SAID", "postURL: " + getUrl);

            HttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(getUrl);


            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return  responseStr;
                //return responseStr;

                // you can add an if statement here and do other actions based on the response
            } else {
                return "NO DATA";
            }
        } catch (Exception e) {
            return e.getMessage();

        }
    }



    public String getMaxGuesthouseID(Context context){

        try {

            String getUrl = "http://dabbssolutions.org/api/guestHouseFeatureAPI/getMaxGuesthouseID.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + getUrl);

            HttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(getUrl);


            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return  responseStr;
                //return responseStr;

                // you can add an if statement here and do other actions based on the response
            } else {
                return "NO DATA";
            }
        } catch (Exception e) {
            return e.getMessage();

        }
    }


}
