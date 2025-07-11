package org.example.SPI;

public class Main {
    public static void main(String[] args) {
        LoggerService service = LoggerService.getService();
        service.info("Hello SPI info");
        service.debug("Hello SPI debug");
    }
}
