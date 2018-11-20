package rpc.impl;

import com.dennis.rpc.model.RpcException;
import rpc.UserService;
import rpc.model.User;

/**
 * @Description:
 * @Time: 2018-07-21 20:02
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public String test() {
        return "hello client ,this is rpc Server";
    }

    @Override
    public User queryUserById(Integer id) {
        User parent = new User(100,"小明爸爸");
        User child = new User(101,"小明同学");
        parent.addChild(child);
        return parent;
    }

    @Override
    public Object exceptionTest() {
        throw new RpcException("exception occur in server！！！");
    }
}
