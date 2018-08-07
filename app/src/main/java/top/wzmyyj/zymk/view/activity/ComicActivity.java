package top.wzmyyj.zymk.view.activity;

import android.widget.FrameLayout;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.IComicView;
import top.wzmyyj.zymk.view.panel.ComicRecyclerPanel;


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
        return R.layout.activity_comic;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new ComicRecyclerPanel(context, mPresenter));
    }


    @BindView(R.id.fl_panel)
    FrameLayout fl_panel;

    @Override
    protected void initView() {
        super.initView();
        fl_panel.addView(getPanelView(0));
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void update(int w, Object... objs) {
        getPanel(0).f(w, objs);
    }
}
