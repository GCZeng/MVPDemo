package zgc.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zgc.mvpdemo.R;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.ui.adapter.base.BaseAdapter;
import zgc.mvpdemo.util.LogUtil;

/**
 * Created by Nick on 2017/2/6
 */
public class HomeAdapter extends BaseAdapter<HomeAdapter.MyViewHolder> {
    private Context mContext;

    public HomeAdapter(List<GankData> data) {
        this.data = data;
    }

    public HomeAdapter(List<GankData> data, Context context) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_home_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GankData gankData = (GankData) getData(position);

        holder.tv_title.setText(gankData.getDesc());

        LogUtil.d(gankData.getUrl());

    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        public MyViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
        }
    }


}
