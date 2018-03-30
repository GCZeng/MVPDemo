package zgc.mvpdemo.ui.contract;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import zgc.mvpdemo.presenter.base.IBasePresenter;
import zgc.mvpdemo.ui.contract.base.IBaseView;

/**
 * Created by Nick on 2017/1/7
 */
public interface HomeContract {
    public interface View extends IBaseView<Presenter> {

        /**
         * 设置适配器
         *
         * @param adapter
         */
        void setAdapter(RecyclerView.Adapter adapter);

        /**
         * 刷新完成
         */
        void refreshComplete();

        RecyclerView getRefreshView();

    }

    public interface Presenter extends IBasePresenter<View> {
        void initData();

        void setActivity(Activity activity);
    }

}
