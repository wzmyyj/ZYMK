package top.wzmyyj.zymk.view.fragment;

import android.annotation.SuppressLint;
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
@SuppressLint("NonConstantResourceId")
public class MineFragment extends BaseFragment<MineContract.IPresenter> implements MineContract.IView {

    @BindView(R.id.fl_panel)
    FrameLayout flPanel;
    @BindView(R.id.v_top)
    View vTop;

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
        addPanels(new MineNestedScrollPanel(context, mPresenter));
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(vTop);
        flPanel.addView(getPanelView(0));
    }
}
