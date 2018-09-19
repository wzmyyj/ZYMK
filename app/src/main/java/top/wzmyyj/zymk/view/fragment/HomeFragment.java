package top.wzmyyj.zymk.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.event.FavorUnReadChangeEvent;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.panel.HomeFavorPanel;
import top.wzmyyj.zymk.view.panel.HomeNestedScrollPanel;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 * 第一页。
 */

public class HomeFragment extends BaseFragment<HomeContract.IPresenter> implements HomeContract.IView {


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }


    private HomeNestedScrollPanel homeNestedScrollPanel;
    private HomeFavorPanel homeFavorPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(homeNestedScrollPanel = new HomeNestedScrollPanel(context, mPresenter));
        addPanels(homeFavorPanel = new HomeFavorPanel(context, mPresenter));
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void onEvent(FavorUnReadChangeEvent event) {
        if (event.isChange()) {
            mPresenter.loadFavor();// 只访问数据库。
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @BindView(R.id.fl_panel)
    FrameLayout fl_panel;

    @OnClick(R.id.img_a)
    public void fff() {
        T.s("这是一个预留按钮>_<");
    }

    @OnClick(R.id.img_search)
    public void search() {
        mPresenter.goSearch();
    }

    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.v_top_0)
    View v0;
    @BindView(R.id.v_top_1)
    View v1;
    @BindView(R.id.v_top_2)
    View v2;


    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v0, v1, v2);

        fl_panel.addView(homeNestedScrollPanel.getView());
        fl_panel.addView(homeFavorPanel.getView());

        homeNestedScrollPanel.bingViews(ll_top);

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
        mPresenter.loadNetFavor();
    }

    @Override
    public void showData(List<BoBean> boBeans, List<ItemBean> itemBeans) {
        homeNestedScrollPanel.setHomeData(boBeans, itemBeans);
    }

    @Override
    public void showFavor(List<FavorBean> list) {
        homeFavorPanel.setFavorData(list);
    }


}



