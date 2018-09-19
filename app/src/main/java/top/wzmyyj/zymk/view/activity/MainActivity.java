package top.wzmyyj.zymk.view.activity;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.view.activity.base.BaseMainActivity;
import top.wzmyyj.zymk.view.fragment.HomeFragment;
import top.wzmyyj.zymk.view.fragment.MineFragment;
import top.wzmyyj.zymk.view.fragment.FindFragment;
import top.wzmyyj.zymk.view.fragment.TypeFragment;

/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initFTs(List<FT> fts) {
        fts.add(new FT(new HomeFragment(), "主页", R.mipmap.svg_tab_bar_main, R.mipmap.svg_tab_bar_main_hl));
        fts.add(new FT(new TypeFragment(), "分类", R.mipmap.svg_tab_bar_kind, R.mipmap.svg_tab_bar_kind_hl));
        fts.add(new FT(new FindFragment(), "足迹", R.mipmap.svg_tab_bar_find, R.mipmap.svg_tab_bar_find_hl));
        fts.add(new FT(new MineFragment(), "我的", R.mipmap.svg_tab_bar_mine, R.mipmap.svg_tab_bar_mine_hl));
    }


}
