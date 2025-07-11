package org.example.SPI;

// 服务提供者的结构 服务提供者需要针对这个接口进行实现
public interface Logger {
    void info(String msg);
    void debug(String msg);
}
