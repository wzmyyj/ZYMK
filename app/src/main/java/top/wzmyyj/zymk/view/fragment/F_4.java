package top.wzmyyj.zymk.view.fragment;

import android.view.View;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.presenter.MinePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_4View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class F_4 extends BaseFragment<MinePresenter> implements IF_4View {

    @Override
    protected void initPresenter() {
        mPresenter = new MinePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_4;
    }

    @BindView(R.id.v_top)
    View v;

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v);
    }
}
