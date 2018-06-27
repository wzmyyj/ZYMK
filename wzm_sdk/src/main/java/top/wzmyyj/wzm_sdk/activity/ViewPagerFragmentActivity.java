package top.wzmyyj.wzm_sdk.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.view.TabMenu;

/**
 * Created by wzm on 2018/4/18 0018.
 */

public abstract class ViewPagerFragmentActivity extends InitActivity {
    private List<Fragment> mFragmentList;
    private TabMenu mTabMenu;
    private ViewPager mViewPager;

    protected class FPT {
        List<Fragment> fragments = new ArrayList<>();
        String[] str = new String[6];
        int[] icon1 = new int[6];
        int[] icon2 = new int[6];

        public FPT add(Fragment fragment, String text, int ic_1, int ic_2) {
            int i = this.fragments.size();
            if (i >= 6) {
                return this;
            }
            this.fragments.add(fragment);
            this.str[i] = text;
            this.icon1[i] = ic_1;
            this.icon2[i] = ic_2;
            return this;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pager_fragment);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabMenu = (TabMenu) findViewById(R.id.tabMenu);

    }

    @Override
    protected void initData() {
        FPT fpt = getFPT(new FPT());
        int which = getWhich();
        if (fpt == null) {
            return;
        }
        mFragmentList = new ArrayList<>();
        mFragmentList = fpt.fragments;


        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int a) {
                return mFragmentList.get(a);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mTabMenu.setItemText(fpt.str)
                .setItemIcon(fpt.icon1, fpt.icon2)
                .setupWithViewPager(mViewPager);

    }

    protected abstract FPT getFPT(FPT fpt);

    protected int getWhich() {
        return 0;
    }


    @Override
    protected void initListener() {
    }


}
