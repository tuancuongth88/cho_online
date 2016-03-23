package com.onlinemarketing.adapter;

import java.util.List;

import com.androidquery.AQuery;
import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.loopj.android.http.RequestParams;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.MessageVO;
import com.onlinemarketing.util.CallWSAsynsHttp;
import com.onlinemarketing.util.ChatDialog;
import com.onlinemarketing.util.ChatDialog.MessageAsystask;
import com.onlinemarketing.util.Message;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListMessageAdapter extends ArrayAdapter<MessageVO>{

	Context context;
	List<MessageVO> listData;
	int layoutId;
	LayoutInflater inflater;
	ViewHolder holder;
	Dialog dialog;
	TextView txtalert;
	Button btnOk,btnCancle;
	 int position_arr;
	public ListMessageAdapter(Context context, int resource, List<MessageVO> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutId = resource;
		this.listData = objects;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private class ViewHolder {
		ImageView img;
		TextView txtUserName, txtDecripMessage, txtDateMessage;
		private AQuery aQuery = null;

		public ViewHolder(View view) {
			img = (ImageView) view.findViewById(R.id.itemAvatarMessage);
			txtUserName = (TextView) view.findViewById(R.id.txtNameMessage);
			txtDecripMessage = (TextView) view.findViewById(R.id.txtDescripMessage);
			txtDateMessage = (TextView) view.findViewById(R.id.txtDateMessage);
			aQuery = new AQuery(view);
		}

		public void init(MessageVO item) {
			Bitmap bitmap = aQuery.getCachedImage(item.getAvatar());
			if (bitmap != null) {
				aQuery.id(img).image(bitmap);
			} else {
				aQuery.id(img).image(item.getAvatar(), true, true, 0, R.drawable.ic_launcher);
			}
			txtUserName.setText(item.getUsername());
			txtDecripMessage.setText(item.getMessage());
			txtDateMessage.setText(item.getCreate_at());
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageView btnDelet=(ImageView)convertView.findViewById(R.id.itemDeleteMessage);
		ImageView btnBock=(ImageView)convertView.findViewById(R.id.itemBockMessage);
		btnDelet.setTag(position);
		btnDelet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.itemDeleteMessage:
					Integer index = (Integer) v.getTag();
					  int id = index;
					  dialogDelete(id);
					break;

				default:
					break;
				}
				  
			}
		});             
		holder.init(listData.get(position));
		return convertView;
	}
	
	public void deletegroup(int position){
		MessageVO objmessage = listData.get(position);
		int id = objmessage.getReceiver_id();
		ChatDialog objchat = new ChatDialog(context,id);
		objchat.run(SystemConfig.statusDeleteGroupMessage);
		
		
	}
	public void dialogDelete( int position) {
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_delete);
		dialog.setTitle("Thông Báo");
		txtalert = (TextView) dialog.findViewById(R.id.txtalert);
		txtalert.setText("Bạn muốn xóa toàn bộ cuộc trò truyên!");
		txtalert.setTextSize(18);
		btnOk = (Button) dialog.findViewById(R.id.btn_Ok_Delete);
		btnCancle = (Button) dialog.findViewById(R.id.btn_Cancle_Delete);
		position_arr = position;
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				deletegroup(position_arr);
				listData.remove(position_arr);
				notifyDataSetChanged();
				dialog.dismiss();
			}
		});
		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	
	
}
