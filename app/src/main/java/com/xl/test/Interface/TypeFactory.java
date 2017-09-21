package com.xl.test.Interface;

import android.view.View;

import com.xl.test.Base.BaseViewHolder;
import com.xl.test.Engin.TYPE_ADDRESS;
import com.xl.test.Engin.TYPE_COMMENT;
import com.xl.test.Engin.TYPE_HOME;
import com.xl.test.Engin.TYPE_RECOMMEND;
import com.xl.test.Engin.TYPE_TIME;

/**
 * Created by hushendian on 2017/8/30.
 */

public interface TypeFactory {
    int type(TYPE_ADDRESS address);

    int type(TYPE_TIME time);

    int type(TYPE_RECOMMEND recommend);

    int type(TYPE_COMMENT comment);

    int type(TYPE_HOME home);

    BaseViewHolder createViewHolder(int viewType, View view);
}
