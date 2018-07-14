package top.wzmyyj.zymk.model.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.model.box.HomeBox;
import top.wzmyyj.zymk.model.box.NewBox;
import top.wzmyyj.zymk.model.box.RankBox;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class MainModel {

    public static Document mDocument;

    public void getHomeData(Observer<HomeBox> observer) {
        Observable.create(new ObservableOnSubscribe<HomeBox>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<HomeBox> observableEmitter) throws Exception {
                try {
                    if (mDocument == null) {
                        mDocument = Jsoup.connect(Urls.ZYMK_HomePage).get();
                    }
                    Document doc = mDocument;
                    HomeBox data = DocUtil.transToHome(doc);
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
                    if (mDocument == null) {
                        mDocument = Jsoup.connect(Urls.ZYMK_HomePage).get();
                    }
                    Document doc = mDocument;
                    NewBox data = DocUtil.transToNew(doc);
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
                    if (mDocument == null) {
                        mDocument = Jsoup.connect(Urls.ZYMK_HomePage).get();
                    }
                    Document doc = mDocument;
                    RankBox data = DocUtil.transToRank(doc);
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
