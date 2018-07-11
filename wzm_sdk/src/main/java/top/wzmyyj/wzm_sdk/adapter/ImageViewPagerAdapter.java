package top.wzmyyj.wzm_sdk.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ImageViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageList;

    public ImageViewPagerAdapter(List<ImageView> imageList) {
        this.imageList = imageList;
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        View v = imageList.get(position % imageList.size());

        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }

        container.addView(v);
        return imageList.get(position % imageList.size());
    }

    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }


}
