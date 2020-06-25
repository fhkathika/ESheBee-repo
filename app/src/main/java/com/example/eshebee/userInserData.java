package com.example.eshebee;

public class userInserData {
    String product_category;
    String product_sub_category;
    String product_name;

    public userInserData(String product_category, String product_sub_category, String product_name) {
        this.product_category = product_category;
        this.product_sub_category = product_sub_category;
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_sub_category() {
        return product_sub_category;
    }

    public void setProduct_sub_category(String product_sub_category) {
        this.product_sub_category = product_sub_category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
