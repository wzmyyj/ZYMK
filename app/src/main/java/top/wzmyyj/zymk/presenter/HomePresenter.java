package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.app.tools.P;
import top.wzmyyj.zymk.model.db.FavorModel;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.HomeBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_1View;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class HomePresenter extends BasePresenter<IF_1View> {
    private MainModel mModel;
    private FavorModel favorModel;

    public HomePresenter(Activity activity, IF_1View iv) {
        super(activity, iv);
        mModel = new MainModel();
        favorModel = new FavorModel(activity);
    }


    public void addEmptyData(List<ItemBean> data) {
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
        data.add(new ItemBean());
    }

    public void loadData() {
        mModel.getHomeData(new Observer<HomeBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBox box) {
                mView.update(0, box.getBoList(), box.getItemList());
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

    public void goMore(String href) {
        I.toMoreActivity(mActivity, href);
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

    List<FavorBean> favorList = new ArrayList<>();

    public void updateLoadFavor() {
        favorModel.updateAll(new Observer<FavorBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                favorList.clear();
            }

            @Override
            public void onNext(FavorBean favorBean) {
                // 是否展示出来。
                boolean is = P.create(mActivity).getBoolean("isCue", true);
                if (favorBean.isUnRead()&&is) {
                    favorList.add(favorBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.loadFavor(favorList);
            }
        });
    }


    public void goNew() {
        I.toNewActivity(mActivity);
    }

    public void goRank() {
        I.toRankActivity(mActivity);
    }

    public void goSearch() {
        I.toSearchActivity(mActivity);
    }

}
