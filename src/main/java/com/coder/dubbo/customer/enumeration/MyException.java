package com.coder.dubbo.customer.enumeration;

public enum MyException {

    TOKEN(-1,"token错误"),
    AUTHORIZATION(-2,"用户无权限"),
    OTHER(-3,"其它未知错误：");



    private int code;
    private String msg;

    private MyException(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

}
