package top.wzmyyj.zymk.model.net;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchModel {

    public void getHotSearch(Observer<List<BookBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<BookBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<BookBean>> observableEmitter) throws Exception {
                try {

                    List<BookBean> list = new ArrayList<>();
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1));
                    list.add(new BookBean("斗罗大陆", Urls.ZYMK_Base + 2));
                    list.add(new BookBean("绝世唐门", Urls.ZYMK_Base + 609));
                    list.add(new BookBean("风起苍岚", Urls.ZYMK_Base + 145));
                    list.add(new BookBean("凤逆天下", Urls.ZYMK_Base + 101));
                    list.add(new BookBean("元尊", Urls.ZYMK_Base + 1966));
                    list.add(new BookBean("哑舍", Urls.ZYMK_Base + 67));
                    list.add(new BookBean("穿越西元3000后", Urls.ZYMK_Base + 100));
                    list.add(new BookBean("寻找前世之旅", Urls.ZYMK_Base + 102));

                    observableEmitter.onNext(list);
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

    public void getSmartSearch(final String key, Observer<List<BookBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<BookBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<BookBean>> observableEmitter) throws Exception {
                try {
                    List<BookBean> list = new ArrayList<>();
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));
                    list.add(new BookBean("斗破苍穹", Urls.ZYMK_Base + 1, "第1话"));

                    observableEmitter.onNext(list);
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
