package zgc.mvpdemo.presenter.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Nick on 2017/12/7
 */
public class BasePresenter {
    protected Activity mActivity;

    public BasePresenter(Activity context) {
        this.mActivity = context;
    }

    protected void goAct(Class clazz) {
        goAct(clazz, null);
    }

    protected void goAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mActivity.startActivity(intent);
    }
}
