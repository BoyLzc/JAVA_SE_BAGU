package org.example.Serializable.demo2;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableDemo1 {
    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("Hollis");

        ObjectOutputStream oos = new ObjectOutputStream
                (new FileOutputStream("tempFile"));
        oos.writeObject(user);
        // 安全关闭资源 == 以下代码
        IOUtils.closeQuietly(oos);
//        try {
//            if(oos != null) {
//                oos.close();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
