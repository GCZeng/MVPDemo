package zgc.mvpdemo.ui.adapter.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;


/**
 * Created by Nick on 2017/2/6
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    protected List data = null;


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected Object getData(int position) {
        return data == null ? null : data.get(position);
    }
}
