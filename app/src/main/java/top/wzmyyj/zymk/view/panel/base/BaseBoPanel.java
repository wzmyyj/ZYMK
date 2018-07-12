package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.BoPanel;
import top.wzmyyj.zymk.presenter.base.IBasePresent;


/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public abstract class BaseBoPanel<P extends IBasePresent> extends BoPanel {
    protected P mPresenter;

    public BaseBoPanel(Context context, P p) {
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
