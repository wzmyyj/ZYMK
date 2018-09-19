package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.TypeBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class TypePresenter extends BasePresenter<TypeContract.IView> implements TypeContract.IPresenter {
    private MainModel mModel;

    public TypePresenter(Activity activity, TypeContract.IView iv) {
        super(activity, iv);
        mModel = new MainModel();
    }

    @Override
    public void loadData() {
        mModel.getTypeData(new Observer<TypeBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TypeBox box) {
                mView.showData(box.getTypeList1(), box.getTypeList2(), box.getTypeList3(), box.getTypeList4());
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

    @Override
    public void goResult(String href) {
        I.toResultActivity(mActivity, href);
    }

    @Override
    public void goSearch() {
        I.toSearchActivity(mActivity);
    }
}
