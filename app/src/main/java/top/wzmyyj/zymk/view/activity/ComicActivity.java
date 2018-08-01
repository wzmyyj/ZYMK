package top.wzmyyj.zymk.view.activity;

import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.IComicView;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicActivity extends BaseActivity<ComicPresenter> implements IComicView {
    @Override
    protected void initPresenter() {
        mPresenter = new ComicPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
