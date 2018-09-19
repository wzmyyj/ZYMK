package top.wzmyyj.zymk.app.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import top.wzmyyj.zymk.view.activity.ComicActivity;
import top.wzmyyj.zymk.view.activity.DetailsActivity;
import top.wzmyyj.zymk.view.activity.MainActivity;
import top.wzmyyj.zymk.view.activity.MoreActivity;
import top.wzmyyj.zymk.view.activity.NewActivity;
import top.wzmyyj.zymk.view.activity.RankActivity;
import top.wzmyyj.zymk.view.activity.ResultActivity;
import top.wzmyyj.zymk.view.activity.SearchActivity;
import top.wzmyyj.zymk.view.activity.SettingActivity;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 * Intent 页面跳转的统一封装类。
 */

public class I {
    public static void toMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void toQQChat(Activity context, String number) {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + number;//uin是发送过去的qq号码
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static void toBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri u = Uri.parse(url);
        intent.setData(u);
        context.startActivity(intent);
    }

    public static void toMoreActivity(Context context, String href) {
        Intent intent = new Intent(context, MoreActivity.class);
        intent.putExtra("href", href);
        context.startActivity(intent);
    }

    public static void toDetailsActivity(Context context, String href) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("href", href);
        context.startActivity(intent);
    }

    public static void toDetailsActivity2(Context context, String href) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("href", href);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void toNewActivity(Context context) {
        Intent intent = new Intent(context, NewActivity.class);
        context.startActivity(intent);
    }

    public static void toRankActivity(Context context) {
        Intent intent = new Intent(context, RankActivity.class);
        context.startActivity(intent);
    }

    public static void toResultActivity(Context context, String href) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("href", href);
        context.startActivity(intent);
    }

    public static void toTyActivity(Context context, String href, String key) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("href", href);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    public static void toSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void toComicActivity(Context context, int comic_id) {
        Intent intent = new Intent(context, ComicActivity.class);
        intent.putExtra("comic_id", comic_id);
        context.startActivity(intent);
    }

    public static void toComicActivity(Context context, int comic_id, long chapter_id) {
        Intent intent = new Intent(context, ComicActivity.class);
        intent.putExtra("comic_id", comic_id);
        intent.putExtra("chapter_id", chapter_id);
        context.startActivity(intent);
    }

    public static void toSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
