package top.wzmyyj.zymk.model.net.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.model.net.box.SearchBox;

/**
 * Created by yyj on 2018/07/31. email: 2209011667@qq.com
 */

public interface SearchService {
    @GET(Urls.API_HotSearch)
    Observable<SearchBox> getHotSearch();

    @GET(Urls.API_SmartSearch)
    Observable<SearchBox> getSmartSearch(@Query("key") String key);
}
