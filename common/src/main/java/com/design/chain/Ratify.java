package com.design.chain;

/**
 * @Description:
 * @Time: 2018-12-09 17:06
 * @Version: 1.0
 */
public interface Ratify {


    //请求处理
    Result deal(Request request);

    /**
     * 接口描述：对request和Result封装，用来转发
     */
    interface Chain {

        // 获取当前request
        Request request();

        // 转发request
        Result proceed(Request request);
    }



}
