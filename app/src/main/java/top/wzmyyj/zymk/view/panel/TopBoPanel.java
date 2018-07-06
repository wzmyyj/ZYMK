package top.wzmyyj.zymk.view.panel;

import android.content.Context;

import com.bumptech.glide.Glide;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.view.panel.base.BaseBoPanel;


/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 */

public class TopBoPanel extends BaseBoPanel {
    public TopBoPanel(Context context) {
        super(context);
    }

    @Override
    protected int getSize() {
        return 6;
    }

    @Override
    protected int getPointSize() {
        return 25;
    }

    @Override
    public void setBoData() {
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/064.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(0));

        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/122.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(1));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/118.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(2));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/104.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(3));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/112.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(4));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/143.jpg-1920x560.webp")
                .centerCrop()
                .error(R.mipmap.ic_error)
                .into(mImageList.get(5));

        mTV.setText(mDosc[mViewPager.getCurrentItem()]);
    }
}
