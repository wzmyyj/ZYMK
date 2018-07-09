package top.wzmyyj.zymk.presenter.p;

import android.app.Activity;

import top.wzmyyj.zymk.presenter.ip.IRecyclePresent;
import top.wzmyyj.zymk.presenter.p.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_1View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class HomePresenter extends BasePresenter<IF_1View> implements IRecyclePresent {
    public HomePresenter(Activity activity, IF_1View iv) {
        super(activity, iv);
    }

    @Override
    public void loadData() {

    }
}
