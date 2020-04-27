package com.example.mymvp;

 public interface BaseViewListener {

    /**
     * 显示正在加载进度框
     */
      void showLoading();

    /**
     * 隐藏正在加载进度框
     */
       void hideLoading();


    /**
     * 当数据请求完成
     */
       void showComplete(String msg);

}
