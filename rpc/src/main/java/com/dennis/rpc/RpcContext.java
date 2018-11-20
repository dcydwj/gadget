package com.dennis.rpc;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Time: 2018-07-21 21:07
 * @Version: 1.0
 */
public class RpcContext {

    public static ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(() -> {
        Map<String, Object> map = new ConcurrentHashMap<>();
        return map;
    });

    public static void addAttribute(String key, Object value) {
        context.get().put(key, value);
    }

    public static Object getAttribute(String key){
        return context.get().get(key);
    }

    public static Map<String,Object> getAttributes(){
        return Collections.unmodifiableMap(context.get());
    }

}
