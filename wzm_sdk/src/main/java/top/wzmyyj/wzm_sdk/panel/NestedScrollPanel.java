package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import top.wzmyyj.wzm_sdk.R;

/**
 * Created by wzm on 2018/05/05. email: 2209011667@qq.com
 */


public abstract class NestedScrollPanel extends InitPanel {


    protected NestedScrollView mNestedScrollView;
    protected SmartRefreshLayout mRefreshLayout;
    protected FrameLayout mFrameLayout;
    protected View contentView;


    protected int delayed_r = 1500, delayed_l = 1000;

    public NestedScrollPanel(Context context) {
        super(context);
    }


    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_ns, null);
        mFrameLayout = view.findViewById(R.id.frameLayout);
        mNestedScrollView = view.findViewById(R.id.nestedScrollView);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setHeaderHeight(100);
        mRefreshLayout.setFooterHeight(100);
        mRefreshLayout.setPrimaryColorsId(R.color.colorRefresh, R.color.colorWhite);
        contentView = mInflater.inflate(getContentViewId(), null);
        mNestedScrollView.addView(contentView);

    }

    protected abstract int getContentViewId();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(delayed_l);
                loadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(delayed_r);
                refresh();
            }
        });
    }

    protected void refresh() {
        update();
    }

    protected void loadMore() {

    }

    protected void update() {


    }

    public void updateWithView() {
        mRefreshLayout.autoRefresh();
        refresh();
        mRefreshLayout.finishRefresh(delayed_r);
    }

    protected void updateView() {

    }
}
