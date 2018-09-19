package top.wzmyyj.zymk.view.fragment.base;

import android.content.Context;
import android.os.Bundle;

import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BaseFragment<P extends IBasePresenter> extends BasePanelFragment implements IBaseView {
    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
        checkPresenterIsNull();
    }


    protected abstract void initPresenter();

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
        mPresenter = null;
    }

    @Override
    public void showToast(String t) {
        T.s(t);
    }
}
