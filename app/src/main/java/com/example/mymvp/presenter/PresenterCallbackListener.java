package com.example.mymvp.presenter;

/**
 * 辅助p层将modle层的ResultCallback中的onDealWithDataCallBack方法数据返回到p层
 *
 * 在从p层返回到viwe 层
 */
public interface PresenterCallbackListener<T> {

    void onSucceed(T msg);

    void onFailure(T msg);

    void onException(T msg);

    void onComplete(T msg);

}
