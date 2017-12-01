package zgc.mvpdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zgc.mvpdemo.di.module.home.HomeModule;
import zgc.mvpdemo.ui.activity.HomeActivity;

/**
 * Created by Nick on 2017/12/1
 */
@Module
public abstract class BuildersModule {

//    @Binds
//    @IntoMap
//    @ActivityKey(HomeActivity.class)
//    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivityInjectorFactory(HomeComponent.Builder builder);

    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract HomeActivity contributeHomeActivityInjector();
}
