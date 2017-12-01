package zgc.mvpdemo.di.component.home;

import dagger.android.AndroidInjector;
import zgc.mvpdemo.ui.activity.HomeActivity;

/**
 * Created by Nick on 2017/12/2
 */
//@Subcomponent(modules = HomeModule.class)
public interface HomeComponent extends AndroidInjector<HomeActivity> {

//    @Subcomponent.Builder
//    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {
//    }
}
