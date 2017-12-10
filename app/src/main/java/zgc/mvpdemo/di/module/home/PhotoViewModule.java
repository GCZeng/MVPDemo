package zgc.mvpdemo.di.module.home;


import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import zgc.mvpdemo.di.scope.ActivityScoped;
import zgc.mvpdemo.ui.activity.PhotoViewActivity;
import zgc.mvpdemo.ui.contract.PhotoViewContract;

/**
 * Created by Nick on 2017/1/7
 */
@Module
public abstract class PhotoViewModule {


    @ActivityScoped
    @Binds
    public abstract PhotoViewContract.View providePhotoViewContractView(PhotoViewActivity photoViewActivity);

    @Binds
    public abstract Activity provideHomeActivity(PhotoViewActivity photoViewActivity);
}
