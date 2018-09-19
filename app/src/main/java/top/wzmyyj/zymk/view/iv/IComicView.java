package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public interface IComicView extends IBaseView{
    void update(BookBean book, List<ChapterBean> chapterList, List<BookBean> bookList, List<ComicBean> comicList);

    void loadFail(String msg);
}
