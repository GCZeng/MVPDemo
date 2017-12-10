package zgc.mvpdemo.ui.adapter;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zgc.mvpdemo.R;
import zgc.mvpdemo.model.entity.GankData;
import zgc.mvpdemo.util.DisplayUtiil;
import zgc.mvpdemo.util.image.ImageLoader;
import zgc.mvpdemo.util.image.ImageManager;
import zgc.mvpdemo.util.image.impl.GlideApp;

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

        if (mIHeightMap.containsKey(gankData.getUrl())) {
            ImageLoader.with(mContext)
                    .url(gankData.getUrl())
                    .scaleType(ImageManager.ScaleType.CENTER_CROP)
                    .into(imageView);
        } else {
            GlideApp.with(mContext)
                    .asBitmap()
                    .load(gankData.getUrl())
                    .centerCrop()
                    .placeholder(R.mipmap.pic_placeholder)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            if (resource != null) {
                                int height = DisplayUtiil.getScreenWidth() / 2 * resource.getHeight() / resource.getWidth();
                                mIHeightMap.put(gankData.getUrl(), height);

                                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                                lp.height = height;

                                imageView.setImageBitmap(resource);
                            }
                        }
                    });
        }

        helper.addOnClickListener(R.id.iv_pic);

    }


}
