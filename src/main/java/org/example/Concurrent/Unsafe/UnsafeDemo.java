package org.example.Concurrent.Unsafe;

import sun.misc.Unsafe;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class UnsafeDemo {
    // Unsafe 提供硬件级别的原子操作
    // 1、可以分配内存也可以释放内存
    // 2、可以定位某字段的内存位置、修改对象的字段值，即使其是私有的
    // 3、将线程进行挂起与恢复
    // 4、CAS操作
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 可以通过反射获取 Unsafe 实例
        Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafeField.get(null);
        System.out.println(unsafe);
        
        // 分配堆外内存，返回内存地址
        long size = 1024; // 内存大小
        // 分配内存
        long address = unsafe.allocateMemory(size);
        System.out.println(address);
        // 写数据到堆外内存
        String dataToWrite = "hello world";
        byte[] dataBytes = dataToWrite.getBytes();
        for (int i = 0; i < dataBytes.length; i++) {
            // 将 dataToWrite 的第 i 个字符写入到 address + i 的位置
            unsafe.putByte(address + i, dataBytes[i]);
//            System.out.println(address + i);
        }
        // 堆外内存读取数据
        byte[] dataToRead = new byte[dataBytes.length];
        for (int i = 0; i < dataToRead.length; i++) {
            // 将 address + i 的位置的数据读到 bytes[i]
            dataToRead[i] = unsafe.getByte(address + i);
        }
        System.out.println(new String(dataToRead));
        // 释放堆外内存
        unsafe.freeMemory(address);
    }
}
