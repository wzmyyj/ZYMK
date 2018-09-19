package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import top.wzmyyj.zymk.contract.base.IBasePresenter;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public abstract class BasePanel<P extends IBasePresenter> extends BaseInitPanel {
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
}
