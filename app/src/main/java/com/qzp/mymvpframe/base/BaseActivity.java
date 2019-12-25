package com.qzp.mymvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.util.loading.VaryViewHelperController;

import org.xutils.common.util.DensityUtil;

/**
 * Created by qizepu on 2017/5/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends BaseAppCompatActivity implements BaseView{


    protected T mPresenter;
    private VaryViewHelperController mVaryViewHelperController = null;
    private ImageView back;
    private TextView title;
    private View fillView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
        initView();
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        initDatas();//初始化数据
    }

    private void init(){
        createPresenter();
        if (mPresenter == null){
            throw new IllegalArgumentException("You must return a right presenter for request");
        }else {
            mPresenter.attachView(this);
        }
    }

    protected abstract int getLayoutId();                                //设置xml文件
    protected abstract View getLoadingTargetView();                      //设置loading，error等状态所隐藏的布局
    protected abstract void createPresenter();                           //初始化presenter
    protected abstract void initView();                                  //初始化view
    protected abstract void initDatas();                                 //初始化数据
    protected boolean setShowToobar(){                                   //是否展示toobar 默认不展示
        return false;
    }
    protected boolean setShowToobarBack(){                               //是否展示toobar的返回键 默认展示
        return true;
    }               //是否展示标题返回键 默认展示
    protected boolean setShowToobarFillView(){                               //是否展示toobar的返回键 默认展示
        return false;
    }
    protected void setTitle(String str){                                 //设置标题
        if (null != title){
            title.setText(str);
        }
    }


    @Override
    public void setContentView(int layoutResID) {
        if (setShowToobar()){
            View toobarView = getLayoutInflater().inflate(R.layout.toobar, null);
            //填充的view 当activity嵌套fragment 并且fragment有图片时 如果要适配状态栏 则需要有一个view填充
            if (setShowToobarFillView()){
                fillView = toobarView.findViewById(R.id.toobar_fill_view);
                if (getStateBarHeight() != 0){
                    ViewGroup.LayoutParams layoutParams = fillView.getLayoutParams();
                    layoutParams.width = getStateBarHeight();
                }
                fillView.setVisibility(View.VISIBLE);
            }

            Toolbar toolbar = toobarView.findViewById(R.id.common_toolbar);
            toolbar.setNavigationIcon(null);
            back = toobarView.findViewById(R.id.title_back);
            if (setShowToobarBack()){
                back.setVisibility(View.VISIBLE);
            }else {
                back.setVisibility(View.GONE);
            }
            title = toobarView.findViewById(R.id.title);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.addView(toobarView);
            View view = getLayoutInflater().inflate(layoutResID, null);
            linearLayout.addView(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            super.setContentView(linearLayout);

        }else {
            super.setContentView(layoutResID);
        }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Presenter解除绑定
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStateBarHeight(){
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
       return result;
    }

}
