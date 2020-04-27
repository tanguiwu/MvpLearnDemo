package com.example.mymvp.modle;

import com.example.mymvp.bean.SendBean;
import com.example.mymvp.presenter.PresenterCallbackListener;
import com.example.mymvp.presenter.PresenterTransportListener;

public class ResultCallback {

    private SendBean msg;
    private String completeMsg;

    public String getCompleteMsg() {
        return completeMsg;
    }

    public void setCompleteMsg(String completeMsg) {
        this.completeMsg = completeMsg;
    }

    public SendBean getMsg() {
        return msg;
    }

    public void setMsg(SendBean msg) {
        this.msg = msg;
    }

    public void onTransportDataListener(int type, PresenterTransportListener<SendBean> listener) {

        switch (type) {
            case 1:
                setMsg(listener.onSucceed());
                break;
            case 2:
                setMsg(listener.onFailure());

                break;
            case 3:
                setMsg(listener.onException());

                break;

        }
        setCompleteMsg(listener.onComplete().msg);

    }


    public void onDealWithDataCallBack(int type, PresenterCallbackListener<String> listener) {

        switch (type) {
            case 1:
                listener.onSucceed(getMsg().msg);
                break;
            case 2:
                listener.onFailure(getMsg().msg);
                break;
            case 3:
                listener.onException(getMsg().msg);
                break;

        }
        listener.onComplete(getCompleteMsg()+getMsg().toString());

    }
}
