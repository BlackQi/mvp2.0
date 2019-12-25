package com.qzp.mymvpframe.view.test;

import android.content.Context;

import com.qzp.mymvpframe.api.ObserverApi;
import com.qzp.mymvpframe.api.RetrofitApi;
import com.qzp.mymvpframe.base.BaseBean;
import com.qzp.mymvpframe.base.BasePresenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.model.httputils.HttpUtils;
import com.qzp.mymvpframe.view.test.ITestView;

/**
 * Created by qizepu on 2017/5/17.
 */

public class TestPresenter extends BasePresenter<ITestView>{

    //请求数据
    public void getMainData(Context context){
        HttpUtils.getData(RetrofitApi.getServer().getData(),new ObserverApi<BaseBean<MainBean>>(context,getView(),true) {
            @Override
            public void onSuccess(BaseBean<MainBean> o) {
                if (null != getView()){
                    getView().onsuccess(o.getData());
                }
            }

            @Override
            public void onError(String msg) {
                if (null != getView()){
                    getView().onError(msg);
                }
            }
        });
    }

}
