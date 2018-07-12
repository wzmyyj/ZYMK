package top.wzmyyj.zymk.app.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.view.activity.MainActivity;
import top.wzmyyj.zymk.view.activity.MoreActivity;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class I {
    public static void toMainActivity(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void toQQChat(Activity context, String number) {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + number;//uin是发送过去的qq号码
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static void toBrowser(Activity context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri u = Uri.parse(url);
        intent.setData(u);
        context.startActivity(intent);
    }

    public static void toMoreActivity(Activity context, String href) {
        Intent intent = new Intent(context, MoreActivity.class);
        intent.putExtra("href", href);
        context.startActivity(intent);
    }

    public static void toDetailsActivity(Activity context, String href) {
//        Intent intent = new Intent(context, MoreActivity.class);
//        intent.putExtra("href", href);
//        context.startActivity(intent);
        T.s(href);
    }
}
