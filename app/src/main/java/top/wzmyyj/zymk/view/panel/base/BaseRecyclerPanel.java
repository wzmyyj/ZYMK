package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.RecyclerPanel;
import top.wzmyyj.zymk.presenter.ip.IRecyclePresent;


/**
 * Created by wzm on 2018/07/06. email: 2209011667@qq.com
 */

public abstract class BaseRecyclerPanel<T> extends RecyclerPanel<T> {
    public BaseRecyclerPanel(Context context) {
        super(context);
    }

    protected IRecyclePresent mPresenter;

    public BaseRecyclerPanel(Context context, IRecyclePresent ip) {
        super(context);
        this.mPresenter = ip;
        checkPresenterIsNull();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
