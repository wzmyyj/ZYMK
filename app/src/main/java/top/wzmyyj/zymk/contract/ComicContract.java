package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface ComicContract {

    interface IView extends IBaseView {
        void showData(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList, List<ComicBean> comicList);

        void showLoadFail(String msg);
    }

    interface IPresenter extends IBasePresenter {
        long getChapterId();

        void loadData();

        void goSetting();

        void goDetails(String href);

        void saveHistory(BookBean book, ChapterBean chapter);


    }


}
