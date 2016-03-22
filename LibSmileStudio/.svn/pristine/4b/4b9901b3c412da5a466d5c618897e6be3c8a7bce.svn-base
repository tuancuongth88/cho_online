package com.lib.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

	private final int mVerticalSpaceHeight;

	public HorizontalSpaceItemDecoration(int mVerticalSpaceHeight) {
		this.mVerticalSpaceHeight = mVerticalSpaceHeight;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		outRect.left = mVerticalSpaceHeight/2;
		outRect.right = mVerticalSpaceHeight/2;
	}
}
