package top.wzmyyj.zymk.model.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.model.net.box.HomeBox;
import top.wzmyyj.zymk.model.net.box.NewBox;
import top.wzmyyj.zymk.model.net.box.RankBox;
import top.wzmyyj.zymk.model.net.box.TypeBox;
import top.wzmyyj.zymk.model.net.utils.DocUtil;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class MainModel {

    public static Element mElement;

    public void getHomeData(Observer<HomeBox> observer) {
        Observable.create(new ObservableOnSubscribe<HomeBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<HomeBox> observableEmitter) throws Exception {
                try {
                    if (mElement == null) {
                        mElement = Jsoup.connect(Urls.ZYMK_Base).get().body();
                    }
                    HomeBox data = DocUtil.transToHome(mElement);
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


    public void getNewData(Observer<NewBox> observer) {
        Observable.create(new ObservableOnSubscribe<NewBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<NewBox> observableEmitter) throws Exception {
                try {
                    if (mElement == null) {
                        mElement = Jsoup.connect(Urls.ZYMK_Base).get().body();
                    }
                    NewBox data = DocUtil.transToNew(mElement);
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


    public void getRankData(Observer<RankBox> observer) {
        Observable.create(new ObservableOnSubscribe<RankBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<RankBox> observableEmitter) throws Exception {
                try {
                    if (mElement == null) {
                        mElement = Jsoup.connect(Urls.ZYMK_Base).get().body();
                    }
                    RankBox data = DocUtil.transToRank(mElement);
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


    public void getTypeData(Observer<TypeBox> observer) {
        Observable.create(new ObservableOnSubscribe<TypeBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<TypeBox> observableEmitter) throws Exception {
                try {
                    if (mElement == null) {
                        mElement = Jsoup.connect(Urls.ZYMK_Base).get().body();
                    }
                    TypeBox data = DocUtil.transToType(mElement);
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
