package com.dennis.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 17:45
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 5806834314327808117L;

    //接口名
    private String interfaceName;

    //方法名
    private String methodName;

    //参数类型列表
    private Class<?>[] parameterTypes;

    //参数列表
    private Object[] args;

    //上下文
    private Map<String, Object> context;
}

