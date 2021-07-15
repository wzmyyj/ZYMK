package top.wzmyyj.zymk.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dl7.tag.TagLayout;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.adapter.ViewTitlePagerAdapter;
import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.app.event.HistoryListChangeEvent;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.presenter.DetailsPresenter;
import top.wzmyyj.zymk.base.activity.BaseActivity;
import top.wzmyyj.zymk.view.adapter.BookAdapter;
import top.wzmyyj.zymk.view.panel.DetailsMuPanel;
import top.wzmyyj.zymk.view.panel.DetailsXiPanel;
import top.wzmyyj.zymk.view.panel.DetailsZiPanel;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class DetailsActivity extends BaseActivity<DetailsContract.IPresenter> implements DetailsContract.IView {

    private DetailsXiPanel xiPanel;
    private DetailsMuPanel muPanel;
    private DetailsZiPanel ziPanel;

    @BindView(R.id.img_book_bg)
    ImageView imgBookBg;
    @BindView(R.id.tv_book_title)
    TextView tvBookTitle;
    @BindView(R.id.tv_book_favor)
    TextView tvBookFavor;
    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;
    @BindView(R.id.tl_book_tag)
    TagLayout tlBookTag;
    @BindView(R.id.tv_book_ift)
    TextView tvBookIft;
    @BindView(R.id.img_book)
    ImageView imgBook;
    @BindView(R.id.tv_book_star)
    TextView tvBookStar;
    @BindView(R.id.bt_read)
    Button btRead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.srl_details)
    SmartRefreshLayout mRefreshLayout;
    // tab
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    // content
    @BindView(R.id.vp_content)
    ViewPager mViewPager;
    @BindView(R.id.rv_books)
    RecyclerView rvBooks;

    private BookBean mBook;
    private final List<BookBean> xgBooks = new ArrayList<>();
    private BookAdapter bookAdapter;
    private long historyChapterId = 0;

    @Override
    protected void initPresenter() {
        mPresenter = new DetailsPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                (xiPanel = new DetailsXiPanel(context, mPresenter)).setTitle("详情"),
                (muPanel = new DetailsMuPanel(context, mPresenter)).setTitle("目录"),
                (ziPanel = new DetailsZiPanel(context, mPresenter)).setTitle("支持")
        );
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity, true, true, true);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @OnClick(R.id.img_back)
    void back() {
        mPresenter.finish();
    }

    @OnClick(R.id.img_love)
    public void favor() {
        if (mBook == null) return;
        mPresenter.addFavor(mBook);
    }

    @OnClick(R.id.bt_read)
    public void read() {
        mPresenter.goComic(mBook.getId(), historyChapterId);
    }

    @OnClick(R.id.bt_save)
    public void save() {
        showToast("暂不支持离线缓存！");
    }

    @Override
    protected void initView() {
        super.initView();
        mRefreshLayout.setHeaderHeight(100);
        mRefreshLayout.setFooterHeight(100);
        mRefreshLayout.setPrimaryColorsId(R.color.colorRefresh, R.color.colorWhite);
        mRefreshLayout.setEnableLoadMore(false);
        rvBooks.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        rvBooks.setNestedScrollingEnabled(false);
        bookAdapter = new BookAdapter(context, xgBooks);
        rvBooks.setAdapter(bookAdapter);
        List<View> viewList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Panel p : mPanelManager.getPanelList()) {
            viewList.add(p.getView());
            titles.add(p.getTitle());
        }
        ViewTitlePagerAdapter pagerAdapter = new ViewTitlePagerAdapter(viewList, titles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                update();
                refreshLayout.finishRefresh(1500);
            }
        });

        bookAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.goDetails(xgBooks.get(position).getHref());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void update() {
        mPresenter.loadData();
    }

    @Override
    public void setBook(BookBean book) {
        if (book == null) return;
        mBook = book;
        tvTitle.setText(book.getTitle());
        tvBookTitle.setText(book.getTitle());
        tvBookAuthor.setText(book.getAuthor());
        tvBookIft.setText(book.getIft());
        tvBookStar.setText((book.getStar() + "分"));
        tlBookTag.cleanTags();
        if (book.getTags() != null) {
            for (String tag : book.getTags()) {
                tlBookTag.addTag(tag);
            }
        }
        GlideLoaderHelper.img(imgBook, book.getDataSrc());
        GlideLoaderHelper.imgBlur(imgBookBg, book.getDataSrc(), 15);
        long id = book.getId();
        mPresenter.isFavor(id);
        mPresenter.getHistoryRead(id);
    }

    @Override
    public void setXi(XiBean xi) {
        xiPanel.setXiData(xi);
    }

    @Override
    public void setMu(MuBean mu) {
        muPanel.setMuData(mu);
    }

    @Override
    public void setZi(ZiBean zi) {
        ziPanel.setZiData(zi);
    }

    @Override
    public void setBookList(List<BookBean> list) {
        if (list == null) return;
        xgBooks.clear();
        xgBooks.addAll(list);
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void setIsFavor(boolean isFavor) {
        if (isFavor) {
            tvBookFavor.setVisibility(View.VISIBLE);
        } else {
            tvBookFavor.setVisibility(View.GONE);
        }
    }

    @Override
    public void setHistory(ChapterBean chapter) {
        if (chapter == null) {
            btRead.setText("开始阅读");
        } else {
            btRead.setText(("续看" + chapter.getChapterName()));
            historyChapterId = chapter.getChapterId();
            muPanel.setHistoryChapter(chapter);
        }
    }

    @Subscribe
    public void onEvent(HistoryListChangeEvent event) {
        if (event.isChange()) {
            mPresenter.getHistoryRead(mBook.getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
