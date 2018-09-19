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
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.panel.TypeRecyclerPanel;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 * 第二页。
 */

public class TypeFragment extends BaseFragment<TypeContract.IPresenter> implements TypeContract.IView {

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
                new TypeRecyclerPanel(context, mPresenter).setTitle("题材"),
                new TypeRecyclerPanel(context, mPresenter).setTitle("进度"),
                new TypeRecyclerPanel(context, mPresenter).setTitle("受众"),
                new TypeRecyclerPanel(context, mPresenter).setTitle("媒体")
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
        for (Panel p : mPanelManager.getPanelList()) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.loadData();
    }

    @SafeVarargs
    @Override
    public final void showData(List<TypeBean>... typeList) {
        ((TypeRecyclerPanel) getPanel(0)).setTypeData(typeList[0]);
        ((TypeRecyclerPanel) getPanel(1)).setTypeData(typeList[1]);
        ((TypeRecyclerPanel) getPanel(2)).setTypeData(typeList[2]);
        ((TypeRecyclerPanel) getPanel(3)).setTypeData(typeList[3]);
    }
}
