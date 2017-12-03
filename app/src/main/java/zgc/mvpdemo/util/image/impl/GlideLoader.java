package zgc.mvpdemo.util.image.impl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;

import javax.inject.Inject;

import zgc.mvpdemo.util.image.ILoader;

/**
 * Created by Nick on 2017/12/2
 */
@GlideModule
public class GlideLoader extends AppGlideModule implements ILoader {

    @Inject
    public GlideLoader() {

    }

    @Override
    public void load(Context context, String url, ImageView imageView) {
//        Glide.with(context).load(url).into(imageView);
        load(context,url,imageView,-1);
    }

    @Override
    public void load(Context context, String url, ImageView imageView, int placeHolder) {
        GlideApp.with(context)
                .load(url)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
