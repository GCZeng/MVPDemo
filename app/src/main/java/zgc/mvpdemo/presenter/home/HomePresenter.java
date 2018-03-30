package zgc.mvpdemo.presenter.home;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zgc.mvpdemo.R;
import zgc.mvpdemo.app.APP;
import zgc.mvpdemo.model.GankDataModel;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.presenter.base.BasePresenter;
import zgc.mvpdemo.service.ApiService;
import zgc.mvpdemo.ui.activity.PhotoViewActivity;
import zgc.mvpdemo.ui.activity.PictureViewActivity;
import zgc.mvpdemo.ui.adapter.HomeAdapter2;
import zgc.mvpdemo.ui.contract.HomeContract;
import zgc.mvpdemo.util.image.ImageLoader;
import zgc.mvpdemo.util.image.impl.GlideApp;

/**
 * Created by Nick on 2017/1/7
 */
public class HomePresenter extends BasePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    @Inject
    ApiService apiService;

    private int mPage = 1;

    private List<GankData> mHomeList = null;
    //    private HomeAdapter mHomeAdapter = null;
    private HomeAdapter2 mHomeAdapter = null;

    private Activity mActivity = null;

    @Inject
    public HomePresenter() {

        mHomeList = new ArrayList<>();
//        mHomeAdapter = new HomeAdapter(mHomeList, context);
        mHomeAdapter = new HomeAdapter2(mHomeList);


    }


    public void loadGankData(boolean clean) {
        if (view == null) {
            return;
        }
        view.setAdapter(mHomeAdapter);
        Flowable.zip(apiService.getPicList(APP.pagesize, mPage),
                apiService.getVideoList(APP.pagesize, mPage),
                this::createHomeData)
                .map(gankDataModel -> gankDataModel.getResults())
                .flatMap(Flowable::fromIterable)
                .toSortedList((o1, o2) -> o2.getPublishedAt().compareTo(o1.getPublishedAt()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gankData -> {
                    if (clean) mHomeList.clear();
                    mHomeList.addAll(gankData);
                    mHomeAdapter.notifyDataSetChanged();
                    view.refreshComplete();
                    mHomeAdapter.loadMoreComplete();
                }, Throwable::printStackTrace);
    }

    public void refresh() {
        mPage = 1;
        loadGankData(true);
    }

    public void loadMore() {
        mPage++;
        loadGankData(false);
    }

    private GankDataModel createHomeData(GankDataModel picDataModel, GankDataModel videoDataModel) {
        for (int i = 0; i < picDataModel.getResults().size(); i++) {
            GankData picData = picDataModel.getResults().get(i);
            GankData videoData = videoDataModel.getResults().get(i);

            picData.setDesc(picData.getDesc() + " " + videoData.getDesc());
        }
        return picDataModel;
    }


    @Override
    public void initData() {
        //加载更多
        mHomeAdapter.setOnLoadMoreListener(() -> {
            loadMore();
        }, view.getRefreshView());

        mHomeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_pic:
//                    ImageLoader.with(APP.sInstance)
//                            .url(mHomeList.get(position).getUrl())
////                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                            .listener(new RequestListener<Drawable>() {
//                                @Override
//                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                    startPhotoActivity(view, position);
//                                    return false;
//                                }
//                            })
//                            .preload();

                    Picasso.get().load(mHomeList.get(position).getUrl()).fetch(new Callback() {

                        @Override
                        public void onSuccess() {
                            startPhotoActivity(view, position);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }

                    });
                    break;
            }
        });
    }

    private void startPhotoActivity(View view, int position) {
        ActivityOptionsCompat compatOptions = ActivityOptionsCompat.
                makeSceneTransitionAnimation(mActivity, view.findViewById(R.id.iv_pic), mActivity.getString(R.string.photo_transiton_tag));
        ARouter.getInstance()
                .build("/util/PhotoViewActivity")
//                .build("/util/PictureViewActivity")
                .withString(PhotoViewPresenter.PHOTO_URL, mHomeList.get(position).getUrl())
                .withOptionsCompat(compatOptions)
                .navigation(mActivity);
    }

    @Override
    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void takeView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
