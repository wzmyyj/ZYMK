package top.wzmyyj.wzm_sdk.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by wzm on 2018/04/17. email: 2209011667@qq.com
 */
public class ViewTitlePagerAdapter extends PagerAdapter {

    private final String[] titles;
    private final List<View> viewList;

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
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null) return null;
        return titles[position];
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
