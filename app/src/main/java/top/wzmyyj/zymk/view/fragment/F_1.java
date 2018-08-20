package top.wzmyyj.zymk.view.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_1View;
import top.wzmyyj.zymk.view.panel.HomeFavorPanel;
import top.wzmyyj.zymk.view.panel.HomeNestedScrollPanel;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public class F_1 extends BaseFragment<HomePresenter> implements IF_1View {


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }


    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new HomeNestedScrollPanel(context, mPresenter));
        addPanels(new HomeFavorPanel(context, mPresenter));
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

        fl_panel.addView(getPanelView(0));
        fl_panel.addView(getPanelView(1));

        getPanel(0).bingViews(ll_top);

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
        mPresenter.updateLoadFavor();
    }

    @Override
    public void update(int w, Object... objs) {
        getPanel(0).f(w, objs);
    }

    @Override
    public void loadFavor(List<FavorBean> list) {
        getPanel(1).f(0, list);
    }
}



