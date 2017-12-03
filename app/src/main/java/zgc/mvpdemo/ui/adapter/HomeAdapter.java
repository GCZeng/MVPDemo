package zgc.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zgc.mvpdemo.R;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.ui.adapter.base.BaseAdapter;
import zgc.mvpdemo.util.image.ImageLoader;

/**
 * Created by Nick on 2017/2/6
 */
public class HomeAdapter extends BaseAdapter<HomeAdapter.MyViewHolder> {
    private Context mContext;

    public HomeAdapter(List<GankData> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_home_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GankData gankData = (GankData) getData(position);

        holder.tv_title.setText(gankData.getDesc());

        ImageLoader.with(mContext)
                .url(gankData.getUrl())
                .placeHolder(R.mipmap.pic_placeholder)
                .into(holder.iv_pic);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title) TextView tv_title;
        @BindView(R.id.iv_pic) ImageView iv_pic;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
