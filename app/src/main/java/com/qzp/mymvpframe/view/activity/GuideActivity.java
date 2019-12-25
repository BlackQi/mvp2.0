package com.qzp.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseAppCompatActivity;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.util.network.NetUtils;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by qzp on 2018/11/30.
 */

public class GuideActivity extends BaseAppCompatActivity {

    private BGABanner mBackgroundBanner;
    private BGABanner mBackgroundBanner2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mBackgroundBanner = findViewById(R.id.banner_guide_background);
        mBackgroundBanner2 = findViewById(R.id.banner_guide_foreground);
        mBackgroundBanner2.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                readyGoThenKill(MainActivity.class);
            }
        });
        mBackgroundBanner.setData(R.drawable.splash_01, R.drawable.splash_02, R.drawable.splash_03);
        mBackgroundBanner2.setData(R.drawable.splash_01, R.drawable.splash_02, R.drawable.splash_03);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.black);
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

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
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
}
