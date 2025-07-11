package org.example.Copy;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.BeanUtils;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("张三", 18);
        user.setAddress(new Address("上海"));

/*        User user2 = new User();
        // BeanUtils 是一种浅拷贝
        BeanUtils.copyProperties(user, user2);
        System.out.println(JSON.toJSONString(user2));
        System.out.println(JSON.toJSONString(user));
        System.out.println(user2 == user); // 创建新对象
        user.setAge(100);
        System.out.println(user2.getAddress() == user.getAddress()); // 但是内部属性字段是同一个引用
        user2.getAddress().setCity("北京"); // 改变其引用指向的字段值，即新旧对象一起改变
        System.out.println(JSON.toJSONString(user2));
        System.out.println(JSON.toJSONString(user));
        System.out.println("****************************");*/
        // clone 也是一种浅拷贝
        User user3 = new User("张三", 20);
        user3.setAddress(new Address("上海"));
        User user4 = (User) user3.clone();
/*        System.out.println(user3 == user4);
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user4));
        user3.getAddress().setCity("武汉"); // 浅拷贝
        user3.setAge(30);
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user4));*/
        // 一般需要重写 clone函数 以实现深拷贝
/*        System.out.println(user3 == user4);
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user4));
        System.out.println(user3.getAddress() == user4.getAddress());
        user3.getAddress().setCity("武汉");
        user3.setAge(30);
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user4));
        System.out.println("****************************");*/
        // 使用json工具实现深拷贝 FastJSON 将对象序列化为 json字符串 然后再将字符串反序列化为对象
        User user6 = JSON.parseObject(JSON.toJSONString(user3), User.class);
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user6));
        user6.getAddress().setCity("西安");
        System.out.println(JSON.toJSONString(user3));
        System.out.println(JSON.toJSONString(user6));
        System.out.println("****************************");
        // Serializable 实现深拷贝
//        User user7 = (User) SerializationUtils.clone(User.class);

    }
}
