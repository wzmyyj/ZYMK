package top.wzmyyj.zymk.view.fragment;

import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.fragment.PanelFragment;
import top.wzmyyj.zymk.presenter.BasePresenter;

/**
 * Created by wzm on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BaseFragment<P extends BasePresenter> extends PanelFragment {
    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
        checkPresenterIsNull();
    }

    @Override
    protected void initView() {
        mVRoot = mInflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, mVRoot);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


}
