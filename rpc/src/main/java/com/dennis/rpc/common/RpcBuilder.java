package com.dennis.rpc.common;

import com.dennis.rpc.RpcContext;
import com.dennis.rpc.model.RpcRequest;
import com.dennis.rpc.model.RpcResponse;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 18:09
 * @Version: 1.0
 */
public final class RpcBuilder {


    private static int nThread = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService pool = Executors.newFixedThreadPool(nThread);


    public static Object buildRpcClient(final Class<?> interfaces, final String host, final int port) {
        if (null == interfaces) {
            throw new IllegalArgumentException("interfaces can not be null!");
        }

        Object proxy = Reflection.newProxy(interfaces, new AbstractInvocationHandler() {
            @Override
            protected Object handleInvocation(Object o, Method method, Object[] args) throws Throwable {

                Socket socket = new Socket(host, port);
                Object retVal = null;
                ObjectOutputStream out = null;
                ObjectInputStream in = null;

                try {
                    out = new ObjectOutputStream(socket.getOutputStream());
                    in = new ObjectInputStream(socket.getInputStream());

                    RpcRequest request = new RpcRequest(method.getName(), method.getParameterTypes(), args, RpcContext.getAttributes());

                    //发送请求
                    out.writeObject(request);

                    //接受server的响应
                    Object resp = in.readObject();

                    if (resp != null && resp instanceof RpcResponse) {
                        RpcResponse rpcResp = (RpcResponse) resp;
                        if (rpcResp.isError()) {
                            return new Throwable(rpcResp.getErrorMsg());
                        }
                        retVal = rpcResp.getResponseBody();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                    in.close();
                    socket.close();
                }
                return retVal;
            }
        });
        return proxy;
    }


    public static void buildRpcServer(final Object service, final int port) throws IOException {
        if (null == service) {
            throw new IllegalArgumentException("service can not be null");
        }

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server started");

        for (; ; ) {
            Socket socket = serverSocket.accept();
            pool.submit(() -> execute(service, socket));
        }
    }

    private static void execute(Object service, Socket socket) {
        try {
            ObjectInputStream in = null;
            ObjectOutputStream out = null;

            RpcResponse resp = new RpcResponse();
            try {
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());


                Object req = in.readObject();

                if (null != req && req instanceof RpcRequest) {
                    RpcRequest rpcReq = (RpcRequest) req;
                    Method method = service.getClass().getMethod(rpcReq.getMethodName(), rpcReq.getParameterTypes());
                    Object retVal = method.invoke(service, rpcReq.getArgs());
                    resp.setResponseBody(retVal);
                    out.writeObject(resp);
                } else {
                    throw new IllegalArgumentException("bad request");
                }

            } catch (Exception e) {
                resp.setErrorMsg(e.getMessage());
                resp.setResponseBody(e);
                out.writeObject(resp);

            } finally {
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
