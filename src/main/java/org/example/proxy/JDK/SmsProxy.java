package org.example.proxy.JDK;

public class SmsProxy implements SmsService {
    private final SmsService smsService;
    // 构造方法
    public SmsProxy(SmsService smsService)
    {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        // 调用方法之前
        System.out.println("before");
        smsService.send(message);
        // 调用方法之后
        System.out.println("after");
        return null;
    }
}
