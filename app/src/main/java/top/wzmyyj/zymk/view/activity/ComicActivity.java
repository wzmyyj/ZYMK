package top.wzmyyj.zymk.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.panel.ComicRecyclerPanel;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicActivity extends BaseActivity<ComicContract.IPresenter> implements ComicContract.IView {
    @Override
    protected void initPresenter() {
        mPresenter = new ComicPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comic;
    }

    private ComicRecyclerPanel comicRecyclerPanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(comicRecyclerPanel = new ComicRecyclerPanel(context, mPresenter));
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.fullScreen(this);
    }

    @BindView(R.id.fl_panel)
    FrameLayout fl_panel;

    @Override
    protected void initView() {
        super.initView();
        fl_panel.addView(comicRecyclerPanel.getView());
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }


    @Override
    public void showData(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList, List<ComicBean> comicList) {
        comicRecyclerPanel.setComicData(book, chapterList, bookList, comicList);
    }

    @Override
    public void showLoadFail(String msg) {
        comicRecyclerPanel.loadFail();
    }
}
