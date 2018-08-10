package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */

public class ComicRecyclerPanel extends BaseRecyclerPanel<ComicBean, ComicPresenter> {

    public ComicRecyclerPanel(Context context, ComicPresenter comicPresenter) {
        super(context, comicPresenter);
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(
                mMeunPanel = new ComicMeunPanel(context, mPresenter),
                new ComicLoadPasePanel(context, mPresenter)
        );
    }

    private ComicMeunPanel mMeunPanel;

    @Override
    protected void initView() {
        super.initView();
        mFrameLayout.addView(getPanelView(0));
        mFrameLayout.addView(getPanelView(1));
    }

    private long mChapterId = 0;

    @Override
    protected void initData() {
        super.initData();
        mChapterId = mPresenter.getChapter_id();
    }

    @Override
    protected void setData() {

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

            private int load_position_now = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int p = mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0));

                if (p == load_position_now) return;
                load_position_now = p;
                setMenu(p);
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

    private void setMenu(int p) {
        mMeunPanel.tv_chapter_name.setText(mData.get(p).getChapter_name());
        String var = mData.get(p).getVar() + "/" + mData.get(p).getVar_size();
        mMeunPanel.tv_chapter_var.setText(var);
        mMeunPanel.tv_chapter_var2.setText(var);
        mMeunPanel.bsb_1.getConfigBuilder()
                .max(mData.get(p).getVar_size())
                .min(1)
                .progress(mData.get(p).getVar())
                .build();
    }

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


    @Override
    public Object f(int w, Object... objects) {
        L.d(w + "");

        getPanel(1).f(w, objects);
        if (w == -1) {
            return null;
        }
        BookBean book = (BookBean) objects[0];
        if (book != null) {
            mBook = book;
        }

        List<ChapterBean> chapterList = (List<ChapterBean>) objects[1];
        if (chapterList != null && chapterList.size() > 0) {
            mChapterList.clear();
            mChapterList.addAll(chapterList);
        }

        List<BookBean> bookList = (List<BookBean>) objects[2];
        if (bookList != null && bookList.size() > 0) {
            mBookList.clear();
            mBookList.addAll(bookList);
        }

        List<ComicBean> comicList = (List<ComicBean>) objects[3];
        if (comicList != null && comicList.size() > 0) {
            mComicList.clear();
            mComicList.addAll(comicList);
        }

        addOnce();
        mHandler.sendEmptyMessageDelayed(1, 500);
        return super.f(w, objects);
    }

    // 第一次添加数据
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
        setMenu(0);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.sendEmptyMessage(0);
    }


    // 内部类panel.菜单面板。
    public class ComicMeunPanel extends BasePanel<ComicPresenter> {

        public ComicMeunPanel(Context context, ComicPresenter p) {
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
        public void showMenu1() {

        }

        ///////////////////////////////////////////////////// menu 2，设置自动滑动和停止。
        @OnClick(R.id.ll_menu_2)
        public void showMenu2() {

        }

        ///////////////////////////////////////////////////// menu 3，设置画质。
        @OnClick(R.id.ll_menu_3)
        public void showMenu3() {
            rl_definition.setVisibility(View.VISIBLE);
        }

        @OnClick(R.id.rl_definition)
        public void closeMenu3() {
            rl_definition.setVisibility(View.GONE);
        }

        @BindView(R.id.img_definition)
        ImageView img_definition;
        @BindView(R.id.rl_definition)
        RelativeLayout rl_definition;

        // 流畅画质
        @OnClick(R.id.img_definition_low)
        public void setDefinitionLow() {
            Definition = Definition_Low;
            T.s("已切换到流畅画质");
            // 只刷新当前显示的item，防止图片跳闪。
            mHeaderAndFooterWrapper.notifyItemRangeChanged(
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0)),
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1))
            );
            img_definition.setImageResource(R.mipmap.ic_read_definition_low);
            closeMenu3();
        }

        // 标清画质
        @OnClick(R.id.img_definition_middle)
        public void setDefinitionMiddle() {
            Definition = Definition_Middle;
            T.s("已切换到标清画质");
            // 只刷新当前显示的item，防止图片跳闪。
            mHeaderAndFooterWrapper.notifyItemRangeChanged(
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0)),
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1))
            );
            img_definition.setImageResource(R.mipmap.ic_read_definition_middle);
            closeMenu3();
        }

        // 高清画质
        @OnClick(R.id.img_definition_high)
        public void setDefinitionHigh() {
            Definition = Definition_High;
            T.s("已切换到高清画质");
            // 只刷新当前显示的item，防止图片跳闪。
            mHeaderAndFooterWrapper.notifyItemRangeChanged(
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(0)),
                    mRecyclerView.getChildAdapterPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1))
            );
            img_definition.setImageResource(R.mipmap.ic_read_definition_high);
            closeMenu3();
        }



        ///////////////////////////////////////////////////// menu 4，设置亮度
        @OnClick(R.id.ll_menu_4)
        public void showMenu4() {

        }

        ///////////////////////////////////////////////////// menu 5，显示章节目录。
        @OnClick(R.id.ll_menu_5)
        public void showMenu5() {

        }

        @BindView(R.id.bsb_1)
        BubbleSeekBar bsb_1;

        @BindView(R.id.tv_chapter_var2)
        TextView tv_chapter_var2;

        @BindView(R.id.img_auto)
        ImageView img_auto;


    }
}
