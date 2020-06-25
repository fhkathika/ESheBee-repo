package com.example.eshebee;

public class partEdit_insertData {
    String updatePart;
    String layer1Textview;
    String layer2Textview;
    String layer3Textview;
    public  partEdit_insertData(){

    }

    public partEdit_insertData(String updatePart, String layer1Textview, String layer2Textview, String  layer3Textview) {
        this.updatePart = updatePart;
        this.layer1Textview = layer1Textview;
        this.layer2Textview = layer2Textview;
        this.layer3Textview = layer3Textview;
    }

    public String getUpdtaepart() {
        return updatePart;
    }

    public void setUpdtaepart(String updtaepart) {
        this.updatePart = updtaepart;
    }

    public String getLayer1Textview() {
        return layer1Textview;
    }

    public void setLayer1Textview(String layer1Textview) {
        this.layer1Textview = layer1Textview;
    }

    public String getLayer2Textview() {
        return layer2Textview;
    }

    public void setLayer2Textview(String layer2Textview) {
        this.layer2Textview = layer2Textview;
    }

    public String getLayer3Textview() {
        return layer3Textview;
    }

    public void setLayer3Textview(String layer3Textview) {
        this.layer3Textview = layer3Textview;
    }
}
