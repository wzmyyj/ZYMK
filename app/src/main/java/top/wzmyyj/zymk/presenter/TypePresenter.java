package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;

import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.base.presenter.BasePresenter;
import top.wzmyyj.zymk.contract.TypeContract;
import top.wzmyyj.zymk.model.net.MainModel;
import top.wzmyyj.zymk.model.net.box.TypeBox;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */
public class TypePresenter extends BasePresenter<TypeContract.IView> implements TypeContract.IPresenter {

    private final MainModel mModel;

    public TypePresenter(Activity activity, TypeContract.IView iv) {
        super(activity, iv);
        mModel = new MainModel();
    }

    @Override
    public void loadData() {
        mModel.getTypeData(new BaseObserver<TypeBox>() {
            @Override
            public void onNext(@NonNull TypeBox box) {
                mView.showData(box.getTypeList1(), box.getTypeList2(), box.getTypeList3(), box.getTypeList4());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showToast("Error:" + e.getMessage());
            }
        });
    }

    @Override
    public void goResult(String href) {
        IntentHelper.toResultActivity(mActivity, href);
    }

    @Override
    public void goSearch() {
        IntentHelper.toSearchActivity(mActivity);
    }
}
