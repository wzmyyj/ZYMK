package top.wzmyyj.zymk.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_2View;
import top.wzmyyj.zymk.view.panel.TypeRecyclerPanel;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 * 第二页。
 */

public class TypeFragment extends BaseFragment<TypePresenter> implements IF_2View {

    @Override
    protected void initPresenter() {
        mPresenter = new TypePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_2;
    }


    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                new TypeRecyclerPanel(context, mPresenter),
                new TypeRecyclerPanel(context, mPresenter),
                new TypeRecyclerPanel(context, mPresenter),
                new TypeRecyclerPanel(context, mPresenter)
        );
    }

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @OnClick(R.id.img_search)
    public void search() {
        mPresenter.goSearch();
    }

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
        titles.add("题材");
        titles.add("进度");
        titles.add("受众");
        titles.add("媒体");
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.loadData();
    }

    @Override
    public void update(int w, Object... objs) {
        getPanel(0).f(w, objs[0]);
        getPanel(1).f(w, objs[1]);
        getPanel(2).f(w, objs[2]);
        getPanel(3).f(w, objs[3]);
    }
}
