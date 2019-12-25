package com.qzp.mymvpframe.presenter;

import android.content.Context;

import com.qzp.mymvpframe.api.ObserverApi;
import com.qzp.mymvpframe.api.RetrofitApi;
import com.qzp.mymvpframe.base.BaseBean;
import com.qzp.mymvpframe.base.BasePresenter;
import com.qzp.mymvpframe.model.bean.SplashBean;
import com.qzp.mymvpframe.model.httputils.HttpUtils;
import com.qzp.mymvpframe.view.iview.ISplashView;

import java.util.ArrayList;

/**
 * Created by qizepu on 2018/11/30.
 */

public class SplashPresenter extends BasePresenter<ISplashView>{

    //请求数据
    public void getSplashAdvertisement(Context context,String type){
        HttpUtils.getData(RetrofitApi.getServer().getSplashAdvertisement(type),new ObserverApi<BaseBean<ArrayList<SplashBean>>>(context) {
            @Override
            public void onSuccess(BaseBean<ArrayList<SplashBean>> o) {
                if (null != getView()){
                    getView().onSuccess(o.getData());
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

}
