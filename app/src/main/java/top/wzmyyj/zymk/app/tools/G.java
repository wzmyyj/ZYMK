package top.wzmyyj.zymk.app.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.utils.GlideApp;
import top.wzmyyj.zymk.common.utils.MockUtil;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 * Glide 图片加载器的封装类。
 */

public class G {

    public static void clear(Context context, ImageView img) {
        Glide.with(context).clear(img);
    }

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


    public static void imgfix(final Context context, final String url, final ImageView img) {

        final DiskCacheStrategy cache = DiskCacheStrategy.NONE;
        final int ScreenWidth = MockUtil.getScreenWidth(context);
        GlideApp.with(context)
                .asBitmap()//强制Glide返回一个Bitmap对象
                .load(url)
                .placeholder(R.mipmap.ico_p)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        float scale = 1.0f;
                        img.setLayoutParams(new RelativeLayout.LayoutParams(ScreenWidth, (int) (scale * ScreenWidth)));
                        GlideApp.with(context)
                                .load(R.mipmap.pic_fail)
                                .into(img);
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                G.imgfix(context, url, img);
                            }
                        });
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        img.setClickable(false);
                        return false;
                    }
                })
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        float scale = ((float) height) / width;
                        img.setLayoutParams(new RelativeLayout.LayoutParams(ScreenWidth, (int) (scale * ScreenWidth)));
                        GlideApp.with(context)
                                .load(url)
                                .dontAnimate()
                                .skipMemoryCache(false)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(img);
                    }
                });
    }


    public static void imgfix(final Context context, final int res_id, final ImageView img) {
        final int ScreenWidth = MockUtil.getScreenWidth(context);
        GlideApp.with(context)
                .asBitmap()//强制Glide返回一个Bitmap对象
                .load(res_id)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        float scale = ((float) height) / width;
                        img.setLayoutParams(new RelativeLayout.LayoutParams(ScreenWidth, (int) (scale * ScreenWidth)));
                        GlideApp.with(context)
                                .load(res_id)
                                .dontAnimate()
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(img);
                    }
                });
    }
}
