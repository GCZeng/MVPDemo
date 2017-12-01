package zgc.mvpdemo.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zgc.mvpdemo.R;
import zgc.mvpdemo.presenter.home.HomePresenter;
import zgc.mvpdemo.ui.activity.base.BaseActivity;
import zgc.mvpdemo.ui.adapter.decoration.HomeItemDecoration;
import zgc.mvpdemo.ui.contract.HomeContract;
import zgc.mvpdemo.util.ToastUtil;
import zgc.mvpdemo.widget.refreshlist.RefreshList;

/**
 * Created by Nick on 2017/12/1
 */
public class HomeActivity extends BaseActivity implements HomeContract.View{
    @BindView(R.id.rl_list) RefreshList rl_list;
    @BindView(R.id.rv_list) RecyclerView rv_list;

    @Inject HomePresenter mHomePresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        rl_list.refresh(() -> {
            ToastUtil.showShort("刷新");
            mHomePresenter.loadGankData(true);
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        rl_list.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        //设置布局管理器
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //设置adapter
        rv_list.setHasFixedSize(true);


        //间隔
        rv_list.addItemDecoration(new HomeItemDecoration(this));

    }

    @Override
    protected void initData() {
        rl_list.setRefreshing(true);
        mHomePresenter.loadGankData(true);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        rv_list.setAdapter(adapter);
    }

    @Override
    public void refreshComplete() {
        rl_list.refreshComplete();
    }
}
