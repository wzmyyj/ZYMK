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
import top.wzmyyj.zymk.contract.FindContract;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.panel.DownloadRecyclerPanel;
import top.wzmyyj.zymk.view.panel.FavorRecyclerPanel;
import top.wzmyyj.zymk.view.panel.HistoryRecyclerPanel;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 * 第三页。
 */

public class FindFragment extends BaseFragment<FindContract.IPresenter> implements FindContract.IView {
    @Override
    protected void initPresenter() {
        mPresenter = new FindPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    private FavorRecyclerPanel favorRecyclerPanel;
    private HistoryRecyclerPanel historyRecyclerPanel;
    private DownloadRecyclerPanel downloadRecyclerPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                (favorRecyclerPanel = new FavorRecyclerPanel(context, mPresenter)).setTitle("订阅"),
                (historyRecyclerPanel = new HistoryRecyclerPanel(context, mPresenter)).setTitle("历史"),
                (downloadRecyclerPanel = new DownloadRecyclerPanel(context, mPresenter)).setTitle("缓存")
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
        for (Panel p : mPanelManager.getPanelList()) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
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
    public void showFavor(List<FavorBean> list) {
        if (list == null) return;
        favorRecyclerPanel.setFindData(list);
    }

    @Override
    public void removeFavor(boolean is) {
        if (!is) return;
        favorRecyclerPanel.deleteSuccess();
    }


    @Subscribe
    public void onEvent(FavorListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadFavor();// 只访问数据库。
        }
    }
    //////////////////////////////////////////////////////////////////////////// history

    @Override
    public void showHistory(List<HistoryBean> list) {
        if (list == null) return;
        historyRecyclerPanel.setFindData(list);
    }

    @Override
    public void removeHistory(boolean is) {
        if (!is) return;
        historyRecyclerPanel.deleteSuccess();
    }

    @Subscribe
    public void onEvent(HistoryListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadHistory();
        }
    }


    @Override
    public void showDownload(List<DownloadBean> list) {
        if (list == null) return;
        downloadRecyclerPanel.setFindData(list);
    }

    @Override
    public void removeDownload(boolean is) {
        if (!is) return;
        downloadRecyclerPanel.deleteSuccess();
    }


    @Subscribe
    public void onEvent(DownloadListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadDownload();
        }
    }
}
