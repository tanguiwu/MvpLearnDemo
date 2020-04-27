package com.example.mymvp;


/**
 * 联系presenter 与view的接口
 */
public interface ResultCallbackViewListener extends BaseViewListener {


    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data);

    /**
     * 当数据请求失败后，调用此接口提示
     * @param msg 失败原因
     */
    void showFailureMessage(String msg);

    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage(String msg);





}
