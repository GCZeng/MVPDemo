package zgc.mvpdemo.ui.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * Created by Nick on 2017/12/1
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int provideContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(provideContentViewId());
        initView();
        AndroidInjection.inject(this);
        initData();
    }

}
