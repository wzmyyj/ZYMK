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
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.greendao.gen.FavorDbDao;
import top.wzmyyj.zymk.model.db.dao.FavorDb;
import top.wzmyyj.zymk.model.db.utils.DaoManager;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class FavorModel {
    private DaoManager manager;

    public FavorModel(Context context) {
        manager = DaoManager.getInstance(context);
    }

    private List<FavorBean> db2beanList(List<FavorDb> DbList) {
        List<FavorBean> list = new ArrayList<>();
        if (DbList != null && DbList.size() > 0) {
            for (FavorDb db : DbList) {
                list.add(db2bean(db));
            }
        }
        return list;
    }

    private FavorBean db2bean(FavorDb db) {
        FavorBean bean = new FavorBean();
        BookBean book = new BookBean();
        book.setId(db.getId().intValue());
        book.setTitle(db.getTitle());
        book.setChapter(db.getChapter_name());
        book.setChapter_id(db.getChapter_id());
        book.setUpdate_time(db.getUpdate_time());
        bean.setBook(book);
        bean.setUnRead(db.getIsUnRead());
        return bean;
    }

    public void loadAll(Observer<List<FavorBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<FavorBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<FavorBean>> observableEmitter) throws Exception {
                try {
                    FavorDbDao favorDbDao = manager.getDaoSession().getFavorDbDao();
                    List<FavorDb> list = favorDbDao.loadAll();
                    List<FavorBean> data = db2beanList(list);
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


    public void insert(final BookBean book, Observer<FavorBean> observer) {
        Observable.create(new ObservableOnSubscribe<FavorBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<FavorBean> observableEmitter) throws Exception {
                try {
                    FavorDbDao favorDbDao = manager.getDaoSession().getFavorDbDao();
                    // 查询原来是否已有。
                    FavorDb dd = favorDbDao.queryBuilder().where(FavorDbDao.Properties.Id.eq((long) book.getId())).unique();
                    // 有的话，直接返回。
                    if (dd != null) {
                        observableEmitter.onNext(null);
                        return;
                    }
                    // 插入。
                    FavorDb db = new FavorDb((long) book.getId(), book.getTitle(), book.getUpdate_time(),
                            book.getChapter(), book.getChapter_id(), false);
                    favorDbDao.insert(db);
                    // 转化。
                    FavorBean bean = db2bean(db);
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
                    FavorDbDao favorDbDao = manager.getDaoSession().getFavorDbDao();
                    favorDbDao.deleteByKey(id);
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

    public void isFavor(final Long id, Observer<Boolean> observer) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> observableEmitter) throws Exception {
                try {
                    FavorDbDao favorDbDao = manager.getDaoSession().getFavorDbDao();
                    // 查询原来是否已有。
                    FavorDb dd = favorDbDao.queryBuilder().where(FavorDbDao.Properties.Id.eq(id)).unique();
                    // 有的话，返回true，否则false。
                    if (dd != null) {
                        observableEmitter.onNext(true);
                    } else {
                        observableEmitter.onNext(false);
                    }
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

