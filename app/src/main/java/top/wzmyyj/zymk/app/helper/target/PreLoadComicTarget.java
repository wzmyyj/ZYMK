package top.wzmyyj.zymk.app.helper.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.app.bean.ComicBean;

/**
 * Created by yyj on 2020/07/10. email: 2209011667@qq.com
 */
public class PreLoadComicTarget extends CustomTarget<Bitmap> {
    private final ComicBean comic;

    public PreLoadComicTarget(ComicBean comic) {
        this.comic = comic;
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        super.onLoadStarted(placeholder);
        comic.setPreLoading(true);
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        comic.setImgWidth(resource.getWidth());
        comic.setImgHeight(resource.getHeight());
        comic.setPreLoading(false);
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        comic.setPreLoading(false);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        comic.setPreLoading(false);
    }
}