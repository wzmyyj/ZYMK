package top.wzmyyj.zymk.model.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.model.net.box.ComicBox;
import top.wzmyyj.zymk.model.net.service.ComicService;
import top.wzmyyj.zymk.model.net.utils.ReOk;

/**
 * Created by yyj on 2018/08/02. email: 2209011667@qq.com
 */

public class ComicModel {

    public void getComic(int comic_id, Observer<ComicBox> observer) {
        Gson gson = new GsonBuilder().registerTypeAdapter(ComicBox.class, new ComicBox.Deserializer2()).create();
        Retrofit retrofit = ReOk.bind(Urls.ZYMK_BaseApi, gson);
        ComicService service = retrofit.create(ComicService.class);
        Observable<ComicBox> observable = service.getComic(comic_id);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getComicInfo(int comic_id, Observer<ComicBox> observer) {
        Gson gson = new GsonBuilder().registerTypeAdapter(ComicBox.class, new ComicBox.Deserializer()).create();
        Retrofit retrofit = ReOk.bind(Urls.ZYMK_BaseApi, gson);
        ComicService service = retrofit.create(ComicService.class);
        Observable<ComicBox> observable = service.getComicInfo(comic_id);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
