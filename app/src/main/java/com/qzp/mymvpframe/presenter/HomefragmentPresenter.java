package com.qzp.mymvpframe.presenter;

import android.content.Context;

import com.qzp.mymvpframe.api.ObserverApi;
import com.qzp.mymvpframe.api.RetrofitApi;
import com.qzp.mymvpframe.base.BaseBean;
import com.qzp.mymvpframe.base.BasePresenter;
import com.qzp.mymvpframe.model.bean.HomeFragmentBannerBean;
import com.qzp.mymvpframe.model.bean.SplashBean;
import com.qzp.mymvpframe.model.httputils.HttpUtils;
import com.qzp.mymvpframe.view.iview.IHomefragmentView;
import com.qzp.mymvpframe.view.iview.ISplashView;

import java.util.ArrayList;

/**
 * Created by qizepu on 2018/11/30.
 */

public class HomefragmentPresenter extends BasePresenter<IHomefragmentView>{

    //请求数据
    public void getBanner(Context context){
        HttpUtils.getData(RetrofitApi.getServer().getBanner(),new ObserverApi<BaseBean<HomeFragmentBannerBean>>(context) {
            @Override
            public void onSuccess(BaseBean<HomeFragmentBannerBean> o) {
                if (null != getView()){
                    getView().loadBannerSuccess(o.getData());
                }
            }

            @Override
            public void onError(String msg) {
                if (null != getView()){
                    getView().loadBannerError(msg);
                }
            }
        });
    }

}
