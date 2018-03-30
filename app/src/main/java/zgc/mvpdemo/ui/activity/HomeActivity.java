package zgc.mvpdemo.ui.activity;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import zgc.mvpdemo.R;
import zgc.mvpdemo.presenter.home.HomePresenter;
import zgc.mvpdemo.ui.activity.base.BaseDiActivity;
import zgc.mvpdemo.ui.adapter.decoration.HomeItemDecoration;
import zgc.mvpdemo.ui.contract.HomeContract;
import zgc.mvpdemo.util.LogUtil;
import zgc.mvpdemo.widget.refreshlist.RefreshList;

import static android.support.v7.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE;

/**
 * Created by Nick on 2017/12/1
 */
public class HomeActivity extends BaseDiActivity implements HomeContract.View {
    @BindView(R.id.rl_list)
    RefreshList rl_list;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {


        setTitle(R.string.app_name);

        rl_list.refresh(() -> {
            mHomePresenter.refresh();
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        rl_list.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        //设置布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(GAP_HANDLING_NONE);
        rv_list.setLayoutManager(layoutManager);

        rv_list.addItemDecoration(new HomeItemDecoration(this));

        rv_list.setPadding(0, 0, 0, 0);
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });

        //设置adapter
        rv_list.setHasFixedSize(true);

        //间隔
//        rv_list.addItemDecoration(new HomeItemDecoration(this));

        mHomePresenter.setActivity(this);
    }

    @Override
    protected void initData() {
        rl_list.setRefreshing(true);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (rv_list.getAdapter() != adapter) {
            rv_list.setAdapter(adapter);
        }
    }

    @Override
    public void refreshComplete() {
        rl_list.refreshComplete();
    }

    @Override
    public RecyclerView getRefreshView() {
        return rv_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHomePresenter.takeView(this);
        if (isFirstLoadData) {
            isFirstLoadData = false;
            mHomePresenter.initData();
            mHomePresenter.loadGankData(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHomePresenter.dropView();
    }
}
