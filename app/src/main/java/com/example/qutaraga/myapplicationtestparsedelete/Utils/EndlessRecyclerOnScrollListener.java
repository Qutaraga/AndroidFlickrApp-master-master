package com.example.qutaraga.myapplicationtestparsedelete.Utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private static boolean loading = false;

    int firstVisibleItem, visibleItemCount = 10, totalItemCount;

    private int current_page = 2;

    //private LinearLayoutManager mLinearLayoutManager;

    private GridLayoutManager gridLayoutManager;

    public EndlessRecyclerOnScrollListener(GridLayoutManager gr) {
        this.gridLayoutManager = gr;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = gridLayoutManager.getItemCount();
        firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

       if(!isLoading() && totalItemCount <= visibleItemCount+firstVisibleItem) {
           setLoading(true);
           onLoadMore(current_page);
           current_page++;
       }
    }
    public static boolean isLoading() {
        return loading;
    }

    public static void setLoading(boolean loading) {
        EndlessRecyclerOnScrollListener.loading = loading;
    }

    public abstract void onLoadMore(int current_page);

    public void setGridLayoutManager(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }
}