package top.wzmyyj.zymk.common.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import top.wzmyyj.wzm_sdk.widget.FixedSpeedScroller;


/**
 * Created by yyj on 2018/07/16. email: 2209011667@qq.com
 */

public class AceNestedScrollView extends NestedScrollView {

    private FrameLayout mWapper;
    private ViewGroup mTop;
    private ViewGroup mTab;
    private View mBg;
    private View mContent;


    private int mTopHeight = 50;
    private int mBgHeight;
    private int mUpHeight;
    private boolean once;
    private boolean isOpen = true;

    public AceNestedScrollView(@NonNull Context context) {
        this(context, null, 0);
    }

    public AceNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AceNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            Field mField = NestedScrollView.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller =
                    new FixedSpeedScroller(this.getContext(), 2 * 1000);
            mField.set(this, mScroller);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        if (changed) {
//            this.scrollTo(mUpHeight, 0);
//        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            mWapper = (FrameLayout) getChildAt(0);
            mBg = mWapper.getChildAt(0);
            mContent = mWapper.getChildAt(1);
            mTab = (ViewGroup) mWapper.getChildAt(2);
            mTop = (ViewGroup) mWapper.getChildAt(3);

            int h = getMeasuredHeight();
            int t = mTop.getMeasuredHeight();
            int a = mTab.getMeasuredHeight();
            int b = mBg.getMeasuredHeight();
            mUpHeight = b - t;
//            mContent.getLayoutParams().height = h - t - a;
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                if (scrollY >= mUpHeight) break;

                if (isOpen) {
                    if (scrollY > mUpHeight / 5) {
                        myRunnable.setAB(scrollY, mUpHeight);
//                        this.scrollTo(0, mUpHeight);
                    } else {
                        myRunnable.setAB(scrollY, 0);
//                        this.scrollTo(0, 0);
                    }
                } else {
                    if (scrollY < mUpHeight * 4 / 5) {
                        myRunnable.setAB(scrollY, 0);
//                        this.scrollTo(0, mUpHeight);
                    } else {
                        myRunnable.setAB(scrollY, mBgHeight);
//                        this.scrollTo(0, 0);
                    }
                }
                handler.postDelayed(myRunnable, 1);
                return true;
            case MotionEvent.ACTION_DOWN:
                if (getScrollY() > mUpHeight) break;
                handler.removeMessages(0);
                break;


        }
        return super.onTouchEvent(ev);
    }

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();


    private class MyRunnable implements Runnable {

        int a = 0, b = 0, c = 15;

        public void setAB(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            if (Math.abs(a - b) < c) {
                AceNestedScrollView.this.scrollTo(0, b);
                isOpen = b == 0;
                handler.removeMessages(0);
                return;
            } else if (a > b) {
                a -= c;
            } else {
                a += c;
            }

            AceNestedScrollView.this.scrollTo(0, a);
            handler.postDelayed(myRunnable, 1);
        }

    }


    private void scrollToY(int y) {

        this.scrollTo(0, y);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        float scale = t * 1.0f / mUpHeight;// 0`1
//        float upAlpha = (1 - 1.2f * scale);
//        mTv2.setText("" + t / 2);
//        mTv.setText("" + t + "   " + mUpHeight);
//
        mBg.setTranslationY(t / 2);

        mTop.setTranslationY(t);
        mTop.getChildAt(1).setAlpha(scale);

        if (t >= mUpHeight) {
            mTab.setTranslationY(t - mUpHeight);
        } else {
            mTab.setTranslationY(0);
        }

    }
}
