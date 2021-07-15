package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.RankContract;
import top.wzmyyj.zymk.presenter.RankPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.view.panel.RankRecyclerPanel;

/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class RankActivity extends BaseActivity<RankContract.IPresenter> implements RankContract.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.img_back)
    ImageView imgBack;

    @Override
    protected void initPresenter() {
        mPresenter = new RankPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                new RankRecyclerPanel(activity, mPresenter).setTitle("人气榜"),
                new RankRecyclerPanel(activity, mPresenter).setTitle("打赏榜"),
                new RankRecyclerPanel(activity, mPresenter).setTitle("月票榜")
        );
    }

    @Override
    protected void initView() {
        super.initView();
        List<View> viewList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Panel p : mPanelManager.getPanelList()) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        imgBack.setOnClickListener(v -> mPresenter.finish());
    }

    @Override
    public void showData(List<BookBean> bookList1, List<BookBean> bookList2, List<BookBean> bookList3) {
        ((RankRecyclerPanel) getPanel(0)).setRankData(bookList1);
        ((RankRecyclerPanel) getPanel(1)).setRankData(bookList2);
        ((RankRecyclerPanel) getPanel(2)).setRankData(bookList3);
    }
}
