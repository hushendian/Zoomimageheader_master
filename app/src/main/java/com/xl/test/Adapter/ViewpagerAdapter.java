package com.xl.test.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.xl.test.R;

import java.util.ArrayList;

/**
 * Created by hushendian on 2017/9/8.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private ArrayList<View> views;
    private int[] imgs = {R.mipmap.pizza, R.mipmap.pic2, R.mipmap.pic3};

    public ViewpagerAdapter(Context context) {
        views = new ArrayList<>();
        views.add(View.inflate(context, R.layout.item_img, null));
        views.add(View.inflate(context, R.layout.item_img, null));
        views.add(View.inflate(context, R.layout.item_img, null));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        views.get(position).findViewById(R.id.imageView).setBackgroundResource(imgs[position]);
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
