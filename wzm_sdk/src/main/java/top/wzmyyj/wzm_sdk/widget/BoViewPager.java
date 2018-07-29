package top.wzmyyj.wzm_sdk.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.lang.reflect.Field;


/**
 * Created by yyj on 2018/07/25. email: 2209011667@qq.com
 */

public class BoViewPager extends ViewPager {
    public BoViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //利用反射修改ViewPager切换速度。
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller =
                    new FixedSpeedScroller(this.getContext(), 2 * 1000);
            mField.set(this, mScroller);
        } catch (Exception e) {

        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //利用反射解决ViewPager在RecyclerView中的Bug。
//        try {
//            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
//            mFirstLayout.setAccessible(true);
//            mFirstLayout.set(this, false);
            getAdapter().notifyDataSetChanged();
            setCurrentItem(getCurrentItem(), false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}
