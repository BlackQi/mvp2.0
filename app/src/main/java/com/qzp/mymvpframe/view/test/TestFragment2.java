package com.qzp.mymvpframe.view.test;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseFragment;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.util.utils.LogUtil;

/**
 * Created by qzp on 2018/11/22.
 */

public class TestFragment2 extends BaseFragment<TestPresenter> implements ITestView{

    private TextView tv;

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test2;
    }

    @Override
    protected void onFirstVisible() {
        mPresenter.getMainData(mContext);
    }



    @Override
    protected void onVisibleChange(boolean b) {

        LogUtil.d("onVisibleChange2"+b);

    }

    @Override
    protected View getLoadingTargetView() {
        return mView.findViewById(R.id.fragment_tv);
    }

    @Override
    protected void initView() {
        tv = mView.findViewById(R.id.fragment_tv);
        View view = mView.findViewById(R.id.fill_view);
        if (getStateBarHeight() != 0){
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = getStateBarHeight();
            view.setLayoutParams(layoutParams);
        }
    }


    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onsuccess(MainBean t) {
        tv.setText(t.getCarouselList().get(0).getImgUrl());
    }

    @Override
    public void onError(String msg) {

    }
}
