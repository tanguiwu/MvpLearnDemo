package com.example.mymvp.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mymvp.BaseViewListener;
import com.example.mymvp.ResultCallbackViewListener;
import com.example.mymvp.bean.SendBean;
import com.example.mymvp.modle.ResultCallback;

public class PresenterCallbackView extends BasePresenter<ResultCallbackViewListener> {

    //modle层返回数据的结果
    private ResultCallback resultCallback;

    //view层辅助p层将modle层数据回调到view层 ---因为继承基类BasePresenter 所以暂时无用
    private ResultCallbackViewListener resultCallbackViewListener;

    //用于判断是哪几种类型 --PresenterTransportListener
    private int type;

    public PresenterCallbackView() {
        this.resultCallback = new ResultCallback();
    }


    public void setPresenterCallbackView(ResultCallbackViewListener resultCallbackViewListener) {
        super.setPresenterCallbackView(resultCallbackViewListener);
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        super.detachView();
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return super.isViewAttached();
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //获取view层数据传递给modle层，在modle层将数据放出去给onDealWithDataCallBack
    public void onTransportDataListener(Activity activity, final String msg) {

        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(activity, "最好输入一下请求数据", Toast.LENGTH_LONG).show();
        }

        if (resultCallback != null && getResultCallbackViewListener() != null) {
            resultCallback.onTransportDataListener(getType(), new PresenterTransportListener<SendBean>() {
                @Override
                public SendBean onSucceed() {
                    SendBean bean = new SendBean();
                    bean.msg = "成功数据" +msg;
                    bean.sendCode = 2000;
                    return bean;
                }

                @Override
                public SendBean onFailure() {
                    SendBean bean = new SendBean();
                    bean.msg = "失败数据" +msg;
                    bean.sendCode = 2001;
                    return bean;
                }

                @Override
                public SendBean onException() {
                    SendBean bean = new SendBean();
                    bean.msg = "异常数据" +msg;
                    bean.sendCode = 2002;
                    return bean;
                }

                @Override
                public SendBean onComplete() {
                    SendBean bean = new SendBean();
                    bean.msg = "传送完成" +msg;
                    bean.sendCode = 2003;
                    return bean;
                }
            });
        } else {
            Toast.makeText(activity, "当前数据获取传输断开了", Toast.LENGTH_LONG).show();
        }
    }


    //回调数据到view层
    public void onPresenterCallbackView(Activity activity) {

        if (getType() == 0) {
            Toast.makeText(activity, "请设置请求参数", Toast.LENGTH_LONG).show();
        }
        if (resultCallback != null && getResultCallbackViewListener() != null) {
            resultCallback.onDealWithDataCallBack(getType(), new PresenterCallbackListener<String>() {
                @Override
                public void onSucceed(String msg) {
                    getResultCallbackViewListener().showData(msg);
                }

                @Override
                public void onFailure(String msg) {
                    getResultCallbackViewListener().showFailureMessage(msg);
                }

                @Override
                public void onException(String msg) {
                    getResultCallbackViewListener().showErrorMessage(msg);
                }

                @Override
                public void onComplete(String msg) {
                    getResultCallbackViewListener().showComplete(msg);
                }
            });

        } else {
            Toast.makeText(activity, "当前传输断开了", Toast.LENGTH_LONG).show();
        }
    }
}


