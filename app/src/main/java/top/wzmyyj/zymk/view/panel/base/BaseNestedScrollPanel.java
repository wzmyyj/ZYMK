package top.wzmyyj.zymk.view.panel.base;

import android.content.Context;

import butterknife.ButterKnife;
import top.wzmyyj.wzm_sdk.panel.NestedScrollPanel;
import top.wzmyyj.zymk.contract.base.IBasePresenter;


/**
 * Created by yyj on 2018/08/16. email: 2209011667@qq.com
 */

public abstract class BaseNestedScrollPanel<P extends IBasePresenter> extends NestedScrollPanel {
    protected P mPresenter;

    public BaseNestedScrollPanel(Context context, P p) {
        super(context);
        this.mPresenter = p;
        checkPresenterIsNull();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this, contentView);
    }
}
