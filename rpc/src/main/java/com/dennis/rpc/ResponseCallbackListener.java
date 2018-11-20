package com.dennis.rpc;

/**
 * @Description:
 * @Time: 2018-07-22 07:36
 * @Version: 1.0
 */
public interface ResponseCallbackListener {
    void onResponse(Object response);

    void onTimeout();

    void onException(Exception e);
}
