package zgc.mvpdemo.ui.activity;


import android.support.v7.app.ActionBar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.chrisbanes.photoview.PhotoView;

import javax.inject.Inject;

import butterknife.BindView;
import zgc.mvpdemo.R;
import zgc.mvpdemo.presenter.home.PhotoViewPresenter;
import zgc.mvpdemo.ui.activity.base.BaseDiActivity;
import zgc.mvpdemo.ui.contract.PhotoViewContract;

/**
 * Created by Nick on 2017/12/7
 */
@Route(path = "/util/PhotoViewActivity")
public class PhotoViewActivity extends BaseDiActivity implements PhotoViewContract.View {
    @BindView(R.id.pv_pic)
    PhotoView pv_pic;

    @Inject
    PhotoViewPresenter mPhotoViewPresenter;

    @Autowired
    String photo_url;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initView() {

        ARouter.getInstance().inject(this);

        showBackPress(() -> finish());

        pv_pic.setOnPhotoTapListener((view, x, y) -> {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar.isShowing()) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.photo_view);
    }

    @Override
    public PhotoView getPhotoView() {
        return pv_pic;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPhotoViewPresenter.takeView(this);
        if (isFirstLoadData) {
            isFirstLoadData = false;
            mPhotoViewPresenter.showPhoto(this, photo_url);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPhotoViewPresenter.dropView();
    }
}
