package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.data.Config;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.wzm_sdk.tools.P;
import top.wzmyyj.zymk.contract.HomeContract;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.HomeBox;
import top.wzmyyj.zymk.base.presenter.BasePresenter;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */
public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter {

    private final MainModel mModel;
    private final FavorModel favorModel;
    private final List<FavorBean> favorList = new ArrayList<>();

    public HomePresenter(Activity activity, HomeContract.IView iv) {
        super(activity, iv);
        mModel = new MainModel();
        favorModel = new FavorModel(activity);
    }

    @Override
    public void loadData() {
        mModel.getHomeData(new BaseObserver<HomeBox>() {
            @Override
            public void onNext(@NonNull HomeBox box) {
                mView.showData(box.getBoList(), box.getItemList());
//                mView.showToast("加载成功");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void loadNetFavor() {
        favorModel.updateAll(new BaseObserver<FavorBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(@NonNull FavorBean favorBean) {
                if (favorBean.isUnRead()) {
                    favorList.add(favorBean);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                // 是否展示出来。
                boolean is = P.create(mActivity).getBoolean("isCue", true);
                if (is) mView.showFavor(favorList);
            }
        });
    }

    // 只访问数据库。
    @Override
    public void loadFavor() {
        favorModel.loadAll(new BaseObserver<List<FavorBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(@NonNull List<FavorBean> favorBeans) {
                for (FavorBean favor : favorBeans) {
                    if (favor.isUnRead()) {
                        favorList.add(favor);
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                // 是否展示出来。
                boolean is = P.create(mActivity).getBoolean("isCue", true);
                if (is) mView.showFavor(favorList);
            }
        });
    }

    @Override
    public void setAllFavorRead() {
        favorModel.setALLRead(new BaseObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean b) {
                if (b) favorList.clear();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
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
    public void goMore(String href) {
        IntentHelper.toMoreActivity(mActivity, href);
    }

    @Override
    public void goNew() {
        IntentHelper.toNewActivity(mActivity);
    }

    @Override
    public void goRank() {
        IntentHelper.toRankActivity(mActivity);
    }

    @Override
    public void goSearch() {
        IntentHelper.toSearchActivity(mActivity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Config.canReadSf = P.create(mActivity).getBoolean("canReadSf", false);
    }
}
