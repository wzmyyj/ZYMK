package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.event.FavorListChangeEvent;
import top.wzmyyj.zymk.app.event.FavorUnReadChangeEvent;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.db.HistoryModel;
import top.wzmyyj.zymk.model.net.DetailsModel;
import top.wzmyyj.zymk.model.net.box.DetailsBox;

/**
 * Created by yyj on 2018/07/14. email: 2209011667@qq.com
 */
public class DetailsPresenter extends BasePresenter<DetailsContract.IView> implements DetailsContract.IPresenter {

    private final DetailsModel mModel;
    private final FavorModel favorModel;
    private final HistoryModel historyModel;

    public DetailsPresenter(Activity activity, DetailsContract.IView iv) {
        super(activity, iv);
        mModel = new DetailsModel();
        favorModel = new FavorModel(activity);
        historyModel = new HistoryModel(activity);
    }

    @Override
    public void loadData() {
        String url = mActivity.getIntent().getStringExtra("href");
        mModel.getMoreData(url, new BaseObserver<DetailsBox>() {
            @Override
            public void onNext(@NonNull DetailsBox box) {
                mView.setBook(box.getBook());
                mView.setXi(box.getXi());
                mView.setMu(box.getMu());
                mView.setZi(box.getZi());
                mView.setBookList(box.getXgBookList());
//                mView.showToast("加载成功");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void goComic(int comic_id, long chapter_id) {
        IntentHelper.toComicActivity(mActivity, comic_id, chapter_id);
    }

    @Override
    public void goDetails(String href) {
        if (href == null) {
            mView.showToast("空值");
            return;
        }
        if (href.contains(Urls.ZYMK_Base)) {
            IntentHelper.toDetailsActivity(mActivity, href);
        } else {
            IntentHelper.toBrowser(mActivity, href);
        }
    }

    @Override
    public void isFavor(long id) {
        favorModel.isFavor(id, new BaseObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean is) {
                mView.setIsFavor(is);
                EventBus.getDefault().post(new FavorUnReadChangeEvent(true));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void addFavor(BookBean book) {
        favorModel.insert(book, new BaseObserver<FavorBean>() {
            @Override
            public void onNext(@NonNull FavorBean favorBean) {
                if (favorBean.getBook() != null) {
                    mView.setIsFavor(true);
                    mView.showToast("收藏成功！");
                    EventBus.getDefault().post(new FavorListChangeEvent(true));
                } else {
                    mView.setIsFavor(true);
                    mView.showToast("已经收藏！");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void getHistoryRead(long id) {
        historyModel.load(id, new BaseObserver<HistoryBean>() {
            @Override
            public void onNext(@NonNull HistoryBean historyBean) {
                mView.setHistory(historyBean.getChapter());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }
}
