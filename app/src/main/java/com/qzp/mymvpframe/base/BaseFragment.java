package com.qzp.mymvpframe.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qzp.mymvpframe.util.loading.VaryViewHelperController;

/**
 * Created by qizepu on 2017/5/18.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragment<T extends BasePresenter> extends BaseLazyLoadFragment implements BaseView {

    protected View mView;

    protected T mPresenter;
    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(getLayoutId(),null);
        return mView;
    }

    private void initPresenter() {
        createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void init() {
        super.init();
        initPresenter();
        initView();
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }

    }

    protected abstract int getLayoutId();
    protected abstract void createPresenter();
    protected abstract View getLoadingTargetView();                      //设置loading，error等状态所隐藏的布局
    protected abstract void initView();                                  //初始化view
    protected abstract void onFirstVisible();
    protected abstract void onVisibleChange(boolean b);


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        onFirstVisible();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        onVisibleChange(isVisible);
    }

    @Override
    public void showError(String msg,boolean show,View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (show) {
            mVaryViewHelperController.showError(msg, onClickListener );
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void showLoading(String msg,boolean show) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (show) {
            mVaryViewHelperController.showLoading(msg, true);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void showEmpty(String msg,boolean show,View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (show) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void showNetworkError(boolean show,View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (show) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    @Override
    public void hideLoading() {
        showLoading(null,false);
    }

    /**
     * 获取状态栏高度
     * @return
     */
    protected int getStateBarHeight(){
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
