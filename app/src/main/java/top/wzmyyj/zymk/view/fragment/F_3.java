package top.wzmyyj.zymk.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.event.DownloadListChangeEvent;
import top.wzmyyj.zymk.app.event.FavorListChangeEvent;
import top.wzmyyj.zymk.app.event.HistoryListChangeEvent;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_3View;
import top.wzmyyj.zymk.view.panel.FavorRecyclerPanel;
import top.wzmyyj.zymk.view.panel.HistoryRecyclerPanel;
import top.wzmyyj.zymk.view.panel.DownloadRecyclerPanel;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class F_3 extends BaseFragment<FindPresenter> implements IF_3View {
    @Override
    protected void initPresenter() {
        mPresenter = new FindPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                new FavorRecyclerPanel(context, mPresenter),
                new HistoryRecyclerPanel(context, mPresenter),
                new DownloadRecyclerPanel(context, mPresenter)
        );
    }

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.v_top)
    View v;


    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v);
    }

    @Override
    protected void initData() {
        super.initData();
        List<View> viewList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Panel p : mPanels.getPanelList()) {
            viewList.add(p.getView());
        }
        titles.add("订阅");
        titles.add("历史");
        titles.add("缓存");
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        // 加载数据库数据。
        mPresenter.loadFavor();// 只访问数据库。
        mPresenter.loadHistory();
        mPresenter.loadDownload();
    }


    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //////////////////////////////////////////////////////////////////////////// favor
    @Override
    public void loadFavor(List<FavorBean> list) {
        getPanel(0).f(1, list);
    }

    @Override
    public void deleteFavor(boolean is) {
        if (!is) return;
        getPanel(0).f(2);
    }


    @Subscribe
    public void onEvent(FavorListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadFavor();// 只访问数据库。
        }
    }
    //////////////////////////////////////////////////////////////////////////// history

    @Override
    public void loadHistory(List<HistoryBean> list) {
        getPanel(1).f(1, list);
    }

    @Override
    public void deleteHistory(boolean is) {
        if (!is) return;
        getPanel(1).f(2);
    }

    @Subscribe
    public void onEvent(HistoryListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadHistory();
        }
    }


    @Override
    public void loadDownload(List<DownloadBean> list) {
        getPanel(2).f(1, list);
    }

    @Override
    public void deleteDownload(boolean is) {
        if (!is) return;
        getPanel(2).f(2);
    }



    @Subscribe
    public void onEvent(DownloadListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadDownload();
        }
    }
}
