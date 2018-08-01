package top.wzmyyj.zymk.model.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.model.net.box.TyBox;
import top.wzmyyj.zymk.model.net.utils.DocUtil;

/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */

public class TyModel {

    public static Map<String, Element> DMap = new HashMap<>();

    public void getTyData(final String url, Observer<TyBox> observer) {
        Observable.create(new ObservableOnSubscribe<TyBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<TyBox> observableEmitter) throws Exception {
                try {
                    Element element = DMap.get(url);
                    if (element == null) {
                        element = Jsoup.connect(url).get().body();
                        DMap.put(url, element);
                    }
                    TyBox data = DocUtil.transToTy(element);
                    observableEmitter.onNext(data);
                } catch (Exception e) {
                    observableEmitter.onError(e);
                    e.printStackTrace();
                } finally {
                    observableEmitter.onComplete();
                }
            }

        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }
}
