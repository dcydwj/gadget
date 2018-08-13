package com.dennis.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 18:02
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = 217642893831295095L;

    public RpcResponse() {
    }

    private Object responseBody;

    private String errorMsg;


    public boolean isError() {
        return (errorMsg != null) && (errorMsg.length() > 0);
    }

}
