package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public interface ITyView extends IBaseView {
    void setTitle(String s);

    void update(boolean isFirst, List<BookBean> books, String base, String next);

    void loadFail(String msg);
}
