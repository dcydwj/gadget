package com.dennis.rpc.model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Time: 2018-07-21 22:33
 * @Version: 1.0
 */
public class ResponseFuture {

    public static ThreadLocal<Future<RpcResponse>> futureThreadLocal = new ThreadLocal<>();

    public static Object getResponse(int timeOut) throws InterruptedException, ExecutionException, TimeoutException {

        if (null == futureThreadLocal.get()) {
            throw new RuntimeException("Thread [" + Thread.currentThread() + "] have not set the response future!");
        }

        RpcResponse rpcResponse = futureThreadLocal.get().get(timeOut, TimeUnit.MILLISECONDS);
        if (rpcResponse.isError()) {
            throw new RuntimeException(rpcResponse.getErrorMsg());
        }

        return rpcResponse.getResponseBody();
    }


    public static void setFuture(Future<RpcResponse> future) {
        futureThreadLocal.set(future);
    }

}
