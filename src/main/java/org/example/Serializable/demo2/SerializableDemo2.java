package org.example.Serializable.demo2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class SerializableDemo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File tempFile = new File("tempFile");

        ObjectInputStream ois = new ObjectInputStream
                (new FileInputStream(tempFile));

        User object = (User) ois.readObject();
        System.out.println(object);
        IOUtils.closeQuietly(ois);
    }
}
