package org.example.JDKProxy;

// CgLib无需代理类无需实现接口
// JDK 需要实现接口
public class UserServiceImpl implements UserService{
    public void add(){
        System.out.println("add");
    }
    // 自调用方法，无法被AOP
    public void add2(){
        add();
    }
    // 私有方法
    private void getInfo(){
        System.out.println("我是私有方法 getInfo");
    }
    // 自调用私有方法，无法被AOP
    public void printInfo(){
        System.out.println("调用私有方法 printInfo->getInfo");
        getInfo();
    }
    // final方法无法被代理、无法被覆盖，无法被AOP
    public final void show(){
        System.out.println("我是final方法");
    }
    // static方法属于类、不属于对象,无法被AOP
    public static void show2(){
        System.out.println("我是static方法");
    }
}
