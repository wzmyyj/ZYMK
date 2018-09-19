package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public interface ITypeView extends IBaseView{
    void update(List<TypeBean>... typeList);
}
