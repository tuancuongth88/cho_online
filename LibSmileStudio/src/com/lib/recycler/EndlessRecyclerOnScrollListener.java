package com.lib.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

	private int previousTotal = 0;
	private boolean loading = true;
	private int visibleThreshold = 10;
	private int firstVisibleItem, visibleItemCount, totalItemCount;
	public int current_page = 1;
	private LinearLayoutManager mLinearLayoutManager = null;
	private GridLayoutManager mGridLayoutManager = null;

	public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, int visibleThreshold) {
		this.mLinearLayoutManager = linearLayoutManager;
		this.visibleThreshold = visibleThreshold;
	}

	public EndlessRecyclerOnScrollListener(GridLayoutManager mGridLayoutManager, int visibleThreshold) {
		this.mGridLayoutManager = mGridLayoutManager;
		this.visibleThreshold = visibleThreshold;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		visibleItemCount = recyclerView.getChildCount();
		if (mLinearLayoutManager != null) {
			totalItemCount = mLinearLayoutManager.getItemCount();
			firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
		}
		if (mGridLayoutManager != null) {
			totalItemCount = mGridLayoutManager.getItemCount();
			firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
		}
		if (loading) {
			if (totalItemCount > previousTotal) {
				loading = false;
				previousTotal = totalItemCount;
			}
		}
		if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
			current_page += 1;
			onLoadMore(current_page);
			loading = true;
		}
	}

	public abstract void onLoadMore(int current_page);
}
