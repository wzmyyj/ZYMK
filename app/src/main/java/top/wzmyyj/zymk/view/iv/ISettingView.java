package top.wzmyyj.zymk.view.iv;

import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public interface ISettingView extends IBaseView {
    void setCache(String s);

    void setVersion(String s);

    void isCue(boolean is);
}
