package com.dabbssolutions.farmwalayuser.dao;

import android.content.Context;
import android.util.Log;
import com.dabbssolutions.farmwalayuser.model.admins;

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

public class adminDAO {


    public String insertAdmin(admins admin, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/adminAPI/addAdmin.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("firstname", admin.getFirstname()));
            nameValuePairs.add(new BasicNameValuePair("lastname", admin.getLastname()));
            nameValuePairs.add(new BasicNameValuePair("cnic", admin.getCnic()));
            nameValuePairs.add(new BasicNameValuePair("password", admin.getPassword()));
            nameValuePairs.add(new BasicNameValuePair("phone", admin.getPhone()));
            nameValuePairs.add(new BasicNameValuePair("isSuperUser", String.valueOf(admin.getIsSuperUser())));

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




    public String updateAdmin(admins admin, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/adminAPI/updateAdmin.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(admin.getAdminid())));
            nameValuePairs.add(new BasicNameValuePair("firstname", admin.getFirstname()));
            nameValuePairs.add(new BasicNameValuePair("lastname", admin.getLastname()));
            nameValuePairs.add(new BasicNameValuePair("cnic", admin.getCnic()));
            nameValuePairs.add(new BasicNameValuePair("password", admin.getPassword()));
            nameValuePairs.add(new BasicNameValuePair("phone", admin.getPhone()));
            nameValuePairs.add(new BasicNameValuePair("isSuperUser", String.valueOf(admin.getIsSuperUser())));

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






    public String deleteAdmin(admins admin, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/adminAPI/deleteAdmin.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("adminid", String.valueOf(admin.getAdminid())));

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
                return "error deleting";
            }
        } catch (Exception e) {
            Log.v("", e.getMessage());
            return e.getMessage();
        }
    }







    public String getAllAdmins(Context context){

        try {

            String getUrl = "http://dabbssolutions.org/api/adminAPI/getAllAdmin.php";//https://www.pakistanscrabble.org/api.php";
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





    public String verifyLogin(admins admin, Context context){

        try{
            String postReceiverUrl = "http://dabbssolutions.org/api/adminAPI/adminLoginVerify.php";//https://www.pakistanscrabble.org/api.php";

            // HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // post header
            HttpPost httpPost = new HttpPost(postReceiverUrl);

            httpPost.setHeader("Accept", "/");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("password", admin.getPassword()));
            nameValuePairs.add(new BasicNameValuePair("phone", admin.getPhone()));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // execute HTTP post request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();


            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v("SOMEONE ONCE SAID", "Response: " + responseStr);
                return responseStr;

                // you can add an if statement here and do other actions based on the response
            }else{
                return "NO DATA";
            }
        } catch (Exception e) {
            return e.getMessage();

        }
    }




}
