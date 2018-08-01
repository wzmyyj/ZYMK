package top.wzmyyj.zymk.model.net.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import top.wzmyyj.zymk.model.box.SearchBox;
import top.wzmyyj.zymk.model.net.Urls;

/**
 * Created by yyj on 2018/07/31. email: 2209011667@qq.com
 */

public interface HotSearchService {
    @GET(Urls.API_HotSearch)
    Observable<SearchBox> getHotSearch();
}
