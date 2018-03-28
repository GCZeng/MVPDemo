package zgc.mvpdemo.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjection;

/**
 * Author: zgc
 * Time: 2018/3/28 下午10:42
 * Description: BaseDiActivity
 **/

public abstract class BaseDiActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
