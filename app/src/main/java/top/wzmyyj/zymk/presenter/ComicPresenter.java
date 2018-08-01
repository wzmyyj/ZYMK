package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IComicView;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicPresenter extends BasePresenter<IComicView> {
    public ComicPresenter(Activity activity, IComicView iv) {
        super(activity, iv);
    }
}
