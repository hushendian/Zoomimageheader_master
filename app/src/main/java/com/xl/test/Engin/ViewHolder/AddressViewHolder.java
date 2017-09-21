package com.xl.test.Engin.ViewHolder;

import android.util.Log;
import android.view.View;

import com.xl.test.Adapter.RecyleViewAdapter;
import com.xl.test.Base.BaseViewHolder;
import com.xl.test.Engin.TYPE_ADDRESS;

/**
 * Created by hushendian on 2017/8/30.
 */

public class AddressViewHolder extends BaseViewHolder<TYPE_ADDRESS> {
    public AddressViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(TYPE_ADDRESS model, int position, RecyleViewAdapter adapter) {
        Log.d("AddressViewHolder", "setUpView: "+model);
       
    }
}
