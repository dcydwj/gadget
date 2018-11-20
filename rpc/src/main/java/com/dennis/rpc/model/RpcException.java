package com.dennis.rpc.model;

/**
 * @Description:
 * @Time: 2018-07-21 18:07
 * @Version: 1.0
 */
public class RpcException extends RuntimeException {
    private static final long serialVersionUID = -3650423832236220215L;

    public RpcException() {
        super();
    }
    public RpcException(String message) {
        super(message);
    }
}
