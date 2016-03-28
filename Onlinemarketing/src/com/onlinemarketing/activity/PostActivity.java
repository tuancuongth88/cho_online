package com.onlinemarketing.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.util.CallWSAsynsHttp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class PostActivity extends Activity implements OnClickListener{
	ProgressDialog prgDialog;
	ImageView imgCamrePost;
	TextView edit_TitlePost,edit_PricePost,edit_AddPost,edit_DescripPost;
	Spinner spnCategoryPost, spnMenuPost;
	Button btnpost;
	JSONObject json;
	
	int PICK_IMAGE_MULTIPLE = 1;
	String imageEncoded;    
	List<String> imagesEncodedList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		setData();

	}
	
	public void setData(){
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Please wait...");
		prgDialog.setCancelable(false);
		edit_TitlePost = (TextView) findViewById(R.id.edit_TitlePost);
		edit_PricePost = (TextView) findViewById(R.id.edit_PricePost);
		edit_AddPost = (TextView) findViewById(R.id.edit_AddPost);
		edit_DescripPost = (TextView) findViewById(R.id.edit_DescripPost);
		spnCategoryPost = (Spinner) findViewById(R.id.spnCategoryPost);
		spnMenuPost = (Spinner) findViewById(R.id.spnMenuPost);
		imgCamrePost = (ImageView) findViewById(R.id.imgCamrePost);
		btnpost = (Button) findViewById(R.id.btnpost);
		imgCamrePost.setOnClickListener(this);
		btnpost.setOnClickListener(this);
		int n = SystemConfig.oOputproduct.getCategoryVO().size();
		String[] title = new String[n];
		for (int i = 0; i < n; i++) {
			title[i] = SystemConfig.oOputproduct.getCategoryVO().get(i)
					.getName();
		}
		spnCategoryPost.setAdapter(new ArrayAdapter<String>(this,
				R.layout.item_spinersearch, title));

	}
	
	public void getData(){
		CallWSAsynsHttp callWS = new CallWSAsynsHttp(this, SystemConfig.API+ SystemConfig.Post_product, SystemConfig.httpget);
		RequestParams params = new RequestParams();
		params.put("user_id", SystemConfig.user_id);
		params.put("session_id", SystemConfig.session_id);
		params.put("device_id", SystemConfig.device_id);
//		String respont = callWS.invokeWSGet(params);
		invokeWSGet(params);
		
		Debug.e("sssss"+ SystemConfig.oOputproduct.getCategoryVO().get(0).getName());
	}
	
	 public void invokeWSGet(RequestParams params){
			// TODO Auto-generated method stub
			// Show Progress Dialog
			 prgDialog.show();
	         AsyncHttpClient client = new AsyncHttpClient();
	         client.get(SystemConfig.API+ SystemConfig.Post_product,params ,new AsyncHttpResponseHandler() {
	        	 @Override
	        	 public void onSuccess(String response) {
	                 // Hide Progress Dialog
	        		 prgDialog.hide();
	                 try {
	                         // JSON Object
	                         JSONObject obj = new JSONObject(response);
	                         Log.e("String tra ve: ", response);
	                         // When the JSON response has status boolean value assigned with true
	                         if(obj.getInt("code")== 200){
	                        	 
	                         }
	                 } catch (JSONException e) {
	                     e.printStackTrace();
	                 }
	             }
	             // When the response returned by REST has Http response code other than '200'
	             @Override
	             public void onFailure(int statusCode, Throwable error,
	                 String content) {
	            	 prgDialog.hide();
	             }
	         });
		}
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		 try {
	            // When an Image is picked
	            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
	                    && null != data) {
	                // Get the Image from data

	                String[] filePathColumn = { MediaStore.Images.Media.DATA };
	                imagesEncodedList = new ArrayList<String>();
	                if(data.getData()!=null){

	                    Uri mImageUri=data.getData();

	                    // Get the cursor
	                    Cursor cursor = getContentResolver().query(mImageUri,
	                            filePathColumn, null, null, null);
	                    // Move to first row
	                    cursor.moveToFirst();

	                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	                    imageEncoded  = cursor.getString(columnIndex);
	                    cursor.close();

	                }else {
	                    if (data.getClipData() != null) {
	                        ClipData mClipData = data.getClipData();
	                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
	                        for (int i = 0; i < mClipData.getItemCount(); i++) {

	                            ClipData.Item item = mClipData.getItemAt(i);
	                            Uri uri = item.getUri();
	                            mArrayUri.add(uri);
	                            // Get the cursor
	                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
	                            // Move to first row
	                            cursor.moveToFirst();

	                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	                            imageEncoded  = cursor.getString(columnIndex);
	                            imagesEncodedList.add(imageEncoded);
	                            cursor.close();

	                        }
	                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
	                    }
	                }
	            } else {
	                Toast.makeText(this, "You haven't picked Image",
	                        Toast.LENGTH_LONG).show();
	            }
	        } catch (Exception e) {
	            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
	                    .show();
	        }

	        super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgCamrePost:
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
			break;

		case R.id.btnpost:
			
			break;
		}
	}

}
