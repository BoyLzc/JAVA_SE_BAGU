package org.example.Serializable;

import java.io.*;
import java.util.Date;

public class SerializableDemo {
    // 序列化就是把 java对象序列化为字节数组的过程，反序列化就是把字节数组转化为java对象
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Initializes The Object
        User user = new User();
        user.setName("Hollis");
        user.setAge(18);
        user.setGender("Male");
        user.setBirthday(new Date());
        System.out.println(user);

        // write Obj to File
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(user);
        // Read Obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User user1 = (User) ois.readObject();
        System.out.println(user1);
//        IOUtils.closeQuietly(ois);
    }
}
