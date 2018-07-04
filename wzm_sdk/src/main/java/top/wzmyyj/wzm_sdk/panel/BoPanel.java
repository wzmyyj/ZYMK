package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ImageViewPagerAdapter;

/**
 * Created by wzm on 2018/07/03. email: 2209011667@qq.com
 */

public abstract class BoPanel extends InitPanel {

    protected ViewPager mViewPager;
    protected TextView mTV;
    private LinearLayout mLL;
    private int size = 3;

    public BoPanel(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_bo, null);
        mViewPager = view.findViewById(R.id.viewPager);
        mTV = view.findViewById(R.id.tv_1);
        mLL = view.findViewById(R.id.ll_1);
    }

    @Override
    protected void initData() {
        size = getSize();
        initBo();
        setBoData();
    }

    private int lastPosition;

    @Override
    protected void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % size;
                mTV.setText(mDosc[position]);
                mLL.getChildAt(position).setEnabled(true);
                mLL.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected abstract int getSize();

    public abstract void setBoData();

    protected List<ImageView> mImageList;

    protected String[] mDosc;


    private void initBo() {
        mImageList = new ArrayList<>();
        mDosc = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                ImageView image = new ImageView(context);
                mImageList.add(image);
                ImageView point = new ImageView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        20, 20);
                params.rightMargin = 15;
                point.setLayoutParams(params);
                point.setBackgroundResource(R.drawable.point_bg);
                if (i == 0) {
                    point.setEnabled(true);
                } else {
                    point.setEnabled(false);
                }
                mDosc[i] = "这是第" + i + "张图的描述。。";
                mLL.addView(point);
            }
            mTV.setText(mDosc[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mViewPager.setAdapter(new ImageViewPagerAdapter(mImageList));

        //利用反射修改ViewPager切换速度
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller =
                    new FixedSpeedScroller(mViewPager.getContext(), 2 * 1000);
            mField.set(mViewPager, mScroller);
        } catch (Exception e) {

        }
    }

    class FixedSpeedScroller extends Scroller {
        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, int duration) {
            super(context);
            this.mDuration = duration;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    private Handler mHandler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                mHandler.postDelayed(myRunnable, 4000);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.removeCallbacks(myRunnable);
        mHandler.postDelayed(myRunnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(myRunnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }
}
