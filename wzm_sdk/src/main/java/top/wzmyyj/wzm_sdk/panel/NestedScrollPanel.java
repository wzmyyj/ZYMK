package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by wzm on 2018/05/05. email: 2209011667@qq.com
 */


public abstract class NestedScrollPanel extends InitPanel {


    private NestedScrollView mNestedScrollView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout mFrameLayout;
    protected View contentView;

    public NestedScrollPanel(Context context) {
        super(context);
    }


    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_ns, null);
        mFrameLayout = view.findViewById(R.id.frameLayout);
        mNestedScrollView = view.findViewById(R.id.nestedScrollView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(context.getResources()
                .getColor(R.color.colorBlue));

        contentView = getContentView();
        mNestedScrollView.addView(contentView);

        setView(mNestedScrollView, mSwipeRefreshLayout, mFrameLayout);
    }

    protected abstract void setView(NestedScrollView ns, SwipeRefreshLayout srl, FrameLayout layout);

    @NonNull
    protected abstract View getContentView();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                try {
                    update();
                    L.e("update data success");
                } catch (Exception e) {
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });
    }

    protected void update() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeView();
            }
        }, 600);

    }

    protected void changeView() {

    }
}
