package zgc.mvpdemo.ui.activity;


import android.support.v7.app.ActionBar;

import com.github.chrisbanes.photoview.PhotoView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zgc.mvpdemo.R;
import zgc.mvpdemo.presenter.home.PhotoViewPresenter;
import zgc.mvpdemo.ui.activity.base.BaseActivity;
import zgc.mvpdemo.ui.contract.PhotoViewContract;

/**
 * Created by Nick on 2017/12/7
 */
public class PhotoViewActivity extends BaseActivity implements PhotoViewContract.View {
    @BindView(R.id.pv_pic) PhotoView pv_pic;

    @Inject PhotoViewPresenter mPhotoViewPresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

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
        mPhotoViewPresenter.showPhoto();
        setTitle(R.string.photo_view);
    }

    @Override
    public PhotoView getPhotoView() {
        return pv_pic;
    }
}
