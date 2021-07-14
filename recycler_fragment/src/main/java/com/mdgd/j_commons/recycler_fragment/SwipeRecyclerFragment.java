package com.mdgd.j_commons.recycler_fragment;

import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.commons.contract.progress.ProgressDecor;

/**
 * Created by Max
 * on 02/01/2018.
 */

public abstract class SwipeRecyclerFragment<X extends FragmentContract.Presenter, Y extends FragmentContract.Host, ITEM>
        extends RecyclerFragment<X, Y, ITEM> implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout swipe;

    public SwipeRecyclerFragment() {
        super();
        setHasProgress(true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_swipe_recycler;
    }

    @Override
    protected void initViews(View v) {
        super.initViews(v);
        swipe = v.findViewById(R.id.fragment_swipe);
        swipe.setOnRefreshListener(this);
    }

    @Override
    protected ProgressDecor createProgressView(String title, String message) {
        return new ProgressDecor() {
            @Override
            public void show() {
                if (swipe != null) swipe.setRefreshing(true);
            }

            @Override
            public boolean isShowing() {
                return swipe != null && swipe.isRefreshing();
            }

            @Override
            public void dismiss() {
                if (swipe != null) swipe.setRefreshing(false);
            }
        };
    }

    @Override
    public void onRefresh() {
    }
}
