package top.wzmyyj.zymk.view.activity;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.view.activity.base.BaseMainActivity;
import top.wzmyyj.zymk.view.fragment.F_1;
import top.wzmyyj.zymk.view.fragment.F_2;
import top.wzmyyj.zymk.view.fragment.F_3;
import top.wzmyyj.zymk.view.fragment.F_4;

/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initFTs(List<FT> fts) {
        fts.add(new FT(new F_1(), "主页", R.mipmap.svg_tab_bar_main, R.mipmap.svg_tab_bar_main_hl));
        fts.add(new FT(new F_2(), "分类", R.mipmap.svg_tab_bar_kind, R.mipmap.svg_tab_bar_kind_hl));
        fts.add(new FT(new F_3(), "发现", R.mipmap.svg_tab_bar_find, R.mipmap.svg_tab_bar_find_hl));
        fts.add(new FT(new F_4(), "我的", R.mipmap.svg_tab_bar_mine, R.mipmap.svg_tab_bar_mine_hl));
    }


}
