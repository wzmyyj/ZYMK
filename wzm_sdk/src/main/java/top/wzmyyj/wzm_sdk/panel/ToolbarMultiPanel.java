package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class ToolbarMultiPanel extends InitPanel {

    public ToolbarMultiPanel(Context context) {
        super(context);
    }

    @NonNull
    protected abstract List<Panel> getPanels();

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPanelList = getPanels();
    }


    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageView;

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.af_multi_panel, null);
        mToolbar = view.findViewById(R.id.toolbar);
        mViewPager = view.findViewById(R.id.viewPager);
        mTabLayout = view.findViewById(R.id.tabLayout);
        mImageView = view.findViewById(R.id.img_1);

        setView(mToolbar, mTabLayout, mViewPager, mImageView);
    }

    protected abstract void setView(Toolbar toolbar, TabLayout tab, ViewPager vp, ImageView img);

    @Override
    protected void initData() {
        List<View> viewList = new ArrayList<View>();
        List<String> titles = new ArrayList<>();
        for (Panel p : mPanelList) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {

    }
}
