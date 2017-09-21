package com.xl.test.Engin;

import android.view.View;

import com.xl.test.Base.BaseViewHolder;
import com.xl.test.Engin.ViewHolder.AddressViewHolder;
import com.xl.test.Engin.ViewHolder.CommentViewHolder;
import com.xl.test.Engin.ViewHolder.HomeViewHolder;
import com.xl.test.Engin.ViewHolder.RecomentViewHolder;
import com.xl.test.Engin.ViewHolder.TimeViewHolder;
import com.xl.test.Interface.TypeFactory;
import com.xl.test.R;


/**
 * Created by hushendian on 2017/8/30.
 */

public class TypeFactoryForList implements TypeFactory {
    private final int TYPE_ADDRESS = R.layout.item_address;
    private final int TYPE_COMMENT = R.layout.item_comment;
    private final int TYPE_RECOMMEND = R.layout.item_recommend;
    private final int TYPE_TIME = R.layout.item_time;
    private final int TYPE_HOME = R.layout.item_home;

    @Override
    public int type(TYPE_ADDRESS TYPEADDRESS) {
        return TYPE_ADDRESS;
    }

    @Override
    public int type(TYPE_TIME TYPE_COMMENT) {
        return TYPE_TIME;
    }

    @Override
    public int type(TYPE_RECOMMEND TYPERECOMMEND) {
        return TYPE_RECOMMEND;
    }

    @Override
    public int type(TYPE_COMMENT comment) {
        return TYPE_COMMENT;
    }

    @Override
    public int type(TYPE_HOME home) {
        return TYPE_HOME;
    }


    @Override
    public BaseViewHolder createViewHolder(int viewType, View view) {

        if (TYPE_ADDRESS == viewType) {
            return new AddressViewHolder(view);
        } else if (TYPE_COMMENT == viewType) {
            return new CommentViewHolder(view);
        } else if (TYPE_RECOMMEND == viewType) {
            return new RecomentViewHolder(view);
        } else if (TYPE_TIME == viewType) {
            return new TimeViewHolder(view);
        } else if (TYPE_HOME == viewType) {
            return new HomeViewHolder(view);
        }
        return null;
    }
}