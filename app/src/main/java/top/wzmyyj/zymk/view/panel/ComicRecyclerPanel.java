package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.wzm_sdk.utils.MockUtil;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.app.helper.target.FixResTarget;
import top.wzmyyj.zymk.app.helper.target.LoadComicTarget;
import top.wzmyyj.zymk.app.helper.target.PreLoadComicTarget;
import top.wzmyyj.zymk.base.panel.BaseRecyclerPanel;
import top.wzmyyj.zymk.contract.ComicContract;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 * 漫画阅读主页面。
 */
@SuppressLint("NonConstantResourceId")
public class ComicRecyclerPanel extends BaseRecyclerPanel<ComicBean, ComicContract.IPresenter> {

    private final static int Definition_Low = 1;
    private final static int Definition_Middle = 2;
    private final static int Definition_High = 3;
    private final static int Add_Previous = 1;
    private final static int Add_After = 2;
    private ComicMenuPanel mMenuPanel;
    private ComicLoadPosePanel mLoadPosePanel;
    private int definition = Definition_Middle;
    private long mChapterId = 0;
    private int indexPrevious = -1;
    private int indexAfter = -1;
    private boolean isScrollByCatalog = false;
    private BookBean mBook;
    private final List<ChapterBean> mChapterList = new ArrayList<>();
    private final List<BookBean> mBookList = new ArrayList<>();
    private final MyRunnable myRunnable = new MyRunnable();
    private final Handler scrollHandler = new Handler(Looper.getMainLooper());
    private final List<Target<Bitmap>> targetList = new LinkedList<>();

