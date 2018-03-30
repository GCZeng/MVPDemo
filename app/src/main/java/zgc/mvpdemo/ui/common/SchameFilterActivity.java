package zgc.mvpdemo.ui.common;

import android.net.Uri;

import com.alibaba.android.arouter.launcher.ARouter;

import zgc.mvpdemo.ui.activity.base.BaseActivity;

/**
 * Author: zgc
 * Time: 2018/3/29 下午3:34
 * Description: SchameFilterActivity
 **/
public class SchameFilterActivity extends BaseActivity {
    @Override
    protected int provideContentViewId() {
        return 0;
    }

    @Override
    protected void initView() {
        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }

    @Override
    protected void initData() {

    }
}
