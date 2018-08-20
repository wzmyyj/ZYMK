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
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.common.java.Vanessa;
import top.wzmyyj.zymk.greendao.gen.HistoryDbDao;
import top.wzmyyj.zymk.model.db.dao.HistoryDb;
import top.wzmyyj.zymk.model.db.utils.DaoManager;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class HistoryModel {
    private HistoryDbDao mDao;

    public HistoryModel(Context context) {
        mDao = DaoManager.getInstance(context).getDaoSession().getHistoryDbDao();
    }

    private List<HistoryBean> db2beanList(List<HistoryDb> DbList) {
        List<HistoryBean> list = new ArrayList<>();
        if (DbList != null && DbList.size() > 0) {
            for (HistoryDb db : DbList) {
                list.add(db2bean(db));
            }
        }
        return list;
    }

    private HistoryBean db2bean(HistoryDb db) {
        HistoryBean bean = new HistoryBean();
        BookBean book = new BookBean();
        book.setId(db.getId().intValue());
        book.setTitle(db.getTitle());
        bean.setBook(book);
        ChapterBean chapter = new ChapterBean();
        chapter.setChapter_id(db.getHistory_chapter_id());
        chapter.setChapter_name(db.getHistory_chapter_name());
        bean.setChapter(chapter);
        bean.setRead_time(db.getHistory_read_time());
        return bean;
    }

    public void loadAll(Observer<List<HistoryBean>> observer) {
        Observable.create(new ObservableOnSubscribe<List<HistoryBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<HistoryBean>> observableEmitter) throws Exception {
                try {
                    List<HistoryDb> list = mDao.loadAll();
                    List<HistoryBean> data = db2beanList(list);
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

    public void insert(final BookBean book, final ChapterBean chapter, Observer<HistoryBean> observer) {
        Observable.create(new ObservableOnSubscribe<HistoryBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<HistoryBean> observableEmitter) throws Exception {
                try {
                    // 查询原来是否已有。
                    HistoryDb dd = mDao.load((long) book.getId());
                    // 有的话，更新数据后返回。
                    if (dd != null) {
                        dd.setHistory_read_time(Vanessa.getTime());
                        dd.setHistory_chapter_id(chapter.getChapter_id());
                        dd.setHistory_chapter_name(chapter.getChapter_name());
                        mDao.update(dd);
                        HistoryBean bean = db2bean(dd);
                        observableEmitter.onNext(bean);//更新后的对象。
                    } else {
                        // 插入。
                        HistoryDb db = new HistoryDb((long) book.getId(), book.getTitle(), chapter.getChapter_id(),
                                chapter.getChapter_name(), Vanessa.getTime());
                        mDao.insert(db);
                        // 转化。
                        HistoryBean bean = db2bean(db);
                        observableEmitter.onNext(bean);
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


    public void delete(final Long[] ids, Observer<Boolean> observer) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> observableEmitter) throws Exception {
                try {
                    mDao.deleteByKeyInTx(ids);
                    observableEmitter.onNext(true);
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


    public void load(final long id, Observer<HistoryBean> observer) {
        Observable.create(new ObservableOnSubscribe<HistoryBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<HistoryBean> observableEmitter) throws Exception {
                try {
                    // 查询原来是否已有。
                    HistoryDb dd = mDao.load(id);
                    // 有的话，更新数据后返回。
                    if (dd != null) {
                        HistoryBean bean = db2bean(dd);
                        observableEmitter.onNext(bean);//更新后的对象。
                    } else {
                        observableEmitter.onNext(new HistoryBean());// 返回一个内容为空的对象。
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
