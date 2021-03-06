package zgc.mvpdemo.ui.contract;

import android.app.Activity;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import zgc.mvpdemo.presenter.base.IBasePresenter;
import zgc.mvpdemo.ui.contract.base.IBaseView;

/**
 * Created by Nick on 2017/1/7
 */
public class PhotoViewContract {
    public interface View extends IBaseView<Presenter> {
        ImageView getPhotoView();
    }

    public interface Presenter extends IBasePresenter<View> {
        void showPhoto(Activity activity, String url);
    }

}
