package top.wzmyyj.zymk.model.net.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.model.net.box.ComicBox;

/**
 * Created by yyj on 2018/08/02. email: 2209011667@qq.com
 */

public interface ComicService {

    @GET(Urls.API_GetComic)
    Observable<ComicBox> getComic(@Query("comic_id") int comic_id);

    @GET(Urls.API_GetComicInfo)
    Observable<ComicBox> getComicInfo(@Query("comic_id") int comic_id);

}
