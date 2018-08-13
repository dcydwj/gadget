package com.dennis.rpc.server;

import com.dennis.rpc.RpcContext;
import com.dennis.rpc.model.RpcRequest;
import com.dennis.rpc.model.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 22:05
 * @Version: 1.0
 */
public class RpcProvider {
    private static int nThread = Runtime.getRuntime().availableProcessors();
    private static ExecutorService pool = Executors.newFixedThreadPool(nThread);

    public static void publish(final Object service, final int port) throws IOException {
        if (null == service) {
            throw new IllegalArgumentException("service can not be null");
        }

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server started");

        for (; ; ) {
            Socket accept = serverSocket.accept();
            //异步处理
            Future<?> submit = pool.submit(() -> process(service, accept));
        }
    }

    private static void process(Object service, Socket accept) {

        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        RpcResponse rpcResponse = new RpcResponse();
        try {
            try {
                in = new ObjectInputStream(accept.getInputStream());
                out = new ObjectOutputStream(accept.getOutputStream());

                Object req = in.readObject();

                if (req instanceof RpcRequest) {
                    RpcRequest rpcRequest = (RpcRequest) req;
                    RpcContext.context.set(rpcRequest.getContext());
                    Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());

                    Object retVal = method.invoke(service, rpcRequest.getArgs());
                    rpcResponse.setResponseBody(retVal);
                    out.writeObject(rpcResponse);

                }
            } catch (Exception e) {
                rpcResponse.setResponseBody(e);
                rpcResponse.setErrorMsg(e.getMessage());
                out.writeObject(rpcResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
