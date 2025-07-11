package org.example.proxy.CGLIB;

import net.sf.cglib.proxy.MethodInterceptor;

public class DebugMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, java.lang.reflect.Method method, Object[] objects, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
        /**
         * @param o 被代理的对象
         * @param method 被拦截的方法
         * @param objects 方法入参
         * @param methodProxy 用于调用原始方法
         */
        // 调用方法之前 添加操作
        System.out.println("Before method " + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        // 调用方法之后，添加操作
        System.out.println("After method " + method.getName());
        return object;
    }
}
