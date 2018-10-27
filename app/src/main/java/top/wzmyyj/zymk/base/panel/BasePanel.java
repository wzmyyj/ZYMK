package top.wzmyyj.zymk.base.panel;

import android.content.Context;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.panel.InitPanel;
import top.wzmyyj.zymk.base.contract.IBasePresenter;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BasePanel<P extends IBasePresenter> extends InitPanel {
    protected P mPresenter;

    public BasePanel(Context context, P p) {
        super(context);
        this.mPresenter = p;
        checkPresenterIsNull();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    protected abstract int getLayoutId();

    @Override
    protected void initView() {
        view = mInflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