    public ComicRecyclerPanel(Context context, ComicContract.IPresenter comicPresenter) {
        super(context, comicPresenter);
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                mMenuPanel = new ComicMenuPanel(context, mPresenter),
                mLoadPosePanel = new ComicLoadPosePanel(context, mPresenter)
        );
        mMenuPanel.setComicRecyclerPanel(this);
    }

    public void setDefinitionLow() {
        this.definition = Definition_Low;
        notifyItemShowRangeChanged();
        T.s("已切换到流畅画质");
    }

    public void setDefinitionMiddle() {
        this.definition = Definition_Middle;
        notifyItemShowRangeChanged();
        T.s("已切换到标清画质");
    }

    public void setDefinitionHigh() {
        this.definition = Definition_High;
        notifyItemShowRangeChanged();
        T.s("已切换到高清画质");
    }

    public long getChapterId() {
        return mChapterId;
    }

    public void postAutoScroll() {
        scrollHandler.postDelayed(myRunnable, 5);
    }

    public MyRunnable getMyRunnable() {
        return myRunnable;
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

    // 前往指定的章节。
    public void goSeeChapterById(long id) {
        mChapterId = id;
        int p = -1;
        for (int i = 0; i < mData.size(); i++) {// mData中查找指定章节。
            if (mData.get(i).getChapterId() == id) {
                p = i;
                break;
            }
        }
        if (p == -1) {// mData中没有所需章节。
            mLoadPosePanel.showLoad();
            addOnce();
            mRecyclerView.post(() -> mLoadPosePanel.loadSuccess());
            return;
        }
        // 滑到指定章节。
        isScrollByCatalog = true;
        scrollToPosition(p);
    }

    public void goDetails() {
        mPresenter.goDetails(mBook.getHref());
    }

    public void loadFail() {
        mLoadPosePanel.loadFail();
    }

    @Override
    protected void initView() {
        super.initView();
        mFrameLayout.addView(getPanelView(0));
        mFrameLayout.addView(getPanelView(1));
        // 消除mRecyclerView刷新的动画。
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    protected void initData() {
        super.initData();
        mChapterId = mPresenter.getChapterId();
    }

    @Override
    protected void setIVD(List<IVD<ComicBean>> ivd) {
        ivd.add(new SingleIVD<ComicBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_comic_image;
            }

            @Override
            public void convert(ViewHolder holder, ComicBean comicBean, int position) {
                ImageView img = holder.getView(R.id.img_comic);
                if (comicBean.getChapterId() == -1) {
                    GlideLoaderHelper.load(img, R.mipmap.pic_comic_end, new FixResTarget(img));
                    return;
                }
                if (comicBean.getPrice() > 0) {
                    GlideLoaderHelper.load(img, R.mipmap.pic_need_money, new FixResTarget(img));
                    return;
                }
                int width = comicBean.getImgWidth();
                int height = comicBean.getImgHeight();
                if (width > 0 && height > 0) {
                    final int screenWidth = MockUtil.getScreenWidth(img.getContext());
                    float scale = ((float) height) / width;
                    ViewGroup.LayoutParams params = img.getLayoutParams();
                    params.width = screenWidth;
                    params.height = Math.round(scale * screenWidth);
                    img.setLayoutParams(params);
                }
                new LoadComicTarget(comicBean, img, getComicImage(comicBean)).load();
            }
        });
    }

    @Override
    public void viewRecycled(@NonNull ViewHolder holder) {
        super.viewRecycled(holder);
        GlideLoaderHelper.clear(holder.getView(R.id.img_comic));
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // 当前屏幕显示最上面一行的position。
            private int loadPositionNow = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mMenuPanel.isAuto() && recyclerView.getScrollState() != SCROLL_STATE_SETTLING) {
                    if (dy > 10) {
                        mMenuPanel.closeMenu();
                    } else if (dy < -10) {
                        mMenuPanel.showMenu();
                    }
                }
                int p = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
                if (p == -1 || p == loadPositionNow) return;
                loadPositionNow = p;
                mMenuPanel.setMenu(mData.get(p));
                long chapterId = mData.get(p).getChapterId();
                if (mChapterId != chapterId && !isScrollByCatalog) {
                    mChapterId = chapterId;
                    mMenuPanel.scrollCatalog();
                }
                if (isScrollByCatalog) {
                    if (mData.get(0).getChapterId() == mChapterId) {
                        mHandler.sendEmptyMessage(Add_Previous);
                    }
                    if (mData.get(mData.size() - 1).getChapterId() == mChapterId) {
                        mHandler.sendEmptyMessage(Add_After);
                    }
                    isScrollByCatalog = false;
                } else {
                    preLoadImageList(mData, loadPositionNow);
                    if (loadPositionNow < 3) {
                        mHandler.sendEmptyMessage(Add_Previous);
                    } else if (loadPositionNow > mData.size() - 5) {
                        mHandler.sendEmptyMessage(Add_After);
                    }
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int w = msg.what;
            if (w == Add_Previous) {
                addPrevious();
            } else if (w == Add_After) {
                addAfter();
            }
        }
    };

    @Override
    public void update() {
        mPresenter.loadData();
    }

    public void setComicData(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList) {
        mLoadPosePanel.loadSuccess();
        if (book != null) {
            mBook = book;
        }
        if (chapterList != null && chapterList.size() > 0) {
            mChapterList.clear();
            mChapterList.addAll(chapterList);
            mChapterList.add(mPresenter.chapterEnd());
        }
        if (bookList != null && bookList.size() > 0) {// 暂时没用。
            mBookList.clear();
            mBookList.addAll(bookList);
            mPresenter.log("mBookList Size:" + mBookList.size());
        }
        if (mChapterId == 0) {
            mChapterId = mChapterList.get(0).getChapterId();
        }
        addOnce();
        mMenuPanel.setCatalogChapterList(chapterList);
    }

    // 第一次添加数据。
    private void addOnce() {
        mHandler.removeMessages(Add_Previous);
        mHandler.removeMessages(Add_After);
        long chapter_id = mChapterId;
        int index = -1, start = -1, end = -1;
        for (int i = 0; i < mChapterList.size(); i++) {
            if (mChapterList.get(i).getChapterId() == chapter_id) {
                index = start = end = i;
                break;
            }
        }
        if (index < 0 || index > mChapterList.size()) return;
        List<ComicBean> comicList = new ArrayList<>();
        int pos = 0;
        if (start > 0) {
            start--;
            ChapterBean chapterStart = mChapterList.get(start);
            comicList.addAll(mPresenter.getComicList(chapterStart));
            pos = comicList.size();
        }
        ChapterBean chapter = mChapterList.get(index);
        comicList.addAll(mPresenter.getComicList(chapter));
        if (end < mChapterList.size() - 1) {
            end++;
            ChapterBean chapterEnd = mChapterList.get(end);
            comicList.addAll(mPresenter.getComicList(chapterEnd));
        }
        if (pos >= comicList.size()) return;
        mLoadPosePanel.setFirstComic(comicList.get(pos));
        for (Target<Bitmap> target : targetList) {
            GlideLoaderHelper.clear(context, target);
        }
        preLoadImageList(comicList, pos);
        int removedSize = mData.size();
        mData.clear();
        mWrapperAdapter.notifyItemRangeRemoved(0, removedSize);
        mData.addAll(comicList);
        mWrapperAdapter.notifyItemRangeInserted(0, comicList.size());
        scrollToPosition(pos);
        mMenuPanel.setMenu(comicList.get(pos));
        indexPrevious = indexAfter = -1;
        if (start > 0) indexPrevious = start - 1;
        if (end < mChapterList.size() - 1) indexAfter = end + 1;
    }

    // 向前加载一章。
    private void addPrevious() {
        if (indexPrevious < 0 || indexPrevious >= mChapterList.size()) return;
        int start = indexPrevious;
        indexPrevious = -1;
        ChapterBean chapter = mChapterList.get(start);
        List<ComicBean> comicList = mPresenter.getComicList(chapter);
        preLoadImageList(comicList, 0);
        mData.addAll(0, comicList);
        mWrapperAdapter.notifyItemRangeInserted(0, comicList.size());
        if (start > 0) indexPrevious = start - 1;
    }

    // 向后加载一章。
    private void addAfter() {
        if (indexAfter < 0 || indexAfter >= mChapterList.size()) return;
        int end = indexAfter;
        indexAfter = -1;
        ChapterBean chapter = mChapterList.get(end);
        List<ComicBean> comicList = mPresenter.getComicList(chapter);
        preLoadImageList(comicList, 0);
        mData.addAll(comicList);
        notifyDataSetChanged();
        if (end < mChapterList.size() - 1) indexAfter = end + 1;
    }

    // 预加载图片。
    private void preLoadImageList(List<ComicBean> comicList, int index) {
        for (int i = 0; i < 7; i++) {
            int p = index + (i + 1) / 2 * (i % 2 == 0 ? 1 : -1);
            if (p >= 0 && p < comicList.size()) {
                ComicBean comic = comicList.get(p);
                preLoadImage(comic);
            } else {
                i++;
            }
        }
    }

    private void preLoadImage(ComicBean comic) {
        if (comic.getChapterId() == -1 || comic.getPrice() > 0) return;
        if (comic.getImgWidth() > 0 && comic.getImgHeight() > 0) return;
        if (comic.isLoading() || comic.isPreLoading()) return;
        CustomTarget<Bitmap> target = new PreLoadComicTarget(comic);
        if (targetList.size() >= 10) {
            GlideLoaderHelper.clear(context, targetList.remove(0));
        }
        targetList.add(target);
        GlideLoaderHelper.load(context, getComicImage(comic), target);
    }

    // mRecyclerView滑到指定position的位置。
    private void scrollToPosition(int p) {
        if (p < 0 || p > mData.size() - 1) return;// 防止越界。
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (mLayoutManager != null) mLayoutManager.scrollToPositionWithOffset(p, 0);
    }

    private void notifyItemShowRangeChanged() {
        // 只刷新当前显示的item，防止图片跳闪。
        int a = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));
        int b = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (a == -1 || b == -1) return;
        mWrapperAdapter.notifyItemRangeChanged(a, b);
    }

    private String getComicImage(ComicBean comicBean) {
        switch (definition) {
            case Definition_Low:
                return comicBean.getImgLow();
            case Definition_Middle:
                return comicBean.getImgMiddle();
            case Definition_High:
                return comicBean.getImgHigh();
        }
        return "";
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        mMenuPanel.clickSome();
    }

    @Override
    public void onStop() {
        super.onStop();
        // 保存阅读记录。
        for (ChapterBean chapter : mChapterList) {
            if (chapter.getChapterId() == mChapterId) {
                mPresenter.saveHistory(mBook, chapter);
                return;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(Add_Previous);
        mHandler.removeMessages(Add_After);
        scrollHandler.removeCallbacks(myRunnable);
    }

    public class MyRunnable implements Runnable {
        int a, b, c;

        @Override
        public void run() {
            if (a >= b || !mRecyclerView.canScrollVertically(1)) {
                a = b = c = 0;
                scrollHandler.removeCallbacks(myRunnable);
                return;
            } else {
                a += c;
            }
            int scroll = mRecyclerView.getScrollY();
            mRecyclerView.scrollBy(0, scroll + c);
            postAutoScroll();
        }
    }
}
