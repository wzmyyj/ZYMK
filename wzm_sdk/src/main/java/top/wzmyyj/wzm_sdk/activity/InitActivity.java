package top.wzmyyj.wzm_sdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by wzm on 2018/04/22. email: 2209011667@qq.com
 */


public abstract class InitActivity extends SwipeBackActivity {

    private SwipeBackLayout mSwipeBackLayout;
    protected Context context;
    protected Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(150);
        initSome(savedInstanceState);
        initView();
        initData();
        initListener();
        initEvent();
    }

    protected void initSome(Bundle savedInstanceState) {
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected void initEvent() {
    }


//    /**
//     * 当按下返回键时
//     */
//    @Override
//    public void onBackPressed() {
//        scrollToFinishActivity();//左滑退出activity
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        context = null;
        activity = null;
    }
}
