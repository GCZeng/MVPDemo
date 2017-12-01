package zgc.mvpdemo.presenter.home;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zgc.mvpdemo.app.APP;
import zgc.mvpdemo.model.GankDataModel;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.presenter.base.BasePresenter;
import zgc.mvpdemo.service.ApiService;
import zgc.mvpdemo.ui.adapter.HomeAdapter;
import zgc.mvpdemo.ui.contract.HomeContract;

/**
 * Created by Nick on 2017/1/7
 */
public class HomePresenter extends BasePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    @Inject ApiService apiService;

    private int mPage = 1;

    private List<GankData> mHomeList = null;
    private HomeAdapter mHomeAdapter = null;

    @Override
    public void loadGankData(boolean clean) {
        Observable.zip(apiService.getPicList(APP.pagesize, mPage),
                apiService.getVideoList(APP.pagesize, mPage),
                this::createHomeData)
                .map(gankDataModel -> gankDataModel.getResults())
//                .flatMap(Observable::from)
//                .toSortedList((gankData, gankData2) -> gankData2.getPublishedAt().compareTo(gankData.getPublishedAt()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GankData>>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GankData> gankDatas) {
                        if (clean) mHomeList.clear();
                        mHomeList.addAll(gankDatas);
                        mHomeAdapter.notifyDataSetChanged();
                        view.refreshComplete();
                    }
                });
    }

    @Inject
    public HomePresenter(HomeContract.View view, Context context) {
        this.view = view;

        mHomeList = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeList,context);
        view.setAdapter(mHomeAdapter);
    }

    private GankDataModel createHomeData(GankDataModel picDataModel, GankDataModel videoDataModel) {
        for (int i = 0; i < picDataModel.getResults().size(); i++) {
            GankData picData = picDataModel.getResults().get(i);
            GankData videoData = videoDataModel.getResults().get(i);

            picData.setDesc(picData.getDesc() + " " + videoData.getDesc());
        }
        return picDataModel;
    }
}
