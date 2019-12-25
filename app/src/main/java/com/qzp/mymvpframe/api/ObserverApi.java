package com.qzp.mymvpframe.api;

import android.content.Context;
import android.text.TextUtils;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseView;
import com.qzp.mymvpframe.util.utils.AppUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by qizepu on 2017/5/17.
 */

public abstract class ObserverApi<T> implements Observer<T> {

    private Context mContext;
    private boolean flag = false;//是否显示进度条
    private BaseView baseView;
    private String loadingMsg;

    /**
     * 不需要展示loading的构造方法
     * @param context  上下文
     */
    public ObserverApi(Context context) {
        this.mContext = context;
    }

    /**
     * 需要展示loading的构造方法
     *
     * @param context       上下文
     * @param baseView      继承了BaseView的Activity 或者Fragment
     * @param flag          是否展示
     */
    public ObserverApi(Context context, BaseView baseView,boolean flag) {
        this.flag = flag;
        this.mContext = context;
        this.baseView = baseView;
    }

    /**
     * 需要展示loading的构造方法 可以添加提示词 比如 正在登录 默认是正在加载中。。。
     *
     * @param context       上下文
     * @param baseView      继承了BaseView的Activity 或者Fragment
     * @param flag          是否展示
     * @param msg          Loading提示词
     */
    public ObserverApi(Context context, BaseView baseView,boolean flag,String msg) {
        this.flag = flag;
        this.mContext = context;
        this.baseView = baseView;
        this.loadingMsg = msg;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (flag && null != baseView) {
            if (TextUtils.isEmpty(loadingMsg)){
                baseView.showLoading(null,true);
            }else {
                baseView.showLoading(loadingMsg,true);
            }
        }
    }


    @Override
    public void onNext(T t) {
        if (flag && null != baseView){
            baseView.hideLoading();
        }
        onSuccess(t);
    }

    //将错误集中处理，或者将错误返回  单独处理
    @Override
    public void onError(Throwable e) {
        if (flag && null != baseView){
            baseView.hideLoading();
        }

        if (AppUtils.isApkInDebug(mContext)){
            onError(e.getMessage());
        }else {
            onError(mContext.getString(R.string.common_error_friendly_msg));
        }

//        if (e instanceof ExceptionApi) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//        } else if ((e instanceof UnknownHostException)) {
//            Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof JsonSyntaxException) {
//            Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof SocketTimeoutException) {
//            Toast.makeText(mContext, "连接超时", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof ConnectException) {
//            Toast.makeText(mContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
//        }


    }

    public abstract void onSuccess(T t);
    public abstract void onError(String msg);
}