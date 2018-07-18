import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-18 17:10
 * @Version: 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {

        People p = new Student();
        InvocationHandler handler = new PeoleInvocationHandler(p);
        People proxy = Reflection.newProxy(People.class, handler);

        proxy.say("dennis $$$$");
        proxy.eat();
    }

}

interface People {
    String say(String name);


    void eat();
}


class Student implements People {
    @Override
    public String say(String name) {
        System.out.println("execute say");

        return "hello " + name;
    }

    @Override
    public void eat() {
        System.out.println("call eat");
    }
}

class PeoleInvocationHandler extends AbstractInvocationHandler {

    private People people;

    public PeoleInvocationHandler(People people) {
        this.people = people;
    }

    @Override
    protected Object handleInvocation(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before call");
        Object invoke = method.invoke(people, objects);
        if (!Objects.isNull(invoke)) {
            System.out.println(invoke.toString());
        }
        return invoke;
    }

}

