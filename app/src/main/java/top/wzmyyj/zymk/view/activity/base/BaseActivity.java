package top.wzmyyj.zymk.view.activity.base;

import android.os.Bundle;

import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BaseActivity<P extends IBasePresenter> extends BasePanelActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void initSome(Bundle savedInstanceState) {
        initPresenter();
        checkPresenterIsNull();
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity,false,true,true);
    }

    protected abstract void initPresenter();


    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
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
