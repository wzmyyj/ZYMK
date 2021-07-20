package top.wzmyyj.zymk.app.helper.target;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import top.wzmyyj.zymk.app.bean.ComicBean;

/**
 * Created by yyj on 2020/07/10. email: 2209011667@qq.com
 */
public class PreLoadComicTarget extends CustomTarget<File> {
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
    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(resource.getName(), options);
        comic.setImgWidth(options.outWidth);
        comic.setImgHeight(options.outHeight);
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