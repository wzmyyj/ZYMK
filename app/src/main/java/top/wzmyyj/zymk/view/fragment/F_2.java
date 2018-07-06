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
import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.app.data.ComicType;
import top.wzmyyj.zymk.presenter.TypePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_2View;
import top.wzmyyj.zymk.view.panel.TypeRecyclePanel;

/**
 * Created by wzm on 2018/06/29. email: 2209011667@qq.com
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
    public void showToast(String t) {

    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TypeRecyclePanel(activity) {
            @Override
            protected void setData() {
                for (int i = 0; i < ComicType.type_0.length; i++)
                    mData.add(new TypeBean(ComicType.type[0],
                            ComicType.type_0[i], ComicType.pic_0[i]));
            }
        });
        addPanels(new TypeRecyclePanel(activity) {
            @Override
            protected void setData() {
                for (int i = 0; i < ComicType.type_1.length; i++)
                    mData.add(new TypeBean(ComicType.type[1],
                            ComicType.type_1[i], ComicType.pic_1[i]));
            }
        });
        addPanels(new TypeRecyclePanel(activity) {
            @Override
            protected void setData() {
                for (int i = 0; i < ComicType.type_2.length; i++)
                    mData.add(new TypeBean(ComicType.type[2],
                            ComicType.type_2[i], ComicType.pic_2[i]));
            }
        });
        addPanels(new TypeRecyclePanel(activity) {
            @Override
            protected void setData() {
                for (int i = 0; i < ComicType.type_3.length; i++)
                    mData.add(new TypeBean(ComicType.type[3],
                            ComicType.type_3[i], ComicType.pic_3[i]));
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
        }

        titles.add(ComicType.type[0]);
        titles.add(ComicType.type[1]);
        titles.add(ComicType.type[2]);
        titles.add(ComicType.type[3]);

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
