package com.qzp.mymvpframe.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qzp on 2018/11/30.
 */

public class SplashBean implements Parcelable{

    /**
     * delFlag : 0
     * detailsLink : http://www.biquge.info/10_10218/
     * id : 1
     * imgUrl : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=124985189,1295706314&fm=27&gp=0.jpg
     * sort : 1
     * useable : 1
     */

    private String delFlag;
    private String detailsLink;
    private String id;
    private String imgUrl;
    private int sort;
    private String useable;

    protected SplashBean(Parcel in) {
        delFlag = in.readString();
        detailsLink = in.readString();
        id = in.readString();
        imgUrl = in.readString();
        sort = in.readInt();
        useable = in.readString();
    }

    public static final Parcelable.Creator<SplashBean> CREATOR = new Parcelable.Creator<SplashBean>() {
        @Override
        public SplashBean createFromParcel(Parcel in) {
            return new SplashBean(in);
        }

        @Override
        public SplashBean[] newArray(int size) {
            return new SplashBean[size];
        }
    };

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(delFlag);
        parcel.writeString(detailsLink);
        parcel.writeString(id);
        parcel.writeString(imgUrl);
        parcel.writeInt(sort);
        parcel.writeString(useable);
    }
}
