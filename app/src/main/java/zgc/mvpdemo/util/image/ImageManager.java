package zgc.mvpdemo.util.image;

import android.content.Context;
import android.widget.ImageView;

import zgc.mvpdemo.util.image.impl.GlideLoader;

/**
 * Created by Nick on 2017/12/3
 */
public class ImageManager {
    private static ILoader mILoader;

    static {
        mILoader = new GlideLoader();
    }

    public static class Builder {
        private Context mContext;
        private String mUrl;
        private ImageView mImageView;
        private int mPlaceHolder;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.mPlaceHolder = placeHolder;
            return this;
        }

        public void into(ImageView imageView) {
            this.mImageView = imageView;

            mILoader.load(mContext, mUrl, imageView,mPlaceHolder);
        }

    }
}
