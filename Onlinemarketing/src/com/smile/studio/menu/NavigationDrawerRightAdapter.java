package com.smile.studio.menu;

import java.util.Collections;
import java.util.List;

import com.example.onlinemarketing.R;
import com.onlinemarketing.object.SettingVO;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavigationDrawerRightAdapter extends RecyclerView.Adapter<NavigationDrawerRightAdapter.MyViewHolder> {
	List<SettingVO> data = Collections.emptyList();
	private LayoutInflater inflater;
	private Context context;

	public NavigationDrawerRightAdapter(Context context, List<SettingVO> data) {
		this.setContext(context);
		inflater = LayoutInflater.from(context);
		this.data = data;
	}

	public void delete(int position) {
		data.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.custom_item_navigator, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		SettingVO current = data.get(position);
		holder.init(current);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

//		private ImageView imageview = null;
		private TextView title = null;

		public MyViewHolder(View itemView) {
			super(itemView);
//			imageview = (ImageView) itemView.findViewById(R.id.imageview);
//			imageview.setColorFilter(context.getResources().getColor(R.color.white));
			title = (TextView) itemView.findViewById(R.id.title);
		}

		public void init(SettingVO item) {
//			imageview.setImageResource(item.getImageRes());
			title.setText(item.getName());
		}
	}
}
