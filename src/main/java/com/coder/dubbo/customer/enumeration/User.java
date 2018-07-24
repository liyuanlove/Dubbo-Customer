package com.coder.dubbo.customer.enumeration;

public enum  User {
    ISDELETE(-1,"您的账户已被删除"),
    ISLOCK(-2,"您的账户已被冻结！"),
    ISLOGIN(-3,"您已经在其他地方登录，请重新登录！");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private User(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
