package zgc.mvpdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zgc.mvpdemo.di.module.home.HomeModule;
import zgc.mvpdemo.di.scope.ActivityScoped;
import zgc.mvpdemo.ui.activity.HomeActivity;

/**
 * Created by Nick on 2017/12/2
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();
}
