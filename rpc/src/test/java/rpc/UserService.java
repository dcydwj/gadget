package rpc;

import rpc.model.User;

/**
 * @Description:
 * @Time: 2018-07-21 19:57
 * @Version: 1.0
 */
public interface UserService {
    String test();

    User queryUserById(Integer id);

    Object exceptionTest();
}
