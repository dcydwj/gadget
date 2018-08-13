package rpc.client;

import com.alibaba.fastjson.JSON;
import com.dennis.rpc.common.RpcBuilder;
import com.dennis.rpc.model.RpcException;
import rpc.UserService;
import rpc.model.User;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 20:11
 * @Version: 1.0
 */
public class ClientTest {

    private static String host = "127.0.0.1";
    private static int port = 8888;
    public static void main(String[] args) {

        UserService service = (UserService) RpcBuilder.buildRpcClient(UserService.class, host, port);

        Object msg = null;
        try {

//             msg = service.test();

            msg = service.queryUserById(0);

            if (msg instanceof User) {
                System.out.println(JSON.toJSONString(msg));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
