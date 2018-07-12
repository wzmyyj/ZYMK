package top.wzmyyj.zymk.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_2View;
import top.wzmyyj.zymk.view.panel.TypeRecyclePanel;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class F_2 extends BaseFragment<TypePresenter> implements IF_2View {

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
        addPanels(new TypeRecyclePanel(activity,mPresenter) {
            @Override
            protected void setData() {
                this.title = F_2.this.mPresenter.getTitle(0);
                mData.addAll(F_2.this.mPresenter.getData(0));
            }
        });
        addPanels(new TypeRecyclePanel(activity,mPresenter) {
            @Override
            protected void setData() {
                this.title = F_2.this.mPresenter.getTitle(1);
                mData.addAll(F_2.this.mPresenter.getData(1));
            }
        });
        addPanels(new TypeRecyclePanel(activity,mPresenter) {
            @Override
            protected void setData() {
                this.title = F_2.this.mPresenter.getTitle(2);
                mData.addAll(F_2.this.mPresenter.getData(2));
            }
        });
        addPanels(new TypeRecyclePanel(activity,mPresenter) {
            @Override
            protected void setData() {
                this.title = F_2.this.mPresenter.getTitle(3);
                mData.addAll(F_2.this.mPresenter.getData(3));
            }
        });
    }

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.img_search)
    ImageView img_search;

    @Override
    protected void initData() {
        super.initData();
        List<View> viewList = new ArrayList<View>();
        List<String> titles = new ArrayList<>();
        for (Panel p : mPanels.getPanelList()) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {
        super.initListener();
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("搜索");
            }
        });
    }
}
