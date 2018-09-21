package top.wzmyyj.wzm_sdk.tools;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by yyj on 2018/08/10. email: 2209011667@qq.com
 * 动画封装类。
 */

public class A {

    private AnimationSet animSet;


    public A() {
        animSet = new AnimationSet(true);
    }

    public static A create() {
        return new A();
    }

    public A t(int fromX, int toX, int fromY, int toY) {
        TranslateAnimation t = new TranslateAnimation(fromX, toX, fromY, toY);
        animSet.addAnimation(t);
        return this;
    }

    public A r(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,
               int pivotYType, float pivotYValue) {
        RotateAnimation r = new RotateAnimation(fromDegrees, toDegrees,
                pivotXType, pivotXValue,
                pivotYType, pivotYValue);
        animSet.addAnimation(r);
        return this;
    }

    public A s(float fromX, float toX, float fromY, float toY,
               int pivotXType, float pivotXValue,
               int pivotYType, float pivotYValue) {
        ScaleAnimation s = new ScaleAnimation(fromX, toX, fromY, toY,
                pivotXType, pivotXValue,
                pivotYType, pivotYValue);
        animSet.addAnimation(s);
        return this;
    }

    public A a(float fromAlpha, float toAlpha) {
        AlphaAnimation a = new AlphaAnimation(fromAlpha, toAlpha);
        animSet.addAnimation(a);
        return this;
    }

    public A duration(long duration) {
        animSet.setDuration(duration);
        return this;
    }

    public A repeatMode(int repeatMode) {
        animSet.setRepeatMode(repeatMode);
        return this;
    }

    public A fillAfter(boolean fillAfter) {
        animSet.setFillAfter(fillAfter);
        return this;
    }

    public A fillBefore(boolean fillBefore) {
        animSet.setFillBefore(fillBefore);
        return this;
    }

    public A setStartOffset(long startOffset) {
        animSet.setStartOffset(startOffset);
        return this;
    }

    public A listener(Animation.AnimationListener listener) {
        animSet.setAnimationListener(listener);
        return this;
    }

    public A reset() {
        animSet.reset();
        return this;
    }

    public AnimationSet getAnim() {
        return animSet;
    }


    public void into(View view) {
        view.setAnimation(animSet);
    }
}
