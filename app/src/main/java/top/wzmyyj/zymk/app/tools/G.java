package top.wzmyyj.zymk.app.tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import top.wzmyyj.zymk.R;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class G {

    public static void img(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)

//                .placeholder(R.mipmap.ic_progress)
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_error))
                .into(img);
    }

    public static void imgBlur(Context context, String url, ImageView img, int r) {
        if (url == null) return;
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().error(R.mipmap.ic_error))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(r, 4)))
                .transition(new DrawableTransitionOptions().crossFade(400))
                .into(img);

    }

    public static void img(Context context, int res_id, ImageView img) {
        Glide.with(context)
                .load(res_id)
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_error))
                .into(img);
    }
}
