package top.wzmyyj.zymk.app.helper.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import top.wzmyyj.wzm_sdk.utils.MockUtil;

/**
 * Created by yyj on 2020/07/10. email: 2209011667@qq.com
 */
public class FixResTarget extends CustomTarget<Bitmap> {
    private final ImageView img;
    private final int screenWidth;

    public FixResTarget(ImageView img) {
        this.img = img;
        screenWidth = MockUtil.getScreenWidth(img.getContext());
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        int width = resource.getWidth();
        int height = resource.getHeight();
        float scale = ((float) height) / width;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = screenWidth;
        params.height = Math.round(scale * screenWidth);
        img.setLayoutParams(params);
        img.setImageBitmap(resource);
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
    }
}