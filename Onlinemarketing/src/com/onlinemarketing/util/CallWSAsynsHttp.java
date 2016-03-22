package com.onlinemarketing.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.onlinemarketing.config.SystemConfig;

public class CallWSAsynsHttp {
	ProgressDialog prgDialog;
	private Context context;
	private String linkWS;
	private int status;
	public CallWSAsynsHttp(Context context, String linkWS,int status) {
		super();
		this.context = context;
		this.linkWS = linkWS ;
		this.status = status ;
		prgDialog = new ProgressDialog(context);
		 // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
	}

	public <T> T[] params(T[] list, RequestParams params) {
		if(status == SystemConfig.httpget)
			list = invokeWSGet(null,params);
		if(status == SystemConfig.httppost)
			list = invokeWSPost(null,params);
        return list;
	}

	 public <T> T[] invokeWSGet(T[] list,RequestParams params){
		// TODO Auto-generated method stub
		// Show Progress Dialog
		 prgDialog.show();
         AsyncHttpClient client = new AsyncHttpClient();
         client.get(linkWS,params ,new AsyncHttpResponseHandler() {
        	 @Override
        	 public void onSuccess(String response) {
                 // Hide Progress Dialog
        		 prgDialog.hide();
                 try {
                         // JSON Object
                         JSONObject obj = new JSONObject(response);
                         Log.e("String tra ve: ", response);
                         // When the JSON response has status boolean value assigned with true
                         if(obj.getBoolean("status")){
                             Toast.makeText(context.getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                         }
                         else{
                             Toast.makeText(context.getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                         }
                 } catch (JSONException e) {
                     Toast.makeText(context.getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                     e.printStackTrace();
                 }
             }
             // When the response returned by REST has Http response code other than '200'
             @Override
             public void onFailure(int statusCode, Throwable error,
                 String content) {
                 // Hide Progress Dialog 
            	 prgDialog.hide();
                 // When Http response code is '404'
                 if(statusCode == 404){
                     Toast.makeText(context.getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code is '500'
                 else if(statusCode == 500){
                     Toast.makeText(context.getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code other than 404, 500
                 else{
                     Toast.makeText(context.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                 }
             }
         });
         return list;
	}
	 
	 public <T> T[] invokeWSPost(T[] list,RequestParams params){
			// TODO Auto-generated method stub
			// Show Progress Dialog
		 	prgDialog.show();
	        AsyncHttpClient client = new AsyncHttpClient();
	        client.get(linkWS,params ,new AsyncHttpResponseHandler() {
	        	 
	        	 @Override
	        	 public void onSuccess(String response) {
	                 // Hide Progress Dialog
	        		 prgDialog.hide();
	                 try {
	                         // JSON Object
	                         JSONObject obj = new JSONObject(response);
	                         Log.e("String tra ve: ", response);
	                         // When the JSON response has status boolean value assigned with true
	                         if(obj.getBoolean("status")){
	                             Toast.makeText(context.getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
	                         }
	                         else{
	                             Toast.makeText(context.getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
	                         }
	                 } catch (JSONException e) {
	                     Toast.makeText(context.getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
	                     e.printStackTrace();
	                 }
	             }
	             // When the response returned by REST has Http response code other than '200'
	             @Override
	             public void onFailure(int statusCode, Throwable error,
	                 String content) {
	                 // Hide Progress Dialog 
	            	 prgDialog.hide();
	                 // When Http response code is '404'
	                 if(statusCode == 404){
	                     Toast.makeText(context.getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
	                 } 
	                 // When Http response code is '500'
	                 else if(statusCode == 500){
	                     Toast.makeText(context.getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
	                 } 
	                 // When Http response code other than 404, 500
	                 else{
	                     Toast.makeText(context.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
	                 }
	             }
	         });
	         return list;
		}
}
