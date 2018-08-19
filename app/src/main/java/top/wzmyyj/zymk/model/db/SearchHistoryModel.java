package top.wzmyyj.zymk.model.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.greendao.gen.SearchHistoryDbDao;
import top.wzmyyj.zymk.model.db.dao.SearchHistoryDb;
import top.wzmyyj.zymk.model.db.utils.DaoManager;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public class SearchHistoryModel {
    private SearchHistoryDbDao mDao;

    public SearchHistoryModel(Context context) {
        mDao = DaoManager.getInstance(context).getDaoSession().getSearchHistoryDbDao();
    }

    private List<SearchHistoryBean> db2beanList(List<SearchHistoryDb> DbList) {
        List<SearchHistoryBean> list = new ArrayList<>();
        if (DbList != null && DbList.size() > 0) {
            for (SearchHistoryDb db : DbList) {
                list.add(db2bean(db));
            }
        }
        return list;
    }

    private SearchHistoryBean db2bean(SearchHistoryDb db) {
        SearchHistoryBean bean = new SearchHistoryBean();
        bean.setId(db.getId());
        bean.setWord(db.getSearch_word());
        bean.setTime(db.getSearch_time());
        return bean;
    }

    public void loadAll(Observer<List<SearchHistoryBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<SearchHistoryBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<SearchHistoryBean>> observableEmitter) throws Exception {
                try {
                    List<SearchHistoryDb> list = mDao.loadAll();
                    List<SearchHistoryBean> data = db2beanList(list);
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


    public void insert(final String word, Observer<SearchHistoryBean> observer) {
        Observable.create(new ObservableOnSubscribe<SearchHistoryBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<SearchHistoryBean> observableEmitter) throws Exception {
                try {
                    // 查询原来是否已有。
                    SearchHistoryDb dd = mDao.queryBuilder().where(SearchHistoryDbDao.Properties.Search_word.eq(word)).unique();
                    // 有的话，将其删除。
                    if (dd != null) {
                        mDao.delete(dd);
                    }
                    // 插入。
                    SearchHistoryDb db = new SearchHistoryDb(null, word, Vanessa.getTime());
                    long insert = mDao.insert(db);
                    db.setId(insert);
                    // 转化。
                    SearchHistoryBean bean = db2bean(db);
                    observableEmitter.onNext(bean);
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

    public void delete(final Long id, Observer<Long> observer) {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> observableEmitter) throws Exception {
                try {
                    mDao.deleteByKey(id);
                    observableEmitter.onNext(id);
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

    public void deleteAll(Observer<Long> observer) {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> observableEmitter) throws Exception {
                try {
                    mDao.deleteAll();
                    observableEmitter.onNext(null);
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
