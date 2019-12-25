package com.qzp.mymvpframe.view.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseActivity;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.util.customview.XViewPager;
import com.qzp.mymvpframe.util.network.NetUtils;
import com.qzp.mymvpframe.util.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by qzp on 2018/11/21.
 */

public class TestActivity extends BaseActivity<TestPresenter> implements ITestView {

    private XViewPager vp;
    private String key;

    @Override
    protected void getBundleExtras(Bundle extras) {
        key = extras.getString("key");
        EventBus.getDefault().post(new EventCenter<>(200, "测试eventbus"));
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {
        ToastUtils.showShort(mContext,"网络已连接"+type);
    }

    @Override
    protected void onNetworkDisConnected() {
        Toast.makeText(mContext,"网络已断开",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test2;
    }

    @Override
    protected View getLoadingTargetView() {
        return findViewById(R.id.vp);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 50,null);
        vp = (XViewPager) findViewById(R.id.vp);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment1());
        fragments.add(new TestFragment2());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(fragmentAdapter);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected boolean isApplyParentTheme() {
        return false;
    }

    @Override
    public void onsuccess(MainBean t) {

    }

    @Override
    public void onError(String msg) {

    }
}
