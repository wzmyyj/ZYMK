package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.view.panel.ComicRecyclerPanel;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class ComicActivity extends BaseActivity<ComicContract.IPresenter> implements ComicContract.IView {

    private ComicRecyclerPanel comicRecyclerPanel;

    @BindView(R.id.fl_panel)
    FrameLayout flPanel;

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
        addPanels(comicRecyclerPanel = new ComicRecyclerPanel(context, mPresenter));
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.fullScreen(this);
    }

    @Override
    protected void initView() {
        super.initView();
        flPanel.addView(comicRecyclerPanel.getView());
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    public void showData(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList) {
        comicRecyclerPanel.setComicData(book, chapterList, bookList);
    }

    @Override
    public void showLoadFail(String msg) {
        comicRecyclerPanel.loadFail();
    }
}
