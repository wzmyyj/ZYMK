package top.wzmyyj.wzm_sdk.activity;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;
import top.wzmyyj.wzm_sdk.widget.TabMenu;

/**
 * Created by wzm on 2018/04/18. email: 2209011667@qq.com
 */
public abstract class ViewPagerFragmentActivity extends InitActivity {

    protected List<Fragment> mFragmentList = new ArrayList<>();
    protected String[] mStr = new String[6];
    protected int[] mIcon1 = new int[6];
    protected int[] mIcon2 = new int[6];

    protected TabMenu mTabMenu;
    protected ViewPager mViewPager;
    protected List<FT> mFTs = new ArrayList<>();

    protected static class FT {
        Fragment fragment;
        String str;
        int icon1;
        int icon2;

        public FT(Fragment fragment, String str, int icon1, int icon2) {
            this.fragment = fragment;
            this.str = str;
            this.icon1 = icon1;
            this.icon2 = icon2;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pager_fragment);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabMenu = (TabMenu) findViewById(R.id.tabMenu);
        setSwipeBackEnable(false);
    }

    protected void addFT(Fragment fragment, String str, int icon1, int icon2) {
        mFTs.add(new FT(fragment, str, icon1, icon2));
    }

    protected abstract void initFTs();

    @Override
    protected void initData() {
        initFTs();
        if (mFTs == null || mFTs.size() == 0) {
            return;
        }
        int i = 0;
        for (FT ft : mFTs) {
            mFragmentList.add(ft.fragment);
            mStr[i] = ft.str;
            mIcon1[i] = ft.icon1;
            mIcon2[i] = ft.icon2;
            if (++i >= 6) break;
        }
        @SuppressWarnings("deprecation")
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @NonNull
            @Override
            public androidx.fragment.app.Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mTabMenu.setItemText(mStr)
                .setItemIcon(mIcon1, mIcon2)
                .setupWithViewPager(mViewPager);
        int which = getWhich();
        if (which < mStr.length)
            mTabMenu.change(which);
    }

    protected int getWhich() {
        return 0;
    }

    @Override
    protected void initListener() {
    }
}
