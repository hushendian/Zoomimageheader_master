package com.xl.test;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by hushendian on 2017/9/11.
 */

public class CtrlLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CtrlLinearLayoutManager(Context context) {
        super(context);

    }

    public void setScrollEnabled(boolean isScrollEnabled) {
        this.isScrollEnabled = isScrollEnabled;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
