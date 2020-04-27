package com.example.mymvp.presenter;


/**
 * 将数据输送到modle层 modle层得到数据 把数据传递给 PresenterCallbackListener
 *
 * 然后p层调用  回调给view层 完成mvp
 */
public interface PresenterTransportListener<T> {

    T onSucceed();

    T onFailure();

    T onException();

    T onComplete();

}
