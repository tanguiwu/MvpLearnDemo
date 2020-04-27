package com.example.mymvp.presenter;

import com.example.mymvp.BaseViewListener;

public class BasePresenter<T extends BaseViewListener> {


    //view层辅助p层将modle层数据回调到view层
    private T resultCallbackViewListener;

    //用于判断是哪几种类型 --PresenterTransportListener
    private int type;


    public void setPresenterCallbackView(T resultCallbackViewListener) {
        this.resultCallbackViewListener = resultCallbackViewListener;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.resultCallbackViewListener = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return resultCallbackViewListener != null;
    }

    public T getResultCallbackViewListener() {
        return resultCallbackViewListener;
    }


}
