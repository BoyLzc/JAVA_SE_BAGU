package org.example.Invocation;

public class TargetObject {
    private String value;
    private int number;

    public TargetObject() {
        value = "JavaGuide";
        number = 1234;
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
        System.out.println("number is " + number);
    }
}
