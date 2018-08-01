package top.wzmyyj.zymk.model.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import top.wzmyyj.zymk.model.net.box.SearchBox;
import top.wzmyyj.zymk.model.net.service.HotSearchService;
import top.wzmyyj.zymk.model.net.service.SmartSearchService;
import top.wzmyyj.zymk.model.net.utils.ReOk;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchModel {

    public void getHotSearch(Observer<SearchBox> observer) {

        Gson gson = new GsonBuilder().registerTypeAdapter(SearchBox.class, new SearchBox.Deserializer()).create();
        Retrofit retrofit = ReOk.bind(Urls.ZYMK_BaseApi, gson);
        HotSearchService service = retrofit.create(HotSearchService.class);
        Observable<SearchBox> observable = service.getHotSearch();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    public void getSmartSearch(final String key, Observer<SearchBox> observer) {
        Gson gson = new GsonBuilder().registerTypeAdapter(SearchBox.class, new SearchBox.Deserializer2()).create();
        Retrofit retrofit = ReOk.bind(Urls.ZYMK_BaseApi, gson);
        SmartSearchService service = retrofit.create(SmartSearchService.class);
        Observable<SearchBox> observable = service.getSmartSearch(key);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
