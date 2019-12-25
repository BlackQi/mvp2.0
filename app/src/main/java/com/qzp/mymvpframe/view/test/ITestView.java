package com.qzp.mymvpframe.view.test;

import com.qzp.mymvpframe.base.BaseView;
import com.qzp.mymvpframe.model.bean.MainBean;

/**
 * Created by qizepu on 2017/5/17.
 */

public interface ITestView extends BaseView {

    void onsuccess(MainBean mainBean);
    void onError(String msg);
}
