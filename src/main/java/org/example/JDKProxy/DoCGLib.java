package org.example.JDKProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DoCGLib {
    public static void main(String[] args) {
        // cglib 动态代理
/*        CglibProxy cglibProxy = new CglibProxy();
        UserServiceImpl userService = (UserServiceImpl)
                cglibProxy.getProxy(UserServiceImpl.class);
        userService.add();*/
        // jdk 动态代理
        UserServiceImpl userService = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) myInvocationHandler.getProxy();
        proxy.add();
        // 动态代理失效的场景
    }
}
