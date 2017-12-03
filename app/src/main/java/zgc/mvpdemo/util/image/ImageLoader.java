package zgc.mvpdemo.util.image;

import android.content.Context;

/**
 * Created by Nick on 2017/12/2
 */
public class ImageLoader {
    public static void init() {
    }

    public static ImageManager.Builder with(Context context) {
        return new ImageManager.Builder(context);
    }


}
