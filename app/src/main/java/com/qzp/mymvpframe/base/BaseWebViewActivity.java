package com.qzp.mymvpframe.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.util.customview.BrowserLayout;
import com.qzp.mymvpframe.util.network.NetUtils;
import com.qzp.mymvpframe.util.utils.ToastUtils;

/**
 * Created by qzp on 2018/11/29.
 */

public class BaseWebViewActivity extends BaseAppCompatActivity implements BrowserLayout.ReceivedTitleListener {


    public static final String BUNDLE_KEY_URL = "BUNDLE_KEY_URL";
    public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    public static final String BUNDLE_KEY_SHOW_BOTTOM_BAR = "BUNDLE_KEY_SHOW_BOTTOM_BAR";
    public static final String BUNDLE_KEY_SHOW_TOP_BAR = "BUNDLE_KEY_SHOW_TOP_BAR";

    private String mWebUrl = null;
    private String mWebTitle = null;
    private boolean isShowBottomBar = false;
    private boolean isShowTopBar = true;
    private TextView title;

    private Toolbar mToolBar = null;
    private BrowserLayout mBrowserLayout = null;
    private ImageView back;

    @Override
    protected void getBundleExtras(Bundle extras) {
        mWebTitle = extras.getString(BUNDLE_KEY_TITLE);
        mWebUrl = extras.getString(BUNDLE_KEY_URL);
        isShowBottomBar = extras.getBoolean(BUNDLE_KEY_SHOW_BOTTOM_BAR);
        isShowTopBar = extras.getBoolean(BUNDLE_KEY_SHOW_TOP_BAR, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseweb);
        initView();
        mBrowserLayout.setReceivedTitleListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) mContext).finish();
            }
        });
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mToolBar.setNavigationIcon(null);
            mToolBar.showOverflowMenu();
            mToolBar.setContentInsetsRelative(0, 0);
        }

        if (!TextUtils.isEmpty(mWebTitle)) {
            title.setText(mWebTitle);
        }

        if (!TextUtils.isEmpty(mWebUrl)) {
            mBrowserLayout.loadUrl(mWebUrl);
        } else {
            ToastUtils.showShort(getApplicationContext(),"获取URL地址失败");
        }

        if (!isShowBottomBar) {
            mBrowserLayout.hideBrowserController();
        } else {
            mBrowserLayout.showBrowserController();
        }

        if (!isShowTopBar) {
            mToolBar.setVisibility(View.GONE);
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

    private void initView() {
        back = (ImageView) findViewById(R.id.title_back);
        mToolBar = (Toolbar) findViewById(R.id.common_toolbar);
        mBrowserLayout = (BrowserLayout) findViewById(R.id.common_web_browser_layout);
        title = (TextView) findViewById(R.id.title);
    }

    @Override
    public void receivedTitle(String title) {
        if (TextUtils.isEmpty(mWebTitle)){
            this.title.setText(title);
        }
    }

    @Override
    protected void onDestroy() {
        mBrowserLayout.destroy();
        super.onDestroy();
    }
}
