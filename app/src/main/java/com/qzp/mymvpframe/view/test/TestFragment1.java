package com.qzp.mymvpframe.view.test;

import android.view.View;
import android.widget.ImageView;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseFragment;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.util.utils.GlideUtils;
import com.qzp.mymvpframe.util.utils.LogUtil;

/**
 * Created by qzp on 2018/11/22.
 */

public class TestFragment1 extends BaseFragment<TestPresenter> implements ITestView{

    private ImageView image;

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test1;
    }

    @Override
    protected void onFirstVisible() {
        mPresenter.getMainData(mContext);

    }


    @Override
    protected void onVisibleChange(boolean b) {

        LogUtil.d("onVisibleChange"+b);
    }

    @Override
    protected View getLoadingTargetView() {
        return mView.findViewById(R.id.fragment_image);
    }

    @Override
    protected void initView() {
        image = mView.findViewById(R.id.fragment_image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
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
        GlideUtils.imageLoader(mContext,t.getCarouselList().get(0).getImgUrl(),image);
    }

    @Override
    public void onError(String msg) {

    }
}
