package zgc.mvpdemo.presenter.home;

import android.app.Activity;

import javax.inject.Inject;

import zgc.mvpdemo.presenter.base.BasePresenter;
import zgc.mvpdemo.ui.contract.HomeContract;
import zgc.mvpdemo.ui.contract.PhotoViewContract;
import zgc.mvpdemo.util.LogUtil;
import zgc.mvpdemo.util.image.ImageLoader;

/**
 * Created by Nick on 2017/12/7
 */
public class PhotoViewPresenter extends BasePresenter implements PhotoViewContract.Presenter {
    private PhotoViewContract.View view = null;

    public static String PHOTO_URL = "photo_url";

    @Inject
    public PhotoViewPresenter() {
    }


    @Override
    public void showPhoto(Activity activity,String url) {
        LogUtil.d(url);
        ImageLoader.with(activity)
                .url(url)
                .into(view.getPhotoView());
    }

    @Override
    public void takeView(PhotoViewContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
