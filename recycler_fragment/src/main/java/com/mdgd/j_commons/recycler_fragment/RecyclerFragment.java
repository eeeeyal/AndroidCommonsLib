package com.mdgd.j_commons.recycler_fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mdgd.commons.contract.fragment.FragmentContract;
import com.mdgd.j_commons.fragment.HostedFragment;
import com.mdgd.j_commons.recycler.CommonRecyclerAdapter;

/**
 * Created by Max
 * on 02/01/2018.
 */

public abstract class RecyclerFragment<X extends FragmentContract.Presenter, Y extends FragmentContract.Host, ITEM>
        extends HostedFragment<X, Y> implements CommonRecyclerAdapter.IOnItemClickListener<ITEM> {

    protected CommonRecyclerAdapter<ITEM> adapter;
    protected RecyclerView recycler;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected void initViews(View v) {
        recycler = v.findViewById(R.id.fragment_recycler);
        adapter = getAdapter();
        recycler.setAdapter(adapter);
    }

    protected abstract CommonRecyclerAdapter<ITEM> getAdapter();
}
