package com.yoyogrape.po;

import java.io.Serializable;

/**
 * @author songjn@yoyogrape.com
 * @date 2018/5/29 0029
 */
public class MailObj implements Serializable {
    private String conpanyName;
    private String conpanyCustomer;
    private String phoneNum;
    private String[] orderProducts;

    public String[] getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(String[] orderProducts) {
        this.orderProducts = orderProducts;
    }


    public String getConpanyName() {
        return conpanyName;
    }

    public void setConpanyName(String conpanyName) {
        this.conpanyName = conpanyName;
    }

    public String getConpanyCustomer() {
        return conpanyCustomer;
    }

    public void setConpanyCustomer(String conpanyCustomer) {
        this.conpanyCustomer = conpanyCustomer;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


}
