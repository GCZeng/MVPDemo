package zgc.mvpdemo.service;


import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zgc.mvpdemo.model.GankDataModel;

/**
 * Created by Nick on 2017/1/6
 */
public interface ApiService {
    /**
     * 获取图片数据列表
     * @param pagesize
     * @param page
     * @return
     */
    @GET("data/福利/{pagesize}/{page}")
    Flowable<GankDataModel> getPicList(@Path("pagesize") int pagesize, @Path("page") int page);

    /**
     * 获取休息视频列表
     * @param pagesize
     * @param page
     * @return
     */
    @GET("data/休息视频/{pagesize}/{page}")
    Flowable<GankDataModel> getVideoList(@Path("pagesize") int pagesize, @Path("page") int page);
}
