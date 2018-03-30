package zgc.mvpdemo.presenter.home;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import zgc.mvpdemo.app.APP;
import zgc.mvpdemo.presenter.base.BasePresenter;
import zgc.mvpdemo.ui.contract.HomeContract;
import zgc.mvpdemo.ui.contract.PhotoViewContract;
import zgc.mvpdemo.util.LogUtil;
import zgc.mvpdemo.util.image.ImageLoader;
import zgc.mvpdemo.util.image.impl.GlideApp;

/**
 * Created by Nick on 2017/12/7
 */
public class PhotoViewPresenter extends BasePresenter implements PhotoViewContract.Presenter {
    private PhotoViewContract.View view = null;

    public static String PHOTO_URL = "photo_url";

    @Inject
    public PhotoViewPresenter() {
    }


    //TODO 这里使用Glide加载图片的时候，第一次进来会跳一下，应该是图片显示策略问题，暂时先改用Picasso解决
    @Override
    public void showPhoto(Activity activity, String url) {
        LogUtil.d(url);
//        ImageLoader.with(APP.sInstance)
//                .url(url)
//                .into(view.getPhotoView());
//        GlideApp.with(activity)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .into(view.getPhotoView());
        Picasso.get().load(url).into(view.getPhotoView());
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
