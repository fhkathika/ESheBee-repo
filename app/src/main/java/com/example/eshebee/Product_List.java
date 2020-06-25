package com.example.eshebee;

import java.io.Serializable;

public class Product_List implements Serializable {

 private String product_name;
// private int logo_image;


//    public Product_List( String product_name) {
//
//        this.product_name = product_name;
//    }



    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


//    public int getLogo_image() {
//        return logo_image;
//    }
//
//    public void setLogo_image(int logo_image) {
//        this.logo_image = logo_image;
//    }
}
