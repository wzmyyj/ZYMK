package top.wzmyyj.zymk.app.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import top.wzmyyj.zymk.R;


/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load((String)path)
//                .placeholder(R.mipmap.ic_progress)
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ico_bg))
                .into(imageView);
    }
}
