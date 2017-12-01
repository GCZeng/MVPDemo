package zgc.mvpdemo.ui.contract;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Nick on 2017/1/7
 */
public class HomeContract {
    public interface View {

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

    }

    public interface Presenter {

        void loadGankData(boolean clean);
    }

}
