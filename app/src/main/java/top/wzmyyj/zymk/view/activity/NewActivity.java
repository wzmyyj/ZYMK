package top.wzmyyj.zymk.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.NewContract;
import top.wzmyyj.zymk.presenter.NewPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.panel.NewRecyclerPanel;


/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */

public class NewActivity extends BaseActivity<NewContract.IPresenter> implements NewContract.IView {
    @Override
    protected void initPresenter() {
        mPresenter = new NewPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                new NewRecyclerPanel(context, mPresenter).setTitle("最新上架"),
                new NewRecyclerPanel(context, mPresenter).setTitle("最新更新")
        );
    }

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.img_back)
    ImageView img_back;


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

    @Override
    protected void initListener() {
        super.initListener();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.finish();
            }
        });
    }

    @Override
    public void showData(List<BookBean>... bookList) {
        ((NewRecyclerPanel) getPanel(0)).setNewData(bookList[0]);
        ((NewRecyclerPanel) getPanel(1)).setNewData(bookList[1]);
    }
}
