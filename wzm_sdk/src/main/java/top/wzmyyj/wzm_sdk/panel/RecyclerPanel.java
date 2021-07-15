package top.wzmyyj.wzm_sdk.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;

/**
 * Created by wzm on 2018/04/23. email: 2209011667@qq.com
 */
public abstract class RecyclerPanel<T> extends InitPanel implements MultiItemTypeAdapter.OnItemClickListener {

    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;
    protected FrameLayout mFrameLayout;
    protected List<T> mData = new ArrayList<>();
    protected List<IVD<T>> mIVD = new ArrayList<>();
    protected HeaderAndFooterWrapper<T> mWrapperAdapter;
    protected View mHeader;
    protected View mFooter;
    protected View mEmpty;
    protected FrameLayout mEmptyLayout;
    protected int delayed_r = 1500, delayed_l = 1000;

    public RecyclerPanel(Context context) {
        super(context);
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.panel_sr, null);
        mFrameLayout = view.findViewById(R.id.frameLayout);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mEmptyLayout = view.findViewById(R.id.fl_empty);
        mRefreshLayout.setHeaderHeight(100);
        mRefreshLayout.setFooterHeight(100);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setPrimaryColorsId(R.color.colorRefresh, R.color.colorWhite);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        setFirstData();
        setIVD(mIVD);
        setHeader();
        setFooter();
        setEmpty();
        if (mEmpty != null) mEmptyLayout.addView(mEmpty);
    }

    protected void setFirstData() {
    }

    protected abstract void setIVD(List<IVD<T>> ivd);

    protected void setHeader() {
    }

    protected void setFooter() {
    }

    protected void setEmpty() {
    }

    @Override
    protected void initData() {
        MultiItemTypeAdapter<T> mAdapter = new MultiItemTypeAdapter<T>(context, mData) {
            @Override
            public void onViewRecycled(@NonNull ViewHolder holder) {
                super.onViewRecycled(holder);
                viewRecycled(holder);
            }
        };
        for (ItemViewDelegate<T> ivd : mIVD) {
            mAdapter.addItemViewDelegate(ivd);
        }
        mAdapter.setOnItemClickListener(this);
        mWrapperAdapter = new HeaderAndFooterWrapper<>(mAdapter);
        if (mHeader != null)
            mWrapperAdapter.addHeaderView(mHeader);
        if (mFooter != null)
            mWrapperAdapter.addFootView(mFooter);
        mRecyclerView.setAdapter(mWrapperAdapter);
    }

    public void viewRecycled(@NonNull ViewHolder holder) {
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

    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    public void updateWithView() {
        mRefreshLayout.autoRefresh();
        refresh();
        mRefreshLayout.finishRefresh(delayed_r);
    }

    public abstract void update();

    // 排序
    protected void sort() {
    }

    protected void notifyDataSetChanged() {
        sort();
        mWrapperAdapter.notifyDataSetChanged();
        upHeaderAndFooter();
        upEmpty();
    }

    protected void upHeaderAndFooter() {
        if (mFooter == null) return;
        if (mData.size() == 0) {
            mFooter.setVisibility(View.GONE);
        } else {
            mFooter.setVisibility(View.VISIBLE);
        }
    }

    protected void upEmpty() {
        if (mEmpty == null) return;
        if (mData.size() == 0) {
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.GONE);
        }
    }
}
