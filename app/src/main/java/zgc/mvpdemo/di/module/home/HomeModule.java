package zgc.mvpdemo.di.module.home;


import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import zgc.mvpdemo.di.scope.ActivityScoped;
import zgc.mvpdemo.ui.activity.HomeActivity;
import zgc.mvpdemo.ui.contract.HomeContract;

/**
 * Created by Nick on 2017/1/7
 */
@Module
public abstract class HomeModule {

//    private static HomeContract.View view;
//
//    //构造方法传递View 接口的实例化对象
//    public HomeModule(HomeContract.View view) {
//        this.view = view;
//    }
//
//    //在DI容器中提供View接口的实例化对象
//    @PerActivityScope
//    @Provides
//    public static HomeContract.View providerView() {
//        return view;
//    }

//    MVPDemo
//            Dagger2
//    RxJava2
//            Retrofit2
//    OKHttp3

    @ActivityScoped
    @Binds
    public abstract HomeContract.View provideHomeContractView(HomeActivity homeActivity);

    @Binds
    public abstract Activity provideHomeActivity(HomeActivity homeActivity);

//    @ActivityScoped
//    @Binds
//    abstract HomeContract.Presenter homePresenter(HomePresenter homePresenter);
}
