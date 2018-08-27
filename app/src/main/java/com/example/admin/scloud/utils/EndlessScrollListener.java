package com.example.admin.scloud.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean mIsLoading = true;
    private int mPreviousTotal = 0;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = mLinearLayoutManager.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (mIsLoading && totalItemCount > mPreviousTotal) {
            mIsLoading = false;
            mPreviousTotal = totalItemCount;
        }

        if (!mIsLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            onLoadMore(totalItemCount);
            mIsLoading = true;
        }
    }

    protected abstract void onLoadMore(int offset);
}
