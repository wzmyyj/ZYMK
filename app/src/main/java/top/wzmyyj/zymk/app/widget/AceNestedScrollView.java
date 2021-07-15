package top.wzmyyj.zymk.app.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import java.lang.reflect.Field;

import top.wzmyyj.wzm_sdk.widget.FixedSpeedScroller;
import top.wzmyyj.zymk.R;
import top.wzmyyj.wzm_sdk.utils.DensityUtil;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;


/**
 * Created by yyj on 2018/07/16. email: 2209011667@qq.com
 * Ace 艾斯，四皇之一，月光岛领主。
 */
public class AceNestedScrollView extends NestedScrollView {

    private ViewGroup mTop;
    private FrameLayout mTab;
    private FrameLayout mBg;

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
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            FrameLayout mWrapper = (FrameLayout) getChildAt(0);
            mBg = (FrameLayout) mWrapper.getChildAt(0);
            LinearLayout mContent = (LinearLayout) mWrapper.getChildAt(1);
            mTab = (FrameLayout) mWrapper.getChildAt(2);
            mTop = (ViewGroup) mWrapper.getChildAt(3);
            int t = mTop.getMeasuredHeight();
            int a = mTab.getMeasuredHeight();
            int b = StatusBarUtil.StatusBarHeight + DensityUtil.pt2px(getContext(), 200);
            FrameLayout.LayoutParams params_bg = (FrameLayout.LayoutParams) mBg.getLayoutParams();
            params_bg.height = b;
            View v0 = this.findViewById(R.id.v_top_0);
            View v1 = this.findViewById(R.id.v_top_1);
            StatusBarUtil.fitsStatusBarView(v0, v1);
            int r = t + StatusBarUtil.StatusBarHeight;
            mUpHeight = b - r;
            FrameLayout.LayoutParams params_tab = (FrameLayout.LayoutParams) mTab.getLayoutParams();
            params_tab.topMargin = b;
            FrameLayout.LayoutParams params_c = (FrameLayout.LayoutParams) mContent.getLayoutParams();
            params_c.topMargin = b + a;
            once = true;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
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
                    } else {
                        myRunnable.setAB(scrollY, 0);
                    }
                } else {
                    if (scrollY < mUpHeight * 4 / 5) {
                        myRunnable.setAB(scrollY, 0);
                    } else {
                        myRunnable.setAB(scrollY, mUpHeight);
                    }
                }
                handler.postDelayed(myRunnable, 1);
                return true;
            case MotionEvent.ACTION_DOWN:
                if (getScrollY() > mUpHeight) break;
                handler.removeCallbacks(myRunnable);
                break;
        }
        return super.onTouchEvent(ev);
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MyRunnable myRunnable = new MyRunnable();

    private class MyRunnable implements Runnable {
        int a = 0, b = 0;
        int c = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getContext().getResources().getDisplayMetrics());

        public void setAB(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            if (Math.abs(a - b) < c) {
                AceNestedScrollView.this.scrollTo(0, b);
                isOpen = b == 0;
                handler.removeCallbacks(myRunnable);
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = t * 1.0f / mUpHeight;
        mBg.setTranslationY(1.0f * t / 2);
        mTop.setTranslationY(t);
        mTop.getChildAt(1).setAlpha(scale);
        if (t >= mUpHeight) {
            mTab.setTranslationY(t - mUpHeight);
        } else {
            mTab.setTranslationY(0);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(myRunnable);
    }
}
