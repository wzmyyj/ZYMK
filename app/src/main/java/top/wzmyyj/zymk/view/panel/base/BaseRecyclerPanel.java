package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.RecyclerPanel;
import top.wzmyyj.zymk.contract.base.IBasePresenter;


/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public abstract class BaseRecyclerPanel<T,P extends IBasePresenter> extends RecyclerPanel<T> {

    protected P mPresenter;

    public BaseRecyclerPanel(Context context, P p) {
        super(context);
        this.mPresenter = p;
        checkPresenterIsNull();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
