package zgc.mvpdemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import zgc.mvpdemo.app.APP;
import zgc.mvpdemo.di.module.AppModule;
import zgc.mvpdemo.di.module.BuildersModule;
import zgc.mvpdemo.di.module.NetworkModule;

/**
 * Created by Nick on 2017/12/1
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        BuildersModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<APP>{

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<APP> {}

}
