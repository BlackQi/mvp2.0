package com.qzp.mymvpframe.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.adapter.MainFragmentsAdapter;
import com.qzp.mymvpframe.base.BaseActivity;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.model.bean.TabEntity;
import com.qzp.mymvpframe.presenter.MainPresenter;
import com.qzp.mymvpframe.util.customview.XViewPager;
import com.qzp.mymvpframe.util.network.NetUtils;
import com.qzp.mymvpframe.util.utils.WindowStateBarUtils;
import com.qzp.mymvpframe.view.fragment.HomeFragment;
import com.qzp.mymvpframe.view.fragment.MineFragment;
import com.qzp.mymvpframe.view.iview.IMainActivityView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainActivityView {


    private CommonTabLayout bottomView;
    private XViewPager viewPager;

    private String[] mTitles = {"首页", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_normal, R.mipmap.tab_my_normal};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_pressed, R.mipmap.tab_my_pressed};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MainFragmentsAdapter vpFragmentAdapter;


    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomView = findViewById(R.id.tl_bottom);
        viewPager = findViewById(R.id.home_container);
        initBottonView();
        setTitle("首页");
    }

    @Override
    protected void initDatas() {

    }

    /**
     * 初始化底部导航栏
     */
    private void initBottonView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        bottomView.setTabData(mTabEntities);
        bottomView.setOnTabSelectListener(new OnTabSelectListener() { //切换底部tab回调
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position, false);
                setToobarOnTabChanged(position);
            }

            @Override
            public void onTabReselect(int position) { //重复点击用来处理刷新或者清除提示文字

            }
        });
        fragments.add(new HomeFragment());
        fragments.add(new MineFragment());
        viewPager.setEnableScroll(false);
        viewPager.setOffscreenPageLimit(fragments.size());
        vpFragmentAdapter = new MainFragmentsAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(vpFragmentAdapter);
    }


    /**
     * 切换fragment时 设置Toobar
     */
    private void setToobarOnTabChanged(int position){

        switch (position){
            case 0:
                setTitle("首页");
                break;
            case 1:
                setTitle("我的");
                break;
            default:
                break;
        }

    }


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isApplyParentTheme() {
        return true;
    }

    @Override
    protected boolean setShowToobar() {
        return true;
    }

    @Override
    protected boolean setShowToobarBack() {
        return false;
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

    @Override
    protected View getLoadingTargetView() {
        return findViewById(R.id.fl_viewpager_root);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }


}
