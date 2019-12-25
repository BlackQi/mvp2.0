package com.qzp.mymvpframe.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseActivity;
import com.qzp.mymvpframe.base.BaseAppCompatActivity;
import com.qzp.mymvpframe.base.BaseWebViewActivity;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.SplashBean;
import com.qzp.mymvpframe.presenter.SplashPresenter;
import com.qzp.mymvpframe.util.network.NetUtils;
import com.qzp.mymvpframe.util.utils.CommonUtils;
import com.qzp.mymvpframe.util.utils.GlideUtils;
import com.qzp.mymvpframe.util.utils.StringEmptyUtils;
import com.qzp.mymvpframe.view.iview.ISplashView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qzp on 2018/11/30.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashView {


    private static final int GO_ADVERTISEMENT = 1002;              //广告页
    private static final int GO_GUIDE = 1001;                      //引导页
    private static final int START_TIMER = 1003;                   //启动定时器
    private static final long SPLASH_DELAY_MILLIS_SHORT = 2000;    //延迟2秒
    private static final long TIMER = 1000;                        //延迟1秒
    private boolean isNoFirstIn = false;
    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    private Timer timer;
    private String imageUrl = null;       //广告图片的url
    private String loadUrl = null;        //广告加载网页的url
    private String title = null;          //广告页的标题

    private ImageView image;
    private TextView tv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected View getLoadingTargetView() {
        return findViewById(R.id.splash_image);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new SplashPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForImageView(this,null);
        image = findViewById(R.id.splash_image);
        tv = findViewById(R.id.splash_tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });
    }

    @Override
    protected void initDatas() {
        mPresenter.getSplashAdvertisement(mContext,"1");
        init();
    }



    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_ADVERTISEMENT:
                    goAdvertisement();
                    break;
                case START_TIMER:
                    int time = Integer.parseInt(tv.getText().subSequence(0, 1).toString());
                    if (time > 1) {
                        tv.setText((time - 1) + " 关闭");
                    } else {
                        stopTimer();
                    }
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };



    private void goHome() {
        readyGoThenKill(MainActivity.class);
    }
    /**
     * 广告页  如果图片地址为空 直接跳转到主界面 否则显示广告页
     */
    private void goAdvertisement() {
        if (TextUtils.isEmpty(imageUrl)) {
            goHome();
        } else {
            GlideUtils.loadBanner(mContext,imageUrl,image);
            tv.setVisibility(View.VISIBLE);
            startTimer();

            if (!TextUtils.isEmpty(loadUrl) && loadUrl.startsWith("http")){
                image.setClickable(true);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stopTimer();
//                        if (loadUrl.contains(".tmall.")){
//                            if (loadUrl.contains("+")){
//                                String[] split = loadUrl.split("\\+");
//                                JumpToTianMaoUtils.totaoBao(mContext, split[1], split[0]);
//                            }else {
//                                JumpToTianMaoUtils.totaoBao(mContext, "-1", loadUrl);
//                            }
//                        }else {
                            Bundle bundle = new Bundle();
                            bundle.putString(BaseWebViewActivity.BUNDLE_KEY_TITLE, StringEmptyUtils.isEmptyResuleString(title));
                            bundle.putBoolean(BaseWebViewActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, false);
                            bundle.putString(BaseWebViewActivity.BUNDLE_KEY_URL, loadUrl);
                            readyGo(BaseWebViewActivity.class, bundle);
//                        }
                    }
                });
            }

        }

    }


    /**
     * 引导页
     */
    private void goGuide() {
        readyGoThenKill(GuideActivity.class);
    }

    /**
     * 启动定时器
     */
    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(START_TIMER);
            }
        };
        timer.schedule(task, TIMER, TIMER);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        goHome();
    }


    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isNoFirstIn = preferences.getString("isFirstIn", "").endsWith(CommonUtils.getVersionName(mContext));

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (isNoFirstIn) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_ADVERTISEMENT, SPLASH_DELAY_MILLIS_SHORT);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS_SHORT);
        }
        setGuided();
    }

    private void setGuided() {
        SharedPreferences preferences = mContext.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putString("isFirstIn", CommonUtils.getVersionName(mContext));
        // 提交修改
        editor.apply();
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

    @Override
    protected boolean isApplyParentTheme() {
        return false;
    }

    @Override
    public void onSuccess(ArrayList<SplashBean> splashEntity) {
        if (splashEntity != null && splashEntity.size() > 0){
            String imgUrl = splashEntity.get(0).getImgUrl();
            String detailsLink = splashEntity.get(0).getDetailsLink();
            String useable = splashEntity.get(0).getUseable();
            if (null != imgUrl && !"".equals(imgUrl)) {
                imageUrl = imgUrl;
            }
            if (null != detailsLink && !"".equals(detailsLink)) {
                loadUrl = detailsLink;
            }
            if (null != useable && !"".equals(useable)) {
                title = useable;
            }

            Log.e("splashImageUrl ", splashEntity.get(0).getImgUrl());
        }
    }
}
