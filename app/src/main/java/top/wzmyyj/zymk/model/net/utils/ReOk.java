package top.wzmyyj.zymk.model.net.utils;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.wzmyyj.wzm_sdk.tools.L;

/**
 * Created by yyj on 2018/07/31. email: 2209011667@qq.com
 */
public class ReOk {

    public static Retrofit bind(String BASE_URL, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor(L::json).setLevel(HttpLoggingInterceptor.Level.BODY))// 拦截日志。
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS);//设置写入超时时间
        return builder.build();
    }
}