package top.wzmyyj.zymk.app.helper.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.wzm_sdk.utils.MockUtil;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;

/**
 * Created by yyj on 2020/07/10. email: 2209011667@qq.com
 */
public class LoadComicTarget extends CustomTarget<Bitmap> {
    private final ComicBean comic;
    private final ImageView img;
    private final String url;
    private final int screenWidth;

    public LoadComicTarget(ComicBean comic, ImageView img, String url) {
        this.comic = comic;
        this.img = img;
        this.url = url;
        screenWidth = MockUtil.getScreenWidth(img.getContext());
    }

    public void load() {
        GlideLoaderHelper.load(img, url, this);
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        super.onLoadStarted(placeholder);
        comic.setLoading(true);
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        L.d("Load: have preload= " + (comic.getImgHeight() > 0));// 有没有预加载过啊啊啊啊啊...
        comic.setImgWidth(resource.getWidth());
        comic.setImgHeight(resource.getHeight());
        comic.setLoading(false);
        int width = resource.getWidth();
        int height = resource.getHeight();
        float scale = ((float) height) / width;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = screenWidth;
        params.height = Math.round(scale * screenWidth);
        img.setLayoutParams(params);
        img.setImageBitmap(resource);
        img.setClickable(false);
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        comic.setLoading(false);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        comic.setLoading(false);
        float scale = 1.0f;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = screenWidth;
        params.height = Math.round(scale * screenWidth);
        img.requestLayout();
        img.setImageResource(R.mipmap.pic_fail);
        img.setOnClickListener(v -> {
            load();
            img.setOnClickListener(null);
            img.setClickable(false);
        });
    }
}