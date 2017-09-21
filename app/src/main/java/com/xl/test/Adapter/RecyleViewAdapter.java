package com.xl.test.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xl.test.Base.BaseListAdapter;
import com.xl.test.Base.BaseViewHolder;
import com.xl.test.Engin.TypeFactoryForList;
import com.xl.test.Interface.TypeFactory;
import com.xl.test.Interface.Visitable;

import java.util.List;

/**
 * Created by hushendian on 2017/8/29.
 */

public class RecyleViewAdapter extends BaseListAdapter<Visitable, BaseViewHolder> {
    private final TypeFactory typeFactory;

    public RecyleViewAdapter(List<Visitable> list) {
        super(list);
        this.typeFactory = new TypeFactoryForList();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = View.inflate(context, viewType, null);
        return typeFactory.createViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Log.d("RecyleViewAdapter", "onBindViewHolder: " + list.get(position));
        if (holder != null)
            holder.setUpView(list.get(position), position, this);


    }


    @Override
    public int getItemViewType(int position) {

        return list.get(position).type(typeFactory);
    }


}
