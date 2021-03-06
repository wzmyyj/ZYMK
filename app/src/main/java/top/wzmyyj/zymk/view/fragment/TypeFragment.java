package top.wzmyyj.zymk.view.fragment;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.base.fragment.BaseFragment;
import top.wzmyyj.zymk.view.panel.TypeRecyclerPanel;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 * 第二页。
 */
@SuppressLint("NonConstantResourceId")
public class TypeFragment extends BaseFragment<TypeContract.IPresenter> implements TypeContract.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.v_top)
    View vTop;

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

    @OnClick(R.id.img_search)
    public void search() {
        mPresenter.goSearch();
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(vTop);
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
    public void showData(List<TypeBean> typeList1, List<TypeBean> typeList2,
                         List<TypeBean> typeList3, List<TypeBean> typeList4) {
        ((TypeRecyclerPanel) getPanel(0)).setTypeData(typeList1);
        ((TypeRecyclerPanel) getPanel(1)).setTypeData(typeList2);
        ((TypeRecyclerPanel) getPanel(2)).setTypeData(typeList3);
        ((TypeRecyclerPanel) getPanel(3)).setTypeData(typeList4);
    }
}
