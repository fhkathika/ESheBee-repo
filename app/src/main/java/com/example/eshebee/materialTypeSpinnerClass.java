package com.example.eshebee;

public class materialTypeSpinnerClass {
    String materialTypeList;
    public materialTypeSpinnerClass(){

    }

    public materialTypeSpinnerClass(String materialTypeList) {
        this.materialTypeList = materialTypeList;
    }

    public String getMaterialTypeList() {

        return materialTypeList;
    }

    public void setMaterialTypeList(String materialTypeList) {
        this.materialTypeList = materialTypeList;
    }
}
