package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by wzm on 2018/04/23. email: 2209011667@qq.com
 */


public abstract class RecyclerPanel<T> extends InitPanel
        implements MultiItemTypeAdapter.OnItemClickListener {

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected FrameLayout mFrameLayout;
    protected List<T> mData = new ArrayList<>();
    protected List<IVD<T>> mIVD = new ArrayList<>();
    protected HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    protected View mHeader;
    protected View mFooter;


    public RecyclerPanel(Context context) {
        super(context);
    }


    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_sr, null);
        mFrameLayout = view.findViewById(R.id.frameLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(context.getResources()
                .getColor(R.color.colorPrimary));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        setData();
        setIVD(mIVD);
        setHeader();
        setFooter();
    }


    protected abstract void setData();


    protected abstract void setIVD(List<IVD<T>> ivd);


    protected void setHeader() {
    }


    protected void setFooter() {
    }


    @Override
    protected void initData() {

        MultiItemTypeAdapter mAdapter = new MultiItemTypeAdapter(context, mData);

        for (ItemViewDelegate<T> ivd : mIVD) {
            mAdapter.addItemViewDelegate(ivd);
        }

        mAdapter.setOnItemClickListener(this);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        if (mHeader != null)
            mHeaderAndFooterWrapper.addHeaderView(mHeader);
        if (mFooter != null)
            mHeaderAndFooterWrapper.addFootView(mFooter);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
    }


    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

    }

    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateWithView();
            }
        });

    }

    public void updateWithView() {
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

    public void update() {
        mData.clear();
        setData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 600);

    }

    protected void notifyDataSetChanged() {
        mHeaderAndFooterWrapper.notifyDataSetChanged();
        upHeaderAndFooter();
    }

    protected void upHeaderAndFooter() {

    }


}
