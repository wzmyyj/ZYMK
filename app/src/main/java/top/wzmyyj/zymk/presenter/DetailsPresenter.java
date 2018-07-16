package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IDetailsView;


/**
 * Created by yyj on 2018/07/14. email: 2209011667@qq.com
 */

public class DetailsPresenter extends BasePresenter<IDetailsView> {
    public DetailsPresenter(Activity activity, IDetailsView iv) {
        super(activity, iv);
    }
}
