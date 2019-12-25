package com.qzp.mymvpframe.view.iview;

import com.qzp.mymvpframe.base.BaseView;
import com.qzp.mymvpframe.model.bean.SplashBean;

import java.util.ArrayList;

/**
 * Created by qzp on 2018/11/30.
 */

public interface ISplashView extends BaseView {

    void onSuccess(ArrayList<SplashBean> splashBean);
}
