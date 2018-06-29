package top.wzmyyj.wzm_sdk.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import top.wzmyyj.wzm_sdk.R;

/**
 * Created by wzm on 2017/10/23. email: 2209011667@qq.com
 */

public class ArcMenu extends ViewGroup implements OnClickListener {

    private static final int POS_LEFL_TOP = 0;
    private static final int POS_LEFL_BOTTOM = 1;
    private static final int POS_RIGHT_TOP = 2;
    private static final int POS_RIGHT_BOTTOM = 3;

    private Position mPositon = Position.RIGHT_BOTTOM;
    private Status mStatus = Status.CLOSE;
    private int mRadius;
    private View mCButton;
    private OnMenuItemClickListener mMenuItemClickListener;

    public void setOnMenuItemClickListener(
            OnMenuItemClickListener mMenuItemClickListener) {
        this.mMenuItemClickListener = mMenuItemClickListener;
    }

    public enum Status {
        OPEN, CLOSE
    }

    public boolean isOpen() {
        return mStatus == Status.OPEN;
    }

    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

    public interface OnMenuItemClickListener {
        void onClick(View view, int pos);
    }

    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                100, getResources().getDisplayMetrics());
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ArcMenu, defStyle, 0);
        int pos = a.getInt(R.styleable.ArcMenu_position, POS_LEFL_BOTTOM);
        switch (pos) {
            case POS_LEFL_TOP:
                mPositon = Position.LEFT_TOP;
                break;
            case POS_LEFL_BOTTOM:
                mPositon = Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPositon = Position.RIGHT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPositon = Position.RIGHT_BOTTOM;
                break;
        }
        mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                        getResources().getDisplayMetrics()));

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutCButton();
            layoutItemButton();
        }
    }

    private void layoutCButton() {
        int l = 0, t = 0;
        mCButton = getChildAt(0);
        mCButton.setOnClickListener(this);
        int width = mCButton.getMeasuredWidth();
        int height = mCButton.getMeasuredHeight();
        switch (mPositon) {
            case LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                l = getMeasuredWidth() - width;
                t = 0;
                break;
            case RIGHT_BOTTOM:
                l = getMeasuredWidth() - width;
                t = getMeasuredHeight() - height;
                break;
        }
        mCButton.layout(l, t, l + width, t + height);

    }

    private void layoutItemButton() {
        int count = getChildCount();
        for (int i = 0; i < count - 1; i++) {
            View child = getChildAt(i + 1);
            child.setVisibility(View.GONE);
            // child.setOnClickListener(this);
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();
            if (mPositon == Position.LEFT_BOTTOM
                    || mPositon == Position.RIGHT_BOTTOM) {
                ct = getMeasuredHeight() - cHeight - ct;
            }
            if (mPositon == Position.RIGHT_TOP
                    || mPositon == Position.RIGHT_BOTTOM) {
                cl = getMeasuredWidth() - cWidth - cl;
            }
            child.layout(cl, ct, cl + cWidth, ct + cHeight);
        }

    }

    @Override
    public void onClick(View v) {
        rotateCButton(v, 0f, 360f, 200);
        toggleMenu(200);

    }

    public void toggleMenu(int duration) {
        int count = getChildCount();
        for (int i = 0; i < count - 1; i++) {
            final View childView = getChildAt(i + 1);
            childView.setVisibility(View.VISIBLE);
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));
            int xflag = 1, yflag = 1;
            if (mPositon == Position.LEFT_TOP
                    || mPositon == Position.LEFT_BOTTOM) {
                xflag = -1;
            }
            if (mPositon == Position.LEFT_TOP || mPositon == Position.RIGHT_TOP) {
                yflag = -1;
            }
            AnimationSet animset = new AnimationSet(true);
            Animation tranAnim = null;
            if (mStatus == Status.CLOSE) {// to open
                tranAnim = new TranslateAnimation(xflag * cl, 0, yflag * ct, 0);
                childView.setClickable(true);
                childView.setFocusable(true);
            } else {// to close
                tranAnim = new TranslateAnimation(0, xflag * cl, 0, yflag * ct);
                childView.setClickable(false);
                childView.setFocusable(false);
            }
            tranAnim.setFillAfter(true);
            tranAnim.setDuration(duration);
            tranAnim.setStartOffset(i * 150 / count);
            tranAnim.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation arg0) {

                }

                @Override
                public void onAnimationRepeat(Animation arg0) {

                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    if (mStatus == Status.CLOSE) {
                        childView.setVisibility(View.GONE);
                    }
                }
            });
            RotateAnimation rotateAnim = new RotateAnimation(0, 720,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(duration);
            rotateAnim.setFillAfter(true);

            animset.addAnimation(rotateAnim);
            animset.addAnimation(tranAnim);
            childView.startAnimation(animset);
            final int pos = i + 1;
            childView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mMenuItemClickListener != null) {
                        mMenuItemClickListener.onClick(childView, pos);
                        menuItemAnim(pos - 1);
                        changeStatus();
                    }
                }
            });

        }
        changeStatus();
    }

    protected void menuItemAnim(int pos) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View childView = getChildAt(i + 1);
            if (i == pos) {
                childView.startAnimation(scaleBigAnim(100));
            } else {
                childView.startAnimation(scaleSmallAnim(100));
            }
            childView.setClickable(false);
            childView.setFocusable(false);
        }

    }

    private Animation scaleBigAnim(int duration) {
        AnimationSet animset = new AnimationSet(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
        animset.addAnimation(scaleAnim);
        animset.addAnimation(alphaAnim);
        animset.setDuration(duration);
        animset.setFillAfter(true);
        return animset;
    }

    private Animation scaleSmallAnim(int duration) {
        AnimationSet animset = new AnimationSet(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
        animset.addAnimation(scaleAnim);
        animset.addAnimation(alphaAnim);
        animset.setDuration(duration);
        animset.setFillAfter(true);
        return animset;
    }

    private void changeStatus() {
        mStatus = mStatus == Status.CLOSE ? Status.OPEN : Status.CLOSE;
    }

    private void rotateCButton(View v, float start, float end, int duration) {
        RotateAnimation anim = new RotateAnimation(start, end,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        v.startAnimation(anim);

    }

}