package com.xl.test;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.xl.test.Adapter.RecyleViewAdapter;
import com.xl.test.Adapter.ViewpagerAdapter;
import com.xl.test.Base.BaseActivity;
import com.xl.test.Engin.TYPE_ADDRESS;
import com.xl.test.Engin.TYPE_COMMENT;
import com.xl.test.Engin.TYPE_RECOMMEND;
import com.xl.test.Engin.TYPE_TIME;
import com.xl.test.Interface.Visitable;
import com.xl.test.UI.ZoomHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hushendian on 2017/9/8.
 */

public class MainActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.zoomHeader)
    ZoomHeaderView zoomHeader;
    @BindView(R.id.rv_bottom)
    RelativeLayout mBottomView;
    private boolean isFirst = true;

    private List<Visitable> list;
    public static int bottomY;

    @Override
    protected void initView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        CtrlLinearLayoutManager layoutManager = new CtrlLinearLayoutManager(this);
        layoutManager.setScrollEnabled(false);
    }

    @Override
    protected void loadData() {

        initData();
        viewpager.setAdapter(new ViewpagerAdapter(this));
        viewpager.setOffscreenPageLimit(4);

        recyclerView.setLayoutManager(new CtrlLinearLayoutManager(this));
        recyclerView.setAdapter(new RecyleViewAdapter(list));
        recyclerView.setAlpha(1f);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new TYPE_ADDRESS(""));
        list.add(new TYPE_TIME(1));
        list.add(new TYPE_RECOMMEND());
        for (int i = 0; i < 5; i++) {
            list.add(new TYPE_COMMENT(""));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isFirst) {
            for (int i = 0; i < viewpager.getChildCount(); i++) {
                View v = viewpager.getChildAt(i).findViewById(R.id.ll_bottom);
                v.setY(viewpager.getChildAt(i).findViewById(R.id.imageView).getHeight());
                v.setX(MarginConfig.MARGIN_LEFT_RIGHT);
                //触发一次dependency变化，让按钮归位
                zoomHeader.setY(zoomHeader.getY() - 1);
                isFirst = false;
            }
        }
        //隐藏底部栏]
        bottomY = (int) mBottomView.getY();
        mBottomView.setTranslationY(mBottomView.getY() + mBottomView.getHeight());
        zoomHeader.setmBottomView(mBottomView, bottomY);
    }

    @Override
    protected void BackPressed() {
        if (zoomHeader.isExpand()) {
            zoomHeader.restore(zoomHeader.getY());
        } else {
            finish();
        }
    }
}
