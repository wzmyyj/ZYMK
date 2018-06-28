package top.wzmyyj.zymk.view.activity;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.view.fragment.F_1;

public class MainActivity extends BaseMainActivity {

    @Override
    protected void initFTs(List<FT> fts) {
        fts.add(new FT(new F_1(), "主页", R.mipmap.svg_tab_bar_main, R.mipmap.svg_tab_bar_main_hl));
        fts.add(new FT(new F_1(), "哈哈", R.mipmap.svg_tab_bar_kind, R.mipmap.svg_tab_bar_kind_hl));
        fts.add(new FT(new F_1(), "呼呼", R.mipmap.svg_tab_bar_find, R.mipmap.svg_tab_bar_find_hl));
        fts.add(new FT(new F_1(), "啦啦", R.mipmap.svg_tab_bar_mine, R.mipmap.svg_tab_bar_mine_hl));
    }

}
