package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.wzm_sdk.tools.A;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.common.utils.DensityUtil;
import top.wzmyyj.zymk.common.utils.MockUtil;
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.view.panel.base.BasePanel;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 * 漫画阅读主页面。
 */

public class ComicRecyclerPanel extends BaseRecyclerPanel<ComicBean, ComicContract.IPresenter> {

    public ComicRecyclerPanel(Context context, ComicContract.IPresenter comicPresenter) {
        super(context, comicPresenter);
    }

    private ComicMeunPanel mMeunPanel;
    private ComicLoadPasePanel mLoadPasePanel;

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                mMeunPanel = new ComicMeunPanel(context, mPresenter),
                mLoadPasePanel = new ComicLoadPasePanel(context, mPresenter)
        );
    }


    @Override
    protected void initView() {
        super.initView();
        mFrameLayout.addView(getPanelView(0));
        mFrameLayout.addView(getPanelView(1));

        // 消除mRecyclerView刷新的动画。
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private long mChapterId = 0;

    @Override
    protected void initData() {
        super.initData();
        mChapterId = mPresenter.getChapterId();
    }


    private final static int Definition_Low = 1;
    private final static int Definition_Middle = 2;
    private final static int Definition_High = 3;

    private int Definition = Definition_Middle;

    @Override
    protected void setIVD(List<IVD<ComicBean>> ivd) {
        ivd.add(new SingleIVD<ComicBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_comic_image;
            }

            @Override
            public void convert(ViewHolder holder, ComicBean comicBean, int position) {

                ImageView img_comic = holder.getView(R.id.img_comic);

                if (comicBean.getChapter_id() == -1) {
                    G.imgfix(context, R.mipmap.pic_comic_end, img_comic);
                    return;
                }


                if (comicBean.getPrice() == 0) {
                    switch (Definition) {
                        case Definition_Low:
                            G.imgfix(context, comicBean.getImg_low(), img_comic);
                            break;
                        case Definition_Middle:
                            G.imgfix(context, comicBean.getImg_middle(), img_comic);
                            break;
                        case Definition_High:
                            G.imgfix(context, comicBean.getImg_high(), img_comic);
                            break;
                    }
                } else {
                    G.imgfix(context, R.mipmap.pic_need_money, img_comic);
                }

            }
        });
    }


    @Override
    public void viewRecycled(@NonNull ViewHolder holder) {
        if (holder != null) {
            G.clear(context, (ImageView) holder.getView(R.id.img_comic));
        }
        super.viewRecycled(holder);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            // 当前屏幕显示最上面一行的position。
            private int load_position_now = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!mMeunPanel.isAuto) {
                    if (dy > 10) {
                        mMeunPanel.closeMenu();
                    } else if (dy < -10) {
                        mMeunPanel.showMenu();
                    }
                }

                int p = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));

                if (p == -1 || p == load_position_now) return;
                load_position_now = p;

                mMeunPanel.setMenu(mData.get(p));

                if (mChapterId != mData.get(p).getChapter_id()) {
                    mChapterId = mData.get(p).getChapter_id();
                    mMeunPanel.scrollCatalog();
                }

                if (load_position_now < 3) {
                    mHandler.sendEmptyMessage(1);
                } else if (load_position_now > mData.size() - 5) {
                    mHandler.sendEmptyMessage(2);
                }

            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int w = msg.what;
            if (w == 1) {
                addPrevious();
            } else if (w == 2) {
                addAfter();
            } else {
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
            }
        }
    };


    @Override
    public void update() {
        mPresenter.loadData();
    }

    private BookBean mBook;
    private List<ChapterBean> mChapterList = new ArrayList<>();
    private List<BookBean> mBookList = new ArrayList<>();
    private List<ComicBean> mComicList = new ArrayList<>();

    private long chapter_id_previous;
    private long chapter_id_after;


    public void loadFail() {
        mLoadPasePanel.loadFail();
    }

    public void setComicData(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList, List<ComicBean> comicList) {
        mLoadPasePanel.loadSuccess();
        if (book != null) {
            mBook = book;
        }

        if (chapterList != null && chapterList.size() > 0) {
            mChapterList.clear();
            mChapterList.addAll(chapterList);
        }


        if (bookList != null && bookList.size() > 0) {
            mBookList.clear();
            mBookList.addAll(bookList);
        }

        if (comicList != null && comicList.size() > 0) {
            mComicList.clear();
            mComicList.addAll(comicList);
        }

        addEnd();
        addOnce();
        mHandler.sendEmptyMessageDelayed(1, 500);
        mMeunPanel.setCatalogChapterList(chapterList);
    }


    // 增加最后结束语。
    private void addEnd() {
        ComicBean comic = new ComicBean();//增加一张结束语。
        comic.setChapter_id(-1);
        comic.setVar(1);
        comic.setVar_size(1);
        mComicList.add(comic);
        mChapterList.add(new ChapterBean(-1));//增加一章结束语。
    }

    // 第一次添加数据。
    private void addOnce() {
        if (mChapterId == 0) {
            mChapterId = mChapterList.get(0).getChapter_id();
        }
        long chapter_id = mChapterId;
        mChapterId = 0;
        chapter_id_previous = 0;
        chapter_id_after = 0;

        List<ComicBean> comicList = new ArrayList<>();
        for (ComicBean comic : mComicList) {
            if (comic.getChapter_id() == chapter_id) {
                comicList.add(comic);
            }
        }
        mData.clear();
        mData.addAll(0, comicList);
        notifyDataSetChanged();
        mMeunPanel.setMenu(mData.get(0));

        // 找上一章和下一章的ID
        for (int i = 0; i < mChapterList.size(); i++) {
            if (mChapterList.get(i).getChapter_id() == chapter_id) {
                if (i > 0) {
                    chapter_id_previous = mChapterList.get(i - 1).getChapter_id();
                }
                if (i < mChapterList.size() - 1) {
                    chapter_id_after = mChapterList.get(i + 1).getChapter_id();
                }
                break;
            }
        }

    }

    // 向前加载一章。
    private void addPrevious() {
        if (chapter_id_previous == 0) return;
        long previous = chapter_id_previous;
        chapter_id_previous = 0;


        List<ComicBean> comicList = new ArrayList<>();
        for (ComicBean comic : mComicList) {
            if (comic.getChapter_id() == previous) {
                comicList.add(comic);
            }
        }
        mData.addAll(0, comicList);
        mHeaderAndFooterWrapper.notifyItemRangeInserted(0, comicList.size());

        // 找上一章ID
        for (int i = 0; i < mChapterList.size(); i++) {
            if (mChapterList.get(i).getChapter_id() == previous) {
                if (i > 0) {
                    chapter_id_previous = mChapterList.get(i - 1).getChapter_id();
                }
                break;
            }
        }
    }

    // 向后加载一章。
    private void addAfter() {
        if (chapter_id_after == 0) return;

        long after = chapter_id_after;
        chapter_id_after = 0;

        List<ComicBean> comicList = new ArrayList<>();
        for (ComicBean comic : mComicList) {
            if (comic.getChapter_id() == after) {
                comicList.add(comic);
            }
        }
        mData.addAll(comicList);
        notifyDataSetChanged();

        // 找下一章ID
        for (int i = 0; i < mChapterList.size(); i++) {
            if (mChapterList.get(i).getChapter_id() == after) {
                if (i < mChapterList.size() - 1) {
                    chapter_id_after = mChapterList.get(i + 1).getChapter_id();
                }
                break;
            }
        }

    }

    // 前往指定的章节。
    private void goChChapterById(long id) {
        int p = -1;
        for (int i = 0; i < mData.size(); i++) {// mData中查找指定章节。
            if (mData.get(i).getChapter_id() == id) {
                p = i;
                break;
            }
        }
        if (p == -1) {// mData中没有所需章节。
            addOnce();
            return;
        }
        // 滑到指定章节。
        scrollToPosition(p);
    }


    public void notifyItemShoewRangeChanged() {
        // 只刷新当前显示的item，防止图片跳闪。
        int a = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
        int b = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (a == -1 || b == -1) return;
        mHeaderAndFooterWrapper.notifyItemRangeChanged(a, b);
    }

    // mRecyclerView滑到指定position的位置。
    public void scrollToPosition(int p) {
        if (p < 0 || p > mData.size() - 1) return;// 防止越界。
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mLayoutManager.scrollToPositionWithOffset(p, 0);
    }

    private Handler handler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();

    private class MyRunnable implements Runnable {

        int a, b, c;

        @Override
        public void run() {
            if (a >= b || !mRecyclerView.canScrollVertically(1)) {
                a = b = c = 0;
                handler.removeCallbacks(myRunnable);
                return;
            } else {
                a += c;
            }

            int scroll = mRecyclerView.getScrollY();
            mRecyclerView.scrollBy(0, scroll + c);
            handler.postDelayed(myRunnable, speed);
        }

    }

    private final long speed = 5;

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mMeunPanel.clickSome();
//        T.s(mData.get(position).getChapter_name() + ":" + mData.get(position).getVar());
    }

    @Override
    public void onPause() {
        super.onPause();
        // 保存阅读记录。
        for (ChapterBean chapter : mChapterList) {
            if (chapter.getChapter_id() == mChapterId) {
                mPresenter.saveHistory(mBook, chapter);
                return;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 移除消息。
        mHandler.sendEmptyMessage(0);
        handler.removeCallbacks(myRunnable);
    }


    // 内部类panel.菜单面板。
    public class ComicMeunPanel extends BasePanel<ComicContract.IPresenter> {

        public ComicMeunPanel(Context context, ComicContract.IPresenter p) {
            super(context, p);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.layout_comic_menu;
        }

        @BindView(R.id.fl_top)
        FrameLayout fl_top;

        @OnClick(R.id.img_back)
        public void back() {
            mPresenter.finish();
        }

        @BindView(R.id.tv_chapter_name)
        TextView tv_chapter_name;

        @BindView(R.id.tv_chapter_var)
        TextView tv_chapter_var;

        @BindView(R.id.ll_bottom)
        LinearLayout ll_bottom;

        ///////////////////////////////////////////////////// menu 1，跳转到设置页面。
        @OnClick(R.id.ll_menu_1)
        public void menu_1() {
            mPresenter.goSetting();
        }

        ///////////////////////////////////////////////////// menu 2，设置自动滑动和停止。
        @OnClick(R.id.ll_menu_2)
        public void menu_2() {
            if (isAuto) {
                stopAuto();
            } else {
                startAuto();
            }
        }

        @BindView(R.id.ll_auto)
        LinearLayout ll_auto;
        @BindView(R.id.bsb_auto)
        BubbleSeekBar bsb_auto;

        private void startAuto() {
            if (isAuto) return;
            isAuto = true;
            ll_auto.setVisibility(View.VISIBLE);
            ll_progress.setVisibility(View.GONE);
            img_auto.setImageResource(R.mipmap.ic_read_stop);
            myRunnable.a = 0;
            myRunnable.b = Integer.MAX_VALUE;
            myRunnable.c = DensityUtil.dp2px(context, bsb_auto.getProgressFloat() * 2);
            handler.postDelayed(myRunnable, speed);
        }

        private void stopAuto() {
            if (!isAuto) return;
            isAuto = false;
            ll_auto.setVisibility(View.GONE);
            ll_progress.setVisibility(View.VISIBLE);
            img_auto.setImageResource(R.mipmap.ic_read_start);
            myRunnable.b = 0;

        }

        private boolean isAuto = false;

        @BindView(R.id.img_auto)
        ImageView img_auto;

        ///////////////////////////////////////////////////// menu 3，设置画质。

        @BindView(R.id.img_definition)
        ImageView img_definition;

        @OnClick(R.id.ll_menu_3)
        public void menu_3() {
            if (isShowMenuDefinition) {
                closeMenuDefinition();
            } else {
                showMenuDefinition();
            }
        }

        public void showMenuDefinition() {
            if (isShowMenuDefinition) return;
            isShowMenuDefinition = true;
            toggleMenuDefinition(300);

        }

        public void closeMenuDefinition() {
            if (!isShowMenuDefinition) return;
            isShowMenuDefinition = false;
            toggleMenuDefinition(300);

        }

        private boolean isShowMenuDefinition = false;

        private void toggleMenuDefinition(int duration) {
            int h = ll_definition.getHeight();
            int fromY = 0, toY = 0;
            float fromA = 1.0f, toA = 1.0f;
            if (isShowMenuDefinition) {
                fromY = -h / 2;
                fromA = 0.0f;
                ll_definition.setVisibility(View.VISIBLE);
            } else {
                toY = -h / 2;
                toA = 0.0f;
                ll_definition.setVisibility(View.GONE);
            }
            A.create()
                    .t(0, 0, fromY, toY)
                    .a(fromA, toA)
                    .duration(duration)
                    .listener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (!isShowMenuDefinition) ll_definition.setVisibility(View.GONE);
                        }
                    })
                    .into(ll_definition);
        }

        @BindView(R.id.ll_definition)
        LinearLayout ll_definition;

        // 流畅画质
        @OnClick(R.id.img_definition_low)
        public void setDefinitionLow() {
            Definition = Definition_Low;
            T.s("已切换到流畅画质");
            notifyItemShoewRangeChanged();
            img_definition.setImageResource(R.mipmap.ic_read_definition_low);
            closeMenuDefinition();
        }

        // 标清画质
        @OnClick(R.id.img_definition_middle)
        public void setDefinitionMiddle() {
            Definition = Definition_Middle;
            T.s("已切换到标清画质");
            notifyItemShoewRangeChanged();
            img_definition.setImageResource(R.mipmap.ic_read_definition_middle);
            closeMenuDefinition();
        }

        // 高清画质
        @OnClick(R.id.img_definition_high)
        public void setDefinitionHigh() {
            Definition = Definition_High;
            T.s("已切换到高清画质");
            notifyItemShoewRangeChanged();
            img_definition.setImageResource(R.mipmap.ic_read_definition_high);
            closeMenuDefinition();
        }


        ///////////////////////////////////////////////////// menu 4，设置亮度
        @OnClick(R.id.ll_menu_4)
        public void menu_4() {
            if (isShowMenuBrightness) {
                closeMenuBrightness();
            } else {
                showMenuBrightness();
            }
        }

        public void showMenuBrightness() {
            if (isShowMenuBrightness) return;
            isShowMenuBrightness = true;
            toggleMenuBrightness(300);

        }

        public void closeMenuBrightness() {
            if (!isShowMenuBrightness) return;
            isShowMenuBrightness = false;
            toggleMenuBrightness(300);

        }

        private boolean isShowMenuBrightness = false;

        private void toggleMenuBrightness(int duration) {
            int h = ll_brightness.getHeight();
            int fromY = 0, toY = 0;
            float fromA = 1.0f, toA = 1.0f;
            if (isShowMenuBrightness) {
                fromY = -h / 2;
                fromA = 0.0f;
                ll_brightness.setVisibility(View.VISIBLE);
            } else {
                toY = -h / 2;
                toA = 0.0f;
                ll_brightness.setVisibility(View.GONE);
            }
            A.create()
                    .t(0, 0, fromY, toY)
                    .a(fromA, toA)
                    .duration(duration)
                    .listener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (!isShowMenuBrightness) ll_brightness.setVisibility(View.GONE);
                        }
                    })
                    .into(ll_brightness);
        }

        @BindView(R.id.ll_brightness)
        LinearLayout ll_brightness;
        @BindView(R.id.v_brightness)
        View v_brightness;
        @BindView(R.id.bsb_brightness)
        BubbleSeekBar bsb_brightness;

        ///////////////////////////////////////////////////// menu 5，显示章节目录。
        @OnClick(R.id.ll_menu_5)
        public void menu_5() {
            if (!isShowMenuCatalog) {
                closeMenu();
                showMenuCatalog();
            }
        }

        public void showMenuCatalog() {
            if (isShowMenuCatalog) return;
            isShowMenuCatalog = true;
            scrollCatalog();
            toggleMenuCatalog(300);

        }

        public void closeMenuCatalog() {
            if (!isShowMenuCatalog) return;
            isShowMenuCatalog = false;
            toggleMenuCatalog(300);

        }

        public void setCatalogChapterList(List<ChapterBean> list) {
            mCatalogChapterList.clear();
            mCatalogChapterList.addAll(list);
            mCatalogAdapter.notifyDataSetChanged();
        }

        public void scrollCatalog() {
            mCatalogAdapter.notifyDataSetChanged();
            int j = mCatalogChapterList.size() - 1;
            for (int i = 0; i < mCatalogChapterList.size(); i++) {
                if (mCatalogChapterList.get(i).getChapter_id() == mChapterId) {
                    j = i;
                    break;
                }
            }
            int p = j - 3 > 0 ? j - 3 : 0;
            if (p < 0 || p > mCatalogChapterList.size() - 1) return;// 防止越界。
            LinearLayoutManager mLayoutManager = (LinearLayoutManager) rv_catalog.getLayoutManager();
            mLayoutManager.scrollToPositionWithOffset(p, 0);
        }

        private boolean isShowMenuCatalog = false;

        private void toggleMenuCatalog(int duration) {
            int w = ll_catalog.getWidth();
            int fromX = 0, toX = 0;
            if (isShowMenuCatalog) {
                fromX = w;
                ll_catalog.setVisibility(View.VISIBLE);
            } else {
                toX = w;
                ll_catalog.setVisibility(View.GONE);
            }
            A.create()
                    .t(fromX, toX, 0, 0)
                    .duration(duration)
                    .listener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (!isShowMenuCatalog) ll_catalog.setVisibility(View.GONE);
                        }
                    })
                    .into(ll_catalog);
        }

        @BindView(R.id.ll_catalog)
        LinearLayout ll_catalog;

        @OnClick({R.id.img_catalog_all, R.id.tv_catalog_all})
        public void catalog_all() {
            mPresenter.goDetails(mBook.getHref());
            mPresenter.finish();
        }


        @BindView(R.id.img_catalog_xu)
        ImageView img_catalog_xu;
        @BindView(R.id.tv_catalog_xu)
        TextView tv_catalog_xu;
        private int xu = 1;

        @OnClick({R.id.img_catalog_xu, R.id.tv_catalog_xu})
        public void catalog_xu() {
            if (xu == 1) {
                // 转为倒序。
                xu = -1;
                img_catalog_xu.setImageResource(R.mipmap.ic_read_catalog_reverse);
                tv_catalog_xu.setText("倒序");
                Collections.reverse(mCatalogChapterList);
                mCatalogAdapter.notifyDataSetChanged();
            } else {
                // 转为正序。
                xu = 1;
                img_catalog_xu.setImageResource(R.mipmap.ic_read_catalog_order);
                tv_catalog_xu.setText("正序");
                Collections.reverse(mCatalogChapterList);
                mCatalogAdapter.notifyDataSetChanged();
            }

        }

        @BindView(R.id.rv_catalog)
        RecyclerView rv_catalog;

        List<ChapterBean> mCatalogChapterList = new ArrayList<>();
        CommonAdapter mCatalogAdapter;

        ////////////////////////////////////////////////////// 总菜单
        private void closeChildMenu() {
            closeMenuDefinition();
            closeMenuBrightness();
            closeMenuCatalog();
        }

        @BindView(R.id.ll_progress)
        LinearLayout ll_progress;
        @BindView(R.id.bsb_1)
        BubbleSeekBar mBsb;

        @BindView(R.id.tv_chapter_var2)
        TextView tv_chapter_var2;

        @Override
        protected void initView() {
            super.initView();
            fl_top.setVisibility(View.INVISIBLE);
            ll_bottom.setVisibility(View.INVISIBLE);
            ll_definition.setVisibility(View.INVISIBLE);
            ll_brightness.setVisibility(View.INVISIBLE);
            ll_catalog.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void initData() {
            super.initData();
            rv_catalog.setLayoutManager(new LinearLayoutManager(context));
            mCatalogAdapter = new CommonAdapter<ChapterBean>(context, R.layout.layout_catalog_item, mCatalogChapterList) {
                @Override
                protected void convert(ViewHolder holder, ChapterBean chapter, int position) {
                    ImageView img_pic = holder.getView(R.id.img_catalog_pic);
                    TextView tv_name = holder.getView(R.id.tv_catalog_name);
                    LinearLayout ll_bg = holder.getView(R.id.ll_catalog_bg);
                    if (chapter.getChapter_id() == mChapterId) {
                        ll_bg.setBackgroundResource(R.color.colorPrimary);
                    } else {
                        ll_bg.setBackgroundResource(R.color.colorClarity);
                    }
                    if (chapter.getPrice() == 0) {
                        G.img(context, chapter.getImageLow(), img_pic);
                    } else {
                        G.img(context, R.mipmap.pic_need_money, img_pic);
                    }
                    tv_name.setText(chapter.getChapter_name());
                }
            };
            rv_catalog.setAdapter(mCatalogAdapter);

        }

        // 标记mBsb是非被触碰。
        private boolean isBsbOnTouch = false;

        @Override
        protected void initListener() {
            super.initListener();
            mBsb.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:
                            isBsbOnTouch = true;
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            isBsbOnTouch = false;
                            break;

                    }
                    return false;
                }
            });
            mBsb.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                @Override
                public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                    if (isBsbOnTouch) {// 被点击时，判断是否由于自身被触摸而引发的改变。
                        progressChanged(progress);
                    }
                }

                @Override
                public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }

                @Override
                public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }
            });

            bsb_auto.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                @Override
                public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }

                @Override
                public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                    myRunnable.c = DensityUtil.dp2px(context, (float) 2 * progressFloat);
                }

                @Override
                public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }
            });

            bsb_brightness.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                @Override
                public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                    v_brightness.setAlpha(1 - progressFloat);
                }

                @Override
                public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                }

                @Override
                public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }
            });

            mCatalogAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    mChapterId = mCatalogChapterList.get(position).getChapter_id();
                    goChChapterById(mChapterId);
                    mCatalogAdapter.notifyDataSetChanged();
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });

        }


        public void clickSome() {
            if (isShowMenuDefinition
                    || isShowMenuBrightness
                    || isShowMenuCatalog) {// 有子菜单打开先关闭子菜单。
                closeChildMenu();
                return;
            }

            if (isAuto) {// 自动播放时。
                changeMenu();
                return;
            }
            myRunnable.a = 0;
            myRunnable.b = MockUtil.getScreenHeight(context) / 2;
            myRunnable.c = MockUtil.getScreenHeight(context) / 30;
            handler.postDelayed(myRunnable, speed);
        }

        public void setMenu(ComicBean comic) {
            int max = comic.getVar_size();
            int p = comic.getVar();

            tv_chapter_name.setText(comic.getChapter_name());

            String var = p + "/" + max;
            tv_chapter_var.setText(var);
            tv_chapter_var2.setText(var);

            if (isBsbOnTouch) return;// bsb_1正在被点击时，不设置它。
            mBsb.getConfigBuilder()
                    .max(max)
                    .min(1)
                    .progress(p)
                    .build();
        }

        // mBsb主动控制mRecyclerView滑动。
        public void progressChanged(int p) {
            // 滑很快的话，holder可能等于null。返回NO_POSITION=-1。
            int a = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
            if (a == -1) return;// 防止越界。
            ComicBean comic = mData.get(a);
            int var = comic.getVar();
            int g = p - var;
            if (g == 0) return;// 防止无意义操作。
            int c = a + g;
            scrollToPosition(c);
        }


        private boolean isShow = false;

        public void showMenu() {
            if (isShow) return;
            isShow = true;
            toggleMenu(300);
            closeChildMenu();
        }

        public void closeMenu() {
            if (!isShow) return;
            isShow = false;
            toggleMenu(300);
            closeChildMenu();
        }

        public void changeMenu() {
            if (isShow) {
                closeMenu();
            } else {
                showMenu();
            }
        }

        private void toggleMenu(int duration) {
            int th = fl_top.getHeight();
            int bh = ll_bottom.getHeight();

            int fromY1 = 0, toY1 = 0, fromY2 = 0, toY2 = 0;
            if (isShow) {
                fromY1 = -th;
                fromY2 = bh;
                fl_top.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.VISIBLE);
            } else {
                toY1 = -th;
                toY2 = bh;
                fl_top.setVisibility(View.GONE);
                ll_bottom.setVisibility(View.GONE);
            }
            A.create()
                    .t(0, 0, fromY1, toY1)
                    .duration(duration)
                    .listener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (!isShow) fl_top.setVisibility(View.GONE);
                        }
                    })
                    .into(fl_top);

            A.create()
                    .t(0, 0, fromY2, toY2)
                    .duration(duration)
                    .listener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            if (!isShow) ll_bottom.setVisibility(View.GONE);
                        }
                    })
                    .into(ll_bottom);
        }
    }
}
