package com.qzp.mymvpframe.util.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qzp.mymvpframe.R;

/**
 * Created by qzp on 2018/11/21.
 */

public class GlideUtils {


    /**
     * 加载图片
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void imageLoader(Context mContext, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.defaullt_image)
                    .apply(getRequestOptions())
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .load(url)
                    .apply(getRequestOptions())
                    .into(imageView);
        }

    }

    /**
     * 加载图片 没有缓存
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void imageLoaderNoCatch(Context mContext, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.defaullt_image)
                    .apply(getRequestOptionsNoCatch())
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .load(url)
                    .apply(getRequestOptions())
                    .into(imageView);
        }

    }

    /**
     * 加载圆角图片
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void imageLoaderFillet(Context mContext, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.defaullt_image)
                    .apply(getRequestOptions())
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .load(url)
                    .apply(getFilletRequestOptions())
                    .into(imageView);
        }

    }


    /**
     * 加载圆形图片
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void imageLoaderCircular(Context mContext, String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.defaullt_image)
                    .apply(getRequestOptions())
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .load(url)
                    .apply(getCircularRequestOptions())
                    .into(imageView);
        }
    }

    /**
     * 加载banner图片
     * @param mContext
     * @param url
     * @param imageView
     */
    public static void loadBanner(Context mContext, String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.default_banner)
                    .apply(getBannerRequestOptions())
                    .into(imageView);
        } else if (url.contains("http")) {
            Glide.with(mContext)
                    .load(url)
                    .apply(getBannerRequestOptions())
                    .into(imageView);
        }
    }



    private static RequestOptions getRequestOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.defaullt_image);
        requestOptions.error(R.drawable.defaullt_image);
        return requestOptions;
    }

    private static RequestOptions getBannerRequestOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.default_banner);
        requestOptions.error(R.drawable.default_banner);
        return requestOptions;
    }

    private static RequestOptions getFilletRequestOptions() {
        RoundedCorners roundedCorners= new RoundedCorners(8);
        RequestOptions requestOptions=RequestOptions.bitmapTransform(roundedCorners);
        requestOptions.placeholder(R.drawable.defaullt_image);
        requestOptions.error(R.drawable.defaullt_image);
        return requestOptions;
    }

    private static RequestOptions getCircularRequestOptions() {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        requestOptions.placeholder(R.drawable.defaullt_image);
        requestOptions.error(R.drawable.defaullt_image);
        return requestOptions;
    }


    private static RequestOptions getRequestOptionsNoCatch() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.defaullt_image);
        requestOptions.error(R.drawable.defaullt_image);
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        return requestOptions;
    }


}
