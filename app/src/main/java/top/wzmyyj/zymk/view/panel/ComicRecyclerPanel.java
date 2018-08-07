package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.wzm_sdk.adapter.ivd.SingleIVD;
import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.ComicPresenter;
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
        addPanels(new ComicLoadPasePanel(context, mPresenter));
    }

    @Override
    protected void initView() {
        super.initView();
        mFrameLayout.addView(getPanelView(0));
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

                if (comicBean.getPrice() == 0) {
                    switch (Definition) {
                        case Definition_Low:
                            G.img(context, comicBean.getImg_low(), img_comic);
                            break;
                        case Definition_Middle:
                            G.img(context, comicBean.getImg_middle(), img_comic);
                            break;
                        case Definition_High:
                            G.img(context, comicBean.getImg_high(), img_comic);
                            break;
                    }
                } else {
                    G.img(context, R.mipmap.pic_need_money, img_comic);
                }

            }
        });
    }

    @Override
    public void update() {
        mPresenter.loadData();
    }

    private BookBean mBook;
    private List<ChapterBean> mChapterList = new ArrayList<>();
    private List<BookBean> mBookList = new ArrayList<>();

    @Override
    public Object f(int w, Object... objects) {
        L.d(w + "");

        getPanel(0).f(w, null);
        if (w == -1) {
            return null;
        }
        BookBean book = (BookBean) objects[0];
        if (book != null) {
            mBook = book;
        }

        List<ChapterBean> chapterList = (List<ChapterBean>) objects[1];
        if (chapterList != null && chapterList.size() > 0) {
            mChapterList.addAll(chapterList);
        }

        List<BookBean> bookList = (List<BookBean>) objects[2];
        if (bookList != null && bookList.size() > 0) {
            mBookList.addAll(bookList);
        }


        setComicData();
        return super.f(w, objects);
    }

    private void setComicData() {
        mData.clear();
        for (ChapterBean chapter : mChapterList) {
            int start = chapter.getStart_var();
            int end = chapter.getEnd_var();
            for (int i = start; i <= end; i++) {
                ComicBean comic = new ComicBean();
                comic.setChapter_id(chapter.getChapter_id());
                comic.setChapter_name(chapter.getChapter_name());
                comic.setChapter_title(chapter.getChapter_title());
                comic.setPrice(chapter.getPrice());
                comic.setImg_high(Urls.ZYMK_Comic + chapter.getChapter_image().getHigh().replace("$$", "" + i));
                comic.setImg_middle(Urls.ZYMK_Comic + chapter.getChapter_image().getMiddle().replace("$$", "" + i));
                comic.setImg_low(Urls.ZYMK_Comic + chapter.getChapter_image().getLow().replace("$$", "" + i));
                mData.add(comic);
            }

        }

        notifyDataSetChanged();


    }
}
