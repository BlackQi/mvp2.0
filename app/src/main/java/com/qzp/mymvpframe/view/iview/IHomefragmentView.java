package com.qzp.mymvpframe.view.iview;

import com.qzp.mymvpframe.base.BaseView;
import com.qzp.mymvpframe.model.bean.HomeFragmentBannerBean;

/**
 * Created by qzp on 2018/11/30.
 */

public interface IHomefragmentView extends BaseView {

    void loadBannerSuccess(HomeFragmentBannerBean homeFragmentBannerBean);    //获取轮播图成功
    void loadBannerError(String message);      //获取轮播图失败

}
