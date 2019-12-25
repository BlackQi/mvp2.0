package com.qzp.mymvpframe.base;

import android.view.View;

/**
 * Created by qizepu on 2017/5/17.
 *
 * 所有的接口都继承这个接口
 */

public interface BaseView {

    void showError(String msg,boolean show,View.OnClickListener onClickListener);
    void showEmpty(String msg,boolean show,View.OnClickListener onClickListener);
    void showNetworkError(boolean show,View.OnClickListener onClickListener);
    void showLoading(String msg,boolean show);
    void hideLoading();

}
