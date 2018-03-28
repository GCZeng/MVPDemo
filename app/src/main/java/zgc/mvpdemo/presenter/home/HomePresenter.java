package zgc.mvpdemo.presenter.home;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;

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
import zgc.mvpdemo.ui.adapter.HomeAdapter2;
import zgc.mvpdemo.ui.contract.HomeContract;

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

    @Inject
    public HomePresenter() {

        mHomeList = new ArrayList<>();
//        mHomeAdapter = new HomeAdapter(mHomeList, context);
        mHomeAdapter = new HomeAdapter2(mHomeList);


    }


    public void loadGankData(boolean clean) {
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
//                    Bundle bundle = new Bundle();
//                    bundle.putString(PhotoViewPresenter.PHOTO_URL, mHomeList.get(position).getUrl());
//                    goAct(PhotoViewActivity.class, bundle);

                    ARouter.getInstance().build("/util/PhotoViewActivity")
                            .withString(PhotoViewPresenter.PHOTO_URL, mHomeList.get(position).getUrl())
                            .navigation();
                    break;
            }
        });
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
