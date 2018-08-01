package com.xiong.entity;

/**
 * @author: xiong
 * @time: 2018/07/09
 * @说明:
 */
public class XBean {

    // 类型
    private int type;
    // 名字
    private String name;
    // 账号
    private String account;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public XBean(int type, String name, String account) {
        this.type = type;
        this.name = name;
        this.account = account;
    }


    public static XBean newIntance(int type, String name, String account){
        return new XBean(type, name,  account);
    }
}
