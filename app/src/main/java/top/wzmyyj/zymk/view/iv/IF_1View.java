package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;
import top.wzmyyj.zymk.view.iv.base.IUpdateView;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public interface IF_1View extends IBaseView, IUpdateView {
    void loadFavor(List<FavorBean> list);
}
