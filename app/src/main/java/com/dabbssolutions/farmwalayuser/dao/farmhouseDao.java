package com.dabbssolutions.farmwalayuser.dao;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.dabbssolutions.farmwalayuser.model.farmhouses;

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

public class farmhouseDao {


    public String insertFarmhouse(farmhouses f, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/farmHousesAPI/addFarmHouses.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("farmname", f.getFarmname()));
            nameValuePairs.add(new BasicNameValuePair("farmprice", f.getFarmprice()));
            nameValuePairs.add(new BasicNameValuePair("farmlocation", f.getFarmlocation()));
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(f.getAdminid())));


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







    public String updateFarmhouse(farmhouses f, Context context) {

        try {
            final StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            String postReceiverUrl = "http://dabbssolutions.org/api/farmHousesAPI/updateFarmHouses.php";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + postReceiverUrl);

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("farmname", f.getFarmname()));
            nameValuePairs.add(new BasicNameValuePair("farmprice", f.getFarmprice()));
            nameValuePairs.add(new BasicNameValuePair("farmlocation", f.getFarmlocation()));
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(f.getAdminid())));

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






    public String deleteFarmhouse(farmhouses f, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/farmHousesAPI/deleteFarmHouses.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("farmhouseid", String.valueOf(f.getFarmhouseid())));

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







    public String getAllFarmhouses(Context context){

        try {

            String getUrl = "http://dabbssolutions.org/api/farmHousesAPI/getfarmhousewithpics.php";//https://www.pakistanscrabble.org/api.php";
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

    public String getMaxFarmhouseID(Context context){

        try {

            String getUrl = "http://dabbssolutions.org/api/farmHouseFeaturesAPI/getMaxFarmhouseID.php";//https://www.pakistanscrabble.org/api.php";
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
