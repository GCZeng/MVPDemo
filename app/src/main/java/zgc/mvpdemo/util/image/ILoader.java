package zgc.mvpdemo.util.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Nick on 2017/12/2
 */
public interface ILoader {
    void load(Context context, String url, ImageView imageView);

    void load(Context context, String url, ImageView imageView,int placeHolder);
}
