package top.wzmyyj.zymk.app.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import top.wzmyyj.zymk.app.tools.G;


/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        G.img(context, (String) path, imageView);
    }
}
