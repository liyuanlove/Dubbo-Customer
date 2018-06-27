package com.coder.dubbo.customer.enumeration;

public enum State {
    SUCCESS(1),
    FAIL(2);

    private int value;

    private State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
