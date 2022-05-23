package com.example.myappsnowpath.Model;

import java.util.HashMap;

public class Order {
    String user_name;
    String user_address;
    String user_email;
    String user_phone;
    String id;
    String price;

    HashMap<String,String> orderMap;

    public Order(String id,String user_name, String user_address, String user_email, String user_phone
            , HashMap<String,String> orderMap, String price) {
        this.user_name = user_name;
        this.id = id;
        this.user_address = user_address;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.orderMap = orderMap;
        this.price = price;
    }

    public String getOrder_id() {
        return id;
    }

    public String getOrder_price() {
        return price;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public HashMap<String, String> getProducts() {
        return orderMap;
    }
}
