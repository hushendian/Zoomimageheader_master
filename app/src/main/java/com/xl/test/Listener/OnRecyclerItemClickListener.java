package com.xl.test.Listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hushendian on 2017/8/29.
 */

public abstract class OnRecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private final GestureDetectorCompat mGestureDetectorCompat;


    public OnRecyclerItemClickListener(final RecyclerView recyclerView) {
        mGestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new
                GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder
                                    (childView);
                            onItemClick(viewHolder);
                        }
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder
                                    (childView);
                            onItemLongClick(viewHolder);
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    public abstract void onItemClick(RecyclerView.ViewHolder v);

    protected abstract void onItemLongClick(RecyclerView.ViewHolder v);

}
