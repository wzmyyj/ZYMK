package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public interface IDetailsView extends IBaseView {
    void setBook(BookBean book);

    void setXi(XiBean xi);

    void setMu(MuBean mu);

    void setZi(ZiBean zi);

    void setBookList(List<BookBean> list);

    void setIsFavor(boolean isFavor);

    void setHistory(ChapterBean chapter);

}
