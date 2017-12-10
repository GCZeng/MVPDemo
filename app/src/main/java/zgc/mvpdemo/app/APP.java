package zgc.mvpdemo.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import zgc.mvpdemo.BuildConfig;
import zgc.mvpdemo.R;
import zgc.mvpdemo.di.component.DaggerAppComponent;
import zgc.mvpdemo.util.DisplayUtiil;
import zgc.mvpdemo.util.LogUtil;
import zgc.mvpdemo.util.image.ImageLoader;

/**
 * Created by Nick on 2017/12/1
 */
public class APP extends DaggerApplication {
    public static APP sInstance;

    public final static int pagesize = 20;

    @Override
    public void onCreate() {
        super.onCreate();

        APP.sInstance = this;

        LogUtil.init(BuildConfig.LOG_DEBUG);
        DisplayUtiil.init();

        ImageLoader.init(R.mipmap.pic_placeholder);

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

}
