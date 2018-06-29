package top.wzmyyj.zymk.view.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.activity.InitActivity;
import top.wzmyyj.wzm_sdk.utils.ThemeUtil;
import top.wzmyyj.zymk.presenter.BasePresenter;

/**
 * Created by wzm on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BaseActivity<P extends BasePresenter> extends InitActivity {

    protected P mPresenter;

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        ThemeUtil.initStatusBar(activity, true);
        initPresenter();
        checkPresenterIsNull();
    }

    @Override
    protected void initView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    protected abstract void initPresenter();

    protected abstract int getLayoutId();

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

}
