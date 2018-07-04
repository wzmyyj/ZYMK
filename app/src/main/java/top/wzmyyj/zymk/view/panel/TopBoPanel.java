package top.wzmyyj.zymk.view.panel;

import android.content.Context;

import com.bumptech.glide.Glide;

import top.wzmyyj.wzm_sdk.panel.BoPanel;
import top.wzmyyj.zymk.R;


/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 */

public class TopBoPanel extends BoPanel {
    public TopBoPanel(Context context) {
        super(context);
    }

    @Override
    protected int getSize() {
        return 6;
    }

    @Override
    public void setBoData() {
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/064.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(0));

        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/122.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(1));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/118.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(2));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/104.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(3));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/112.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(4));
        Glide.with(context)
                .load("https://image.zymkcdn.com/file/news/000/001/143.jpg-1920x560.webp")
                .centerCrop()
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_error)
                .into(mImageList.get(5));

        mTV.setText(mDosc[mViewPager.getCurrentItem()]);
    }
}
