package top.wzmyyj.zymk.view.fragment;

import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.base.fragment.BaseFragment;
import top.wzmyyj.zymk.contract.MineContract;
import top.wzmyyj.zymk.presenter.MinePresenter;
import top.wzmyyj.zymk.view.panel.MineNestedScrollPanel;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 * 第四页。
 */

public class MineFragment extends BaseFragment<MineContract.IPresenter> implements MineContract.IView {

    @Override
    protected void initPresenter() {
        mPresenter = new MinePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_4;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new MineNestedScrollPanel(context,mPresenter));
    }

    @BindView(R.id.fl_panel)
    FrameLayout fl_panel;

    @BindView(R.id.v_top)
    View v;

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v);
        fl_panel.addView(getPanelView(0));
    }
}
