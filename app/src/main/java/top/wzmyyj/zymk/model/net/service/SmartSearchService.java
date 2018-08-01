package top.wzmyyj.zymk.model.net.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.wzmyyj.zymk.model.box.SearchBox;
import top.wzmyyj.zymk.model.net.Urls;

/**
 * Created by yyj on 2018/07/31. email: 2209011667@qq.com
 */

public interface SmartSearchService {
    @GET(Urls.API_SmartSearch)
    Observable<SearchBox> getSmartSearch(@Query("key") String key);
}
