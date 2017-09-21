package com.xl.test.Base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

/**
 * Created by hushendian on 2017/8/29.
 */

public abstract class BaseListAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView
        .Adapter<VH> {
    protected final List<M> list;


    protected BaseListAdapter(List<M> list) {
        this.list = list;

    }

    @Override
    public int getItemCount() {
        Log.d("BaseListAdapter", "getItemCount: " + list.size());
        return list.size();
    }

    protected M getItem(int position) {

        return list.get(position);
    }

}
