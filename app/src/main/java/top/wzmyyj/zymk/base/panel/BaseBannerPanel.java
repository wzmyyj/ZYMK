package top.wzmyyj.zymk.base.panel;

import android.content.Context;

import top.wzmyyj.wzm_sdk.panel.BannerPanel;
import top.wzmyyj.zymk.base.contract.IBasePresenter;


/**
 * Created by yyj on 2018/09/19. email: 2209011667@qq.com
 */

public abstract class BaseBannerPanel<P extends IBasePresenter> extends BannerPanel {
    protected P mPresenter;

    public BaseBannerPanel(Context context, P p) {
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
