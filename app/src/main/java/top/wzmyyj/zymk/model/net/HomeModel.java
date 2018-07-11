package top.wzmyyj.zymk.model.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.charset.Charset;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.HomeBean;
import top.wzmyyj.zymk.app.bean.ItemBean;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public class HomeModel {


    public void getHomeData(Observer<HomeBean> observer) {
        Observable.create(new ObservableOnSubscribe<HomeBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<HomeBean> observableEmitter) throws Exception {
                try {
                    Document doc = Jsoup.connect(Urls.ZYMK_HomePage).get();
                    doc.charset(Charset.forName("utf-8"));
                    List<ItemBean> items = DocUtil.transToItem(doc);
                    List<BoBean> bos = DocUtil.transToBo(doc);
                    HomeBean home = new HomeBean(bos, items);
                    observableEmitter.onNext(home);
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
//                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);


    }


}
