package top.wzmyyj.wzm_sdk.widget;

import android.content.Context;
import android.widget.Scroller;

/**
 * Created by yyj on 2018/07/16. email: 2209011667@qq.com
 */
public class FixedSpeedScroller extends Scroller {

    private final int mDuration;

    public FixedSpeedScroller(Context context, int duration) {
        super(context);
        this.mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
