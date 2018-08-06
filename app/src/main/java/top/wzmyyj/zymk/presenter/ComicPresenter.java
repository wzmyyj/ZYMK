package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.wzmyyj.zymk.model.net.ComicModel;
import top.wzmyyj.zymk.model.net.box.ComicBox;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IComicView;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class ComicPresenter extends BasePresenter<IComicView> {

    private ComicModel mModel;

    public ComicPresenter(Activity activity, IComicView iv) {
        super(activity, iv);
        mModel = new ComicModel();
    }

    public int getChapter_id() {
        return mActivity.getIntent().getIntExtra("chapter_id", 0);
    }


    public void loadData() {
        int id = mActivity.getIntent().getIntExtra("comic_id", 0);
        mModel.getComicInfo(id, new Observer<ComicBox>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ComicBox box) {
                int status = box.getStatus();
                if (status == 0) {
                    mView.update(0, box.getBook(), box.getChapterList(), box.getBookList());
                } else {
                    mView.update(-1, box.getMsg());
                    mView.showToast(box.getMsg());
                }
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
}
