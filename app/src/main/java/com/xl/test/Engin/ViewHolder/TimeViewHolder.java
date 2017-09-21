package com.xl.test.Engin.ViewHolder;

import android.util.Log;
import android.view.View;

import com.xl.test.Adapter.RecyleViewAdapter;
import com.xl.test.Base.BaseViewHolder;
import com.xl.test.Engin.TYPE_TIME;

/**
 * Created by hushendian on 2017/8/30.
 */

public class TimeViewHolder extends BaseViewHolder<TYPE_TIME> {
    public TimeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(TYPE_TIME model, int position, RecyleViewAdapter adapter) {
        Log.d("AddressViewHolder", "setUpView: " + model);

    }
}
