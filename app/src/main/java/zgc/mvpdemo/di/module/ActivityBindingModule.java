package zgc.mvpdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zgc.mvpdemo.di.module.home.HomeModule;
import zgc.mvpdemo.di.module.home.PhotoViewModule;
import zgc.mvpdemo.di.scope.ActivityScoped;
import zgc.mvpdemo.ui.activity.HomeActivity;
import zgc.mvpdemo.ui.activity.PhotoViewActivity;

/**
 * Created by Nick on 2017/12/2
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotoViewModule.class)
    abstract PhotoViewActivity photoViewActivity();
}
