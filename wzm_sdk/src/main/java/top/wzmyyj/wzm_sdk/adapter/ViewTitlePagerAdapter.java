package top.wzmyyj.wzm_sdk.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wzm on 2018/04/17. email: 2209011667@qq.com
 */


public class ViewTitlePagerAdapter extends PagerAdapter {
    private String[] titles;
    private List<View> viewList;


    public ViewTitlePagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    public ViewTitlePagerAdapter(List<View> viewList, String[] titles) {
        this.viewList = viewList;
        this.titles = titles;
    }

    public ViewTitlePagerAdapter(List<View> viewList, List<String> titles) {
        this.viewList = viewList;
        String[] strings = new String[titles.size()];
        for (int i = 0; i < titles.size(); i++) {
            strings[i] = titles.get(i);
        }
        this.titles = strings;
    }


    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null) {
            return null;
        }
        return titles[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
