package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.event.HistoryListChangeEvent;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.model.db.HistoryModel;
import top.wzmyyj.zymk.model.net.ComicModel;
import top.wzmyyj.zymk.model.net.box.ComicBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicPresenter extends BasePresenter<ComicContract.IView> implements ComicContract.IPresenter{

    private ComicModel mModel;
    private HistoryModel historyModel;

    public ComicPresenter(Activity activity, ComicContract.IView iv) {
        super(activity, iv);
        mModel = new ComicModel();
        historyModel = new HistoryModel(activity);
    }

    @Override
    public long getChapterId() {
        return mActivity.getIntent().getLongExtra("chapter_id", 0);
    }


    @Override
    public void loadData() {
        int id = mActivity.getIntent().getIntExtra("comic_id", 0);
        mModel.getComicInfo(id, new Observer<ComicBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ComicBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    List<ChapterBean> chapterList = box.getChapterList();
                    Collections.reverse(chapterList);// 反序
                    List<ComicBean> comicList = getComicData(chapterList);
                    mView.showData(box.getBook(), chapterList, box.getBookList(), comicList);
                } else {
                    mView.showLoadFail(box.getMsg());
                    mView.showToast(box.getMsg());
                }
//                mView.showToast("加载成功");
            }

            @Override
            public void onError(Throwable e) {
                mView.showLoadFail(e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private List<ComicBean> getComicData(List<ChapterBean> chapterList) {
        List<ComicBean> comicList = new ArrayList<>();

        if (chapterList == null || chapterList.size() == 0) return comicList;

        for (ChapterBean chapter : chapterList) {

            int start = chapter.getStart_var();
            int end = chapter.getEnd_var();
//            chapter.setPrice(0);

            for (int i = start; i <= end; i++) {
                ComicBean comic = new ComicBean();
                comic.setChapter_id(chapter.getChapter_id());
                comic.setChapter_name(chapter.getChapter_name());
                comic.setChapter_title(chapter.getChapter_title());
                comic.setPrice(chapter.getPrice());
                comic.setImg_high(chapter.getImageHigh(i));
                comic.setImg_middle(chapter.getImageMiddle(i));
                comic.setImg_low(chapter.getImageLow(i));
                comic.setVar(i);
                comic.setVar_size(end - start + 1);
                comicList.add(comic);
            }

        }
        return comicList;
    }

    @Override
    public void goDetails(String href) {
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity2(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

    @Override
    public void goSetting() {
        I.toSettingActivity(mActivity);
    }

    @Override
    public void saveHistory(BookBean book, ChapterBean chapter) {
        historyModel.insert(book, chapter, new Observer<HistoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HistoryBean historyBean) {
                if (historyBean == null) {
                    mView.showToast("保存失败！");
                    return;
                }
                EventBus.getDefault().post(new HistoryListChangeEvent(true));
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
