package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.model.data.TypeData;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_2View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class TypePresenter extends BasePresenter<IF_2View> {
    public TypePresenter(Activity activity, IF_2View iv) {
        super(activity, iv);
    }

    public List<TypeBean> getData(int w) {
        List<TypeBean> data = new ArrayList<>();
        switch (w) {
            case 0:
                for (int i = 0; i < TypeData.type_0.length; i++)
                    data.add(new TypeBean(TypeData.type[0],
                            TypeData.type_0[i], TypeData.pic_0[i]));
                break;
            case 1:
                for (int i = 0; i < TypeData.type_1.length; i++)
                    data.add(new TypeBean(TypeData.type[1],
                            TypeData.type_1[i], TypeData.pic_1[i]));
                break;
            case 2:
                for (int i = 0; i < TypeData.type_2.length; i++)
                    data.add(new TypeBean(TypeData.type[2],
                            TypeData.type_2[i], TypeData.pic_2[i]));
                break;
            case 3:
                for (int i = 0; i < TypeData.type_3.length; i++)
                    data.add(new TypeBean(TypeData.type[3],
                            TypeData.type_3[i], TypeData.pic_3[i]));
                break;
        }
        return data;
    }

    public String getTitle(int w) {
        return TypeData.type[w];
    }

}
