package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.model.net.ComicModel;
import top.wzmyyj.zymk.model.net.box.ComicBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IComicView;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicPresenter extends BasePresenter<IComicView> {

    private ComicModel mModel;

    public ComicPresenter(Activity activity, IComicView iv) {
        super(activity, iv);
        mModel = new ComicModel();
    }

    public long getChapter_id() {
        return mActivity.getIntent().getLongExtra("chapter_id", 0);
    }


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
                    mView.update(0, box.getBook(), chapterList, box.getBookList(), comicList);
                } else {
                    mView.update(-1, box.getMsg());
                    mView.showToast(box.getMsg());
                }
//                mView.showToast("加载成功");
            }

            @Override
            public void onError(Throwable e) {
                mView.update(-1, e.getMessage());
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

            for (int i = start; i <= end; i++) {
                ComicBean comic = new ComicBean();
                comic.setChapter_id(chapter.getChapter_id());
                comic.setChapter_name(chapter.getChapter_name());
                comic.setChapter_title(chapter.getChapter_title());
                comic.setPrice(chapter.getPrice());
                comic.setImg_high(Urls.ZYMK_Comic + chapter.getChapter_image().getHigh().replace("$$", "" + i));
                comic.setImg_middle(Urls.ZYMK_Comic + chapter.getChapter_image().getMiddle().replace("$$", "" + i));
                comic.setImg_low(Urls.ZYMK_Comic + chapter.getChapter_image().getLow().replace("$$", "" + i));
                comic.setVar(i);
                comic.setVar_size(end - start + 1);
                comicList.add(comic);
            }

        }
        ComicBean comic = new ComicBean();
        comic.setChapter_id(-1);
        comic.setVar(1);
        comic.setVar_size(1);
        comicList.add(comic);
        return comicList;
    }
}
