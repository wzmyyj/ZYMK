package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.TypeBean;

/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */
public class TypeBox {

    private final List<TypeBean> typeList1;
    private final List<TypeBean> typeList2;
    private final List<TypeBean> typeList3;
    private final List<TypeBean> typeList4;

    public TypeBox(List<TypeBean> typeList1, List<TypeBean> typeList2, List<TypeBean> typeList3, List<TypeBean> typeList4) {
        this.typeList1 = typeList1;
        this.typeList2 = typeList2;
        this.typeList3 = typeList3;
        this.typeList4 = typeList4;
    }

    public List<TypeBean> getTypeList1() {
        return typeList1;
    }

    public List<TypeBean> getTypeList2() {
        return typeList2;
    }

    public List<TypeBean> getTypeList3() {
        return typeList3;
    }

    public List<TypeBean> getTypeList4() {
        return typeList4;
    }
}
