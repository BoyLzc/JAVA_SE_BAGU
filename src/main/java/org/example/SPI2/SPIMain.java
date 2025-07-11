package org.example.SPI2;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        // 使用 ServiceLoader 加载配置文件中的实现类
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
