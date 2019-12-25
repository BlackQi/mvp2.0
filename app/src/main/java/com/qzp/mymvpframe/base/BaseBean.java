package com.qzp.mymvpframe.base;

/**
 * Created by qzp on 2018/11/20.
 */

public class BaseBean<T> {

    /**
     * resultMsg : App TEST SUCCESS
     * resultCode : 1
     * data : {}
     */

    private String message;
    private String code;
    private T data;
    private T resultRntity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Deprecated
    public T getResultRntity() {

//        if(resultRntity != null){
//            return resultRntity;
//        }

//        if (!TextUtils.isEmpty(data)) {
//            if ((data.contains("{") || data.contains("["))) {
//                try {
////                AES mAes = new AES(AppApplication.humanPassphrase);
////                data = mAes.decrypt(data);
//                    resultRntity = new Gson().fromJson(data, new TypeToken<T>() {
//                    }.getType());
////                mAes = null;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    AES mAes = new AES(AppApplication.humanPassphrase);
//                    data = mAes.decrypt(data);
//                    resultRntity = new Gson().fromJson(data, new TypeToken<T>() {
//                    }.getType());
//                    mAes = null;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        setResultRntity(resultRntity);
        return data;
    }

    @Deprecated
    public void setResultRntity(T resultRntity) {
        this.resultRntity = resultRntity;
    }


}
