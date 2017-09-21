package com.xl.test.UI;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xl.test.CtrlLinearLayoutManager;
import com.xl.test.Engin.ViewPager.ZoomHeaderViewPager;
import com.xl.test.R;

/**
 * Created by hushendian on 2017/9/8.
 */

public class ZoomHeaderView extends LinearLayout {
    private float mTouchSlop;//getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件
    private float iDownY;
    private ZoomHeaderViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private TextView mCloseTxt;
    private RelativeLayout mBottomView;
    private float mFirstY;
    private boolean isExpand = false;
    //图片放到最大时候的y
    private float mMaxY;
    private final int ANIMATE_LENGTH = 300;
    //底部栏的起始Y
    private int mBottomY;
    private final String TAG = "ZoomHeaderView";
    private boolean isFirst = false;

    public ZoomHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() + 50;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mViewPager = (ZoomHeaderViewPager) getChildAt(1);
        mFirstY = getY();
        mCloseTxt = (TextView) findViewById(R.id.tv_close);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //返回true时，表示消费事件
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                //event.getY()触摸点距离控件顶部的距离;getY()是view自身的顶边到其父布局顶边的距离+偏移量;
                // view向上滑动，则偏移量为负，view向下滑动，则偏移量为正
                float moveY = event.getY() - iDownY;
                float currentY = getY();
//                Log.d(TAG, "onTouchEvent: "+"=====moveY===="+moveY+"=========="+getTranslationY
// ());
                //view的移动时坐标应该是View的Y轴坐标加上移动的距离
                //(currentY + moveY > -getHeight() / 2)此时view的Y坐标应该超过view的一半
                if (currentY + moveY < 0 && currentY + moveY > -getHeight() / 2) {
                    //向上滑动viewpager整体移动
                    doPagerUp(moveY, currentY);
                }

                if (currentY + moveY > 0 && currentY + moveY < 800) {
                    //向下移动
                    doPagerDown(moveY, currentY);
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
                float upY = event.getY() - iDownY;
                Log.d(TAG, "onTouchEvent: "+"手指按下与手指抬起之间的距离"+upY+"图片的最大Y值"+mMaxY);
                float currentUpY = getY();
                //超过阀值 结束Activity
                if (upY + currentUpY > 220) {
                    finish();
                }
                //不在任何阀值  恢复

                Log.d(TAG, "onTouchEvent: " + (currentUpY + upY) + "========" + -getHeight() / 3);
                if (currentUpY + upY > -getHeight() / 4 && currentUpY + upY < 220) {
                    Log.d(TAG, "onTouchEvent: " + "下滑");
                    restore(upY + currentUpY);
                }
                //超过展开阀值
                if (upY + currentUpY < -getHeight() / 4) {
                    //+100是为了解决伸展开时，点击图片后，会晃动的问题（阀值没有计算清楚）;<mMaxY是为了保证图片滑出去后，会返回到顶部
//
                    if (upY + currentUpY + 100 < mMaxY) {
                        Log.d(TAG, "onTouchEvent: " + "超过最大值" + mMaxY);
                        expand(mMaxY);
                    } else {
                        Log.d(TAG, "onTouchEvent: " + "普通");
                        expand(upY + currentUpY);
                    }
                }
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                iDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                //移动距离大于系统认为滑动的移动距离时，拦截
                if (Math.abs(moveY - iDownY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void finish() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 1000);
        animation.setDuration(ANIMATE_LENGTH);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((Activity) getContext()).finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(animation);
    }

    private void doPagerUp(float moveY, float currentY) {
        //此时图片的Y轴坐标是View的原坐标+移动的距离
        mMaxY = currentY + moveY;
        //todo 鬼知道为什么mMaxY
//        setTranslationY(currentY +moveY );
        setY(currentY + moveY);
        mCloseTxt.setAlpha(0f);

    }

    private void doPagerDown(float moveY, float currentY) {
        //并没有什么用，只是为了让滑动的时候设置text的透明度
        int pos = mViewPager.getCurrentItem();
        View view = mViewPager.getChildAt(pos);
        view.setTranslationY((currentY + moveY) / 4);
        mCloseTxt.setAlpha(view.getY() / 76);
        if (currentY + moveY < 800 && currentY + moveY > 300) {
            mCloseTxt.setText("松开关闭");
        } else {
            mCloseTxt.setText("下滑关闭");
        }
    }

    public void restore(float y) {
        mCloseTxt.setAlpha(0f);
        if (y > mFirstY) {
            //下滑的时候显示下滑关闭提示
            final ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mCloseTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
                }
            });
            animator.setDuration(ANIMATE_LENGTH);
            animator.start();
        }
        mRecyclerView.scrollToPosition(0);
        ValueAnimator restoreVa = ValueAnimator.ofFloat(y, mFirstY);
        restoreVa.setInterpolator(new DecelerateInterpolator());
        restoreVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float y = (float) valueAnimator.getAnimatedValue();
                setTranslationY(y);
                isExpand = false;
                mViewPager.canScroll = true;
            }
        });
        restoreVa.setDuration(ANIMATE_LENGTH);
        restoreVa.start();
        //禁止滑动
        ((CtrlLinearLayoutManager) mRecyclerView.getLayoutManager()).setScrollEnabled(false);
        //底部隐藏
        ValueAnimator bootomVa = ValueAnimator.ofFloat(mBottomView.getY(), mBottomY + mBottomView
                .getHeight());
        bootomVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBottomView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        bootomVa.start();
    }

    private void expand(float y) {
        mRecyclerView.scrollToPosition(0);
        Log.d(TAG, "expand: " + "======" + y + "=======" + -getHeight() / 3);
        ValueAnimator va = ValueAnimator.ofFloat(y, -getHeight() / 3f);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float y = (float) valueAnimator.getAnimatedValue();
                mViewPager.canScroll = false;
                setTranslationY(y);
                isExpand = true;
            }
        });
        va.setInterpolator(new DecelerateInterpolator());
        va.setDuration(ANIMATE_LENGTH);
        va.start();
        //允许滑动
        ((CtrlLinearLayoutManager) mRecyclerView.getLayoutManager()).setScrollEnabled(true);
        //底部上移
        ValueAnimator bottomAnimate = ValueAnimator.ofFloat(mBottomView.getY(), mBottomY);
        bottomAnimate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBottomView.setY((Float) animation.getAnimatedValue());
            }
        });

        bottomAnimate.start();

    }

    public ZoomHeaderViewPager getViewPager() {
        return mViewPager;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public boolean isExpand() {
        return isExpand;
    }


    public RelativeLayout getmBottomView() {
        return mBottomView;
    }

    public void setmBottomView(RelativeLayout mBottomView, int bottomY) {
        this.mBottomView = mBottomView;
        mBottomY = bottomY;
    }
}
