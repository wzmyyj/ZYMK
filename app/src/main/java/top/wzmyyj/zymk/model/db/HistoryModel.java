package top.wzmyyj.zymk.model.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.wzmyyj.wzm_sdk.utils.TimeUtil;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.greendao.gen.HistoryDbDao;
import top.wzmyyj.zymk.model.db.dao.HistoryDb;
import top.wzmyyj.zymk.model.db.utils.DaoManager;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
public class HistoryModel {

    private final HistoryDbDao mDao;

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
        chapter.setChapterId(db.getHistory_chapter_id());
        chapter.setChapterName(db.getHistory_chapter_name());
        bean.setChapter(chapter);
        bean.setReadTime(db.getHistory_read_time());
        return bean;
    }

    public void loadAll(Observer<List<HistoryBean>> observer) {
        Observable.create((ObservableOnSubscribe<List<HistoryBean>>) observableEmitter -> {
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
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void insert(final BookBean book, final ChapterBean chapter, Observer<HistoryBean> observer) {
        Observable.create((ObservableOnSubscribe<HistoryBean>) observableEmitter -> {
            try {
                // 查询原来是否已有。
                HistoryDb dd = mDao.load((long) book.getId());
                // 有的话，更新数据后返回。
                if (dd != null) {
                    dd.setHistory_read_time(TimeUtil.getTime());
                    dd.setHistory_chapter_id(chapter.getChapterId());
                    dd.setHistory_chapter_name(chapter.getChapterName());
                    mDao.update(dd);
                    HistoryBean bean = db2bean(dd);
                    observableEmitter.onNext(bean);//更新后的对象。
                } else {
                    // 插入。
                    HistoryDb db = new HistoryDb((long) book.getId(), book.getTitle(), chapter.getChapterId(),
                            chapter.getChapterName(), TimeUtil.getTime());
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
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void delete(final Long[] ids, Observer<Boolean> observer) {
        Observable.create((ObservableOnSubscribe<Boolean>) observableEmitter -> {
            try {
                mDao.deleteByKeyInTx(ids);
                observableEmitter.onNext(true);
            } catch (Exception e) {
                observableEmitter.onError(e);
                e.printStackTrace();
            } finally {
                observableEmitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void load(final long id, Observer<HistoryBean> observer) {
        Observable.create((ObservableOnSubscribe<HistoryBean>) observableEmitter -> {
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
        })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
