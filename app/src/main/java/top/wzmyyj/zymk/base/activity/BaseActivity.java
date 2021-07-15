package top.wzmyyj.zymk.base.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.activity.PanelActivity;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.wzm_sdk.utils.AdaptScreenUtil;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.app.data.Config;
import top.wzmyyj.zymk.base.contract.IBasePresenter;
import top.wzmyyj.zymk.base.contract.IBaseView;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */
public abstract class BaseActivity<P extends IBasePresenter> extends PanelActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void initSome(Bundle savedInstanceState) {
        initPresenter();
        checkPresenterIsNull();
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity, false, true, true);
        AdaptScreenUtil.adaptWidth(getResources(), Config.BASE_WIDTH);
    }

    protected abstract void initPresenter();

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    protected abstract int getLayoutId();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.onReStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void showToast(String t) {
        T.s(t);
    }
}
