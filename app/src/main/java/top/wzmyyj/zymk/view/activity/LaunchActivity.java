package top.wzmyyj.zymk.view.activity;

import android.os.Bundle;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.contract.LaunchContract;
import top.wzmyyj.zymk.presenter.LaunchPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;


/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public class LaunchActivity extends BaseActivity<LaunchContract.IPresenter> implements LaunchContract.IView {

    @Override
    protected void initPresenter() {
        mPresenter = new LaunchPresenter(activity, this);
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPresenter.CheckPermission();
        mPresenter.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        super.initView();
        setSwipeBackEnable(false);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mPresenter.goMain();
    }
}
