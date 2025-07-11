package org.example.proxy.JDK;

public class Main {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImplTwo();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("hello");
    }
}
