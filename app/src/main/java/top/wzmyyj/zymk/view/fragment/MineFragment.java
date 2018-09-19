package top.wzmyyj.zymk.view.fragment;

import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.MineContract;
import top.wzmyyj.zymk.presenter.MinePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;

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

    @OnClick(R.id.img_me)
    public void about() {
        mPresenter.goAboutMe();
    }

    @OnClick(R.id.ll_info)
    public void goGitHub() {
        mPresenter.goGitHubWeb();
    }

    @OnClick(R.id.ll_1)
    public void goWeb1() {
        mPresenter.goHomeWeb();
    }

    @OnClick(R.id.ll_2)
    public void goWeb2() {
        mPresenter.goHotWeb();
    }

    @OnClick(R.id.ll_3)
    public void goWeb3() {
        mPresenter.goTmallWeb();
    }

    @OnClick(R.id.ll_4)
    public void goSetting() {
        mPresenter.goSetting();
    }


    @BindView(R.id.v_top)
    View v;

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.fitsStatusBarView(v);
    }
}
