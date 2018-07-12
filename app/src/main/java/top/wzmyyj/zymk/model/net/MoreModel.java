package top.wzmyyj.zymk.model.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.model.box.MoreBox;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class MoreModel {


    public static Map<String, Document> DMap = new HashMap<>();

    public void getData(final String url, Observer<MoreBox> observer) {
        Observable.create(new ObservableOnSubscribe<MoreBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<MoreBox> observableEmitter) throws Exception {
                try {
                    Document mDocument = DMap.get(url);
                    if (mDocument == null) {
                        mDocument = Jsoup.connect(url).get();
                        DMap.put(url, mDocument);
                    }
                    Document doc = mDocument;
                    MoreBox data = DocUtil.transToMore(doc);
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
