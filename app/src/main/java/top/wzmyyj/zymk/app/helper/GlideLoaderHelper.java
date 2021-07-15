package top.wzmyyj.zymk.app.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;
import top.wzmyyj.zymk.R;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 * Glide 图片加载器的封装类。
 */
public class GlideLoaderHelper {

    private final static RequestOptions ORIGINAL_OPTIONS = new RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

    public static void img(ImageView img, String url) {
        Glide.with(img)
                .load(url)
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_error))
                .into(img);
    }

    public static void img(ImageView img, String url, int error) {
        Glide.with(img)
                .load(url)
                .apply(new RequestOptions().centerCrop().error(error))
                .into(img);
    }

    public static void imgBlur(ImageView img, String url, int r) {
        if (url == null) return;
        Glide.with(img)
                .load(url)
                .apply(new RequestOptions().error(R.mipmap.ic_error))
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(r, 4)))
                .transition(new DrawableTransitionOptions().crossFade(400))
                .into(img);

    }

    public static void img(ImageView img, int res_id) {
        Glide.with(img)
                .load(res_id)
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_error))
                .into(img);
    }

    public static void load(ImageView view, final String url, Target<Bitmap> target) {
        Glide.with(view).asBitmap().load(url).apply(ORIGINAL_OPTIONS).into(target);
    }

    public static void load(Context context, final String url, Target<Bitmap> target) {
        Glide.with(context).asBitmap().load(url).apply(ORIGINAL_OPTIONS).into(target);
    }

    public static void load(ImageView view, final int res, Target<Bitmap> target) {
        Glide.with(view).asBitmap().load(res).apply(ORIGINAL_OPTIONS).into(target);
    }

    public static void clear(ImageView img) {
        Glide.with(img).clear(img);
    }

    public static <T> void clear(Context context, Target<T> target) {
        Glide.with(context).clear(target);
    }

}
