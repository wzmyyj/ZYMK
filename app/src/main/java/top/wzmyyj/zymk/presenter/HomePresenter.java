package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.wzm_sdk.tools.P;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.HomeBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter {
    private MainModel mModel;
    private FavorModel favorModel;

    public HomePresenter(Activity activity, HomeContract.IView iv) {
        super(activity, iv);
        mModel = new MainModel();
        favorModel = new FavorModel(activity);
    }

    @Override
    public void loadData() {
        mModel.getHomeData(new Observer<HomeBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBox box) {
                mView.showData(box.getBoList(), box.getItemList());
//                mView.showToast("加载成功");
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


    List<FavorBean> favorList = new ArrayList<>();

    @Override
    public void loadNetFavor() {
        favorModel.updateAll(new Observer<FavorBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(FavorBean favorBean) {
                if (favorBean.isUnRead()) {
                    favorList.add(favorBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                // 是否展示出来。
                boolean is = P.create(mActivity).getBoolean("isCue", true);
                if (is) {
                    mView.showFavor(favorList);
                }
            }
        });
    }

    @Override
    public void loadFavor() {
        favorModel.loadAll(new Observer<List<FavorBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(List<FavorBean> favorBeans) {
                for (FavorBean favor : favorBeans) {
                    if (favor.isUnRead()) {
                        favorList.add(favor);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                // 是否展示出来。
                boolean is = P.create(mActivity).getBoolean("isCue", true);
                if (is) {
                    mView.showFavor(favorList);
                }
            }
        });
    }

    @Override
    public void goMore(String href) {
        I.toMoreActivity(mActivity, href);
    }

    @Override
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

    @Override
    public void goNew() {
        I.toNewActivity(mActivity);
    }

    @Override
    public void goRank() {
        I.toRankActivity(mActivity);
    }

    @Override
    public void goSearch() {
        I.toSearchActivity(mActivity);
    }

}
