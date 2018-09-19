package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface DetailsContract {

    interface IView extends IBaseView {
        void setBook(BookBean book);

        void setXi(XiBean xi);

        void setMu(MuBean mu);

        void setZi(ZiBean zi);

        void setBookList(List<BookBean> list);

        void setIsFavor(boolean isFavor);

        void setHistory(ChapterBean chapter);
    }

    interface IPresenter extends IBasePresenter {
        void loadData();

        void goComic(int comic_id, long chapter_id);

        void goComic(int comic_id);

        void goDetails(String href);

        void isFavor(long id);

        void addFavor(BookBean book);

        void getHistoryRead(long id);
    }


}
