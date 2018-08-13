package com.dennis.rpc.client;

import com.dennis.rpc.ResponseCallbackListener;
import com.dennis.rpc.RpcContext;
import com.dennis.rpc.model.RpcRequest;
import com.dennis.rpc.model.RpcResponse;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 21:40
 * @Version: 1.0
 */
public class RpcConsumer extends AbstractInvocationHandler {

    private String host;

    private int port;

    private Class<?> interfaceClass;

    private int timeOut;

    private static int nThread = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService pool = Executors.newFixedThreadPool(nThread);

    /**
     * 存放当前线程正在执行的方法
     */
    private static final ThreadLocal<Set<String>> asyncMethod = ThreadLocal.withInitial(() -> new LinkedHashSet<>());

    public RpcConsumer targetHostPort(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    public RpcConsumer interfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    public RpcConsumer timeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }


    public Object newProxy() {
        return Reflection.newProxy(interfaceClass, this);
    }


    public <T extends ResponseCallbackListener> void asynCall(String methodName, T callbackListener) {
        asyncMethod.get().add(methodName);


    }


    @Override
    protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {

        Object retVal = null;
        RpcRequest rpcRequest = new RpcRequest(method.getName(), method.getParameterTypes(), args, RpcContext.getAttributes());
        Object resp;

        try {
            resp = doInvoker(rpcRequest);

            if (resp instanceof RpcResponse) {
                RpcResponse rpcResp = (RpcResponse) resp;
                if (rpcResp.isError()) {
                    throw new Throwable(rpcResp.getErrorMsg());
                }
                retVal = rpcResp.getResponseBody();
            }
        } catch (Exception e) {
            throw e;
        }
        return retVal;
    }

    private Object doInvoker(RpcRequest rpcRequest) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);

        Object retVal;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(rpcRequest);
            retVal = in.readObject();
        } finally {
            out.close();
            in.close();
            socket.close();
        }
        return retVal;
    }


}
