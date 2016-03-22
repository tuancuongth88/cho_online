package com.smile.studio.menu;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.lib.recycler.OnItemTouchListener;
import com.onlinemarketing.activity.GlobalApp;
import com.onlinemarketing.config.SystemConfig;

import android.annotation.TargetApi;
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

public class FragmentDrawer extends Fragment {

	private RecyclerView recyclerView = null;
	private ActionBarDrawerToggle mDrawerToggle = null;
	private DrawerLayout mDrawerLayout = null;
	private NavigationDrawerAdapter adapter = null;
	private View containerView = null;
	private FragmentDrawerListener drawerListener = null;

	public FragmentDrawer() {

	}

	public void setDrawerListener(FragmentDrawerListener listener) {
		this.drawerListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
		
		int n = SystemConfig.oOputproduct.getCategoryVO().size();
		String []title = new String[n];
		for(int i=0; i< n; i++){
			title[i]= SystemConfig.oOputproduct.getCategoryVO().get(i).getName();
		Debug.e("ten category: "+ SystemConfig.oOputproduct.getCategoryVO().get(i).getName());
		}
		
		adapter = new NavigationDrawerAdapter(getActivity(), SystemConfig.oOputproduct.getCategoryVO());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addOnItemTouchListener(
				new OnItemTouchListener(getActivity(), recyclerView, new OnItemTouchListener.ClickListener() {
					@Override
					public void onClick(View view, int position) {
						drawerListener.onDrawerItemSelected(view, position);
						mDrawerLayout.closeDrawer(containerView);
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

	public interface FragmentDrawerListener {
		public void onDrawerItemSelected(View view, int position);
	}
}