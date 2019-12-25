package com.qzp.mymvpframe.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.application.MyApplication;
import com.qzp.mymvpframe.util.network.NetChangeObserver;
import com.qzp.mymvpframe.util.network.NetStateReceiver;
import com.qzp.mymvpframe.util.network.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by qizepu on 2017/5/17.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity{

    protected NetChangeObserver mNetChangeObserver = null;

    /**
     * overridePendingTransition mode
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_in);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }

        super.onCreate(savedInstanceState);

        initViewBefore();

        if (isApplyParentTheme()){
            StatusBarUtil.setColorNoTranslucent(this,getResources().getColor(R.color.white));
        }

        mContext = this;
        MyApplication.getInstance().addActivity(this);//添加当前Activity

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }

        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };

        NetStateReceiver.registerObserver(mNetChangeObserver);          //网络监听

    }

    protected abstract void getBundleExtras(Bundle extras);              //获取跳转至本页携带的信息
    protected abstract boolean isBindEventBusHere();                     //是否绑定eventbus
    protected abstract void onEventComming(EventCenter eventCenter);     //eventbus消息
    protected boolean isApplyParentTheme() {                             //是否只用父类的主题
        return true;
    }              //是否应用父布局主题
    protected abstract boolean toggleOverridePendingTransition();        //打开关闭页面是否启用动画
    protected abstract TransitionMode getOverridePendingTransitionMode();//选择动画
    protected abstract void onNetworkConnected(NetUtils.NetType type);   //网络已连接
    protected abstract void onNetworkDisConnected();                     //网络已断开

    @Override
    public void finish() {
        super.finish();

        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(0, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);     //网络监听
        MyApplication.getInstance().removeActivity(this);//移除当前Activity
    }

    @Subscribe
    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComming(eventCenter);
        }
    }

    /**
     * 在初始化view之前做操作可以重写此方法
     */
    protected void initViewBefore(){

    }


    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
