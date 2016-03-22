package com.smile.studio.menu;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.lib.recycler.OnItemTouchListener;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonSetting;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.SettingVO;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDrawerRight extends Fragment {

	ArrayList<SettingVO> listSetting = new ArrayList<SettingVO>();
	public static OutputProduct oOput;
	
	private RecyclerView recyclerView = null;
	private ActionBarDrawerToggle mDrawerToggle = null;
	private DrawerLayout mDrawerLayout = null;
	private NavigationDrawerRightAdapter adapter = null;
	private View containerView = null;
	private FragmentDrawerListener drawerListener = null;

	public FragmentDrawerRight() {

	}

	public void setDrawerListener(FragmentDrawerListener listener) {
		this.drawerListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new SettingAsystask().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
		
//		adapter = new NavigationDrawerRightAdapter(getActivity(), SystemConfig.oOputproduct.getSettingVO());
//		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addOnItemTouchListener(
				new OnItemTouchListener(getActivity(), recyclerView, new OnItemTouchListener.ClickListener() {
					@Override
					public void onClick(View view, int position) {
//						drawerListener.onDrawerItemSelected(view, position);
//						mDrawerLayout.closeDrawer(containerView);
					}

					@Override
					public void onLongClick(View view, int position) {
						

					}
				}));
		return layout;
	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
		containerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().supportInvalidateOptionsMenu();
			}

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				toolbar.setAlpha(1 - slideOffset / 2);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

	}
	public class SettingAsystask extends AsyncTask<Integer, Integer, OutputProduct> {
		JsonSetting setting;

		@Override
		protected void onPreExecute() {
			setting = new JsonSetting();

			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(Integer... params) {
			oOput = setting.paserSetting(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
			listSetting = oOput.getSettingVO();
//			SystemConfig.oOputproduct.setSettingVO(listSetting);
			return oOput;
		}

		@Override
		protected void onPostExecute(OutputProduct result) {
			Debug.e("SystemConfig.oOputproduct.getSettingVO():  " + listSetting.size());
			adapter = new NavigationDrawerRightAdapter(getActivity(),listSetting);
			recyclerView.setAdapter(adapter);
		}
	}
	public interface FragmentDrawerListener {
		public void onDrawerItemSelected(View view, int position);
	}
}