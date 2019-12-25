package com.qzp.mymvpframe.api;

import com.qzp.mymvpframe.application.Content;
import com.qzp.mymvpframe.base.BaseBean;
import com.qzp.mymvpframe.model.bean.HomeFragmentBannerBean;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.model.bean.SplashBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by qizepu on 2017/5/17.
 *
 * 接口
 */

public interface Api {


    //测试接口
    @GET(Content.HOME_BANNER)
    Observable<BaseBean<MainBean>> getData();

    //启动页广告
    @FormUrlEncoded
    @POST(Content.SPLASH)
    Observable<BaseBean<ArrayList<SplashBean>>> getSplashAdvertisement(@Field("type") String type);

    //首页banner
    @GET(Content.HOME_BANNER)
    Observable<BaseBean<HomeFragmentBannerBean>> getBanner();


}
