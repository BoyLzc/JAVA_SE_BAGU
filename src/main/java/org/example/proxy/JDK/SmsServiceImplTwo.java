package org.example.proxy.JDK;

public class SmsServiceImplTwo implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("send message Two:" + message);
        return message;    }
}
