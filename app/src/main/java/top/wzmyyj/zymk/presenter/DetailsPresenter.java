package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.db.HistoryModel;
import top.wzmyyj.zymk.model.net.DetailsModel;
import top.wzmyyj.zymk.model.net.box.DetailsBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IDetailsView;


/**
 * Created by yyj on 2018/07/14. email: 2209011667@qq.com
 */

public class DetailsPresenter extends BasePresenter<IDetailsView> {
    private DetailsModel mModel;
    private FavorModel favorModel;
    private HistoryModel historyModel;

    public DetailsPresenter(Activity activity, IDetailsView iv) {
        super(activity, iv);
        mModel = new DetailsModel();
        favorModel = new FavorModel(activity);
        historyModel = new HistoryModel(activity);
    }


    public void loadData() {
        String url = mActivity.getIntent().getStringExtra("href");
        mModel.getMoreData(url, new Observer<DetailsBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DetailsBox box) {
                mView.setBook(box.getBook());
                mView.setXi(box.getXi());
                mView.setMu(box.getMu());
                mView.setZi(box.getZi());
                mView.setBookList(box.getXgBookList());

//                mView.showToast("加载成功");
            }

            @Override
            public void onError(Throwable e) {
                mView.update(-1, e.getMessage());
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void goComic(int comic_id, long chapter_id) {
        I.toComicActivity(mActivity, comic_id, chapter_id);
    }

    public void goComic(int comic_id) {
        I.toComicActivity(mActivity, comic_id);
    }

    public void goDetails(String href) {
        if (href == null) {
            mView.showToast("空值");
            return;
        }
        if (href.contains(Urls.ZYMK_Base)) {
            I.toDetailsActivity(mActivity, href);
        } else {
            I.toBrowser(mActivity, href);
        }
    }

    public void isFavor(long id) {
        favorModel.isFavor(id, new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean is) {
                mView.setIsFavor(is);
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addFavor(BookBean book) {
        favorModel.insert(book, new Observer<FavorBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FavorBean favorBean) {
                if(favorBean != null){
                    mView.setIsFavor(true);
                    mView.showToast("收藏成功！");
                }else{
                    mView.setIsFavor(false);
                    mView.showToast("已经收藏！");
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void history(long id) {

    }

}
