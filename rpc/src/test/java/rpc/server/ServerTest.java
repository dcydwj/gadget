package rpc.server;

import com.dennis.rpc.common.RpcBuilder;
import rpc.UserService;
import rpc.impl.UserServiceImpl;

import java.io.IOException;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 20:09
 * @Version: 1.0
 */
public class ServerTest {

    private static int port = 8888;

    public static void main(String[] args) throws IOException {
        UserService service = new UserServiceImpl();
        //暴露服务
        RpcBuilder.buildRpcServer(service, port);
    }
}
