package zgc.mvpdemo.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zgc.mvpdemo.R;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.util.image.ImageLoader;
import zgc.mvpdemo.util.image.ImageManager;

/**
 * Created by Nick on 2017/2/6
 */
public class HomeAdapter2 extends BaseQuickAdapter<GankData, BaseViewHolder> {

    public HomeAdapter2(List<GankData> data) {
        super(R.layout.rv_home_item, data);
    }

    private Map<String, Integer> mIHeightMap = new HashMap<>();

    @Override
    protected void convert(BaseViewHolder helper, GankData gankData) {
        helper.setText(R.id.tv_title, gankData.getDesc());
        ImageView imageView = helper.getView(R.id.iv_pic);

//        ImageLoader.with(mContext)
//                .url(gankData.getUrl())
//                .scaleType(ImageManager.ScaleType.CENTER_CROP)
//                .into(imageView);

        int imgHeight = 0;
        if (mIHeightMap.containsKey(gankData.getUrl())) {
            imgHeight = mIHeightMap.get(gankData.getUrl());
        } else {
            imgHeight = (int) (300 + Math.random() * 300);
            mIHeightMap.put(gankData.getUrl(), imgHeight);
        }

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = imgHeight;
        imageView.setLayoutParams(lp);

        ImageLoader.with(mContext)
                .url(gankData.getUrl())
                .scaleType(ImageManager.ScaleType.CENTER_CROP)
                .into(imageView);

        helper.addOnClickListener(R.id.iv_pic);

    }


}
