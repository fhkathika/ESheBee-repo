package com.example.eshebee;

public class Material {
    String codenumbr;
    String material_type;
    String Suta;
    String Bohor;
    String GOjMullo;
    String Flactuation;
    String Color;
    String Brand;
    String waste;
    String radiobtn1textview;
    String rtadiobtn2textview;

    public Material(String m) {

    }

    public Material(String codenumbr, String material_type, String suta, String bohor, String GOjMullo, String flactuation, String color, String brand, String waste, String radiobtn1textview, String rtadiobtn2textview) {
        this.codenumbr = codenumbr;
        this.material_type = material_type;
        Suta = suta;
        Bohor = bohor;
        this.GOjMullo = GOjMullo;
        Flactuation = flactuation;
        Color = color;
        Brand = brand;
        this.waste = waste;
        this.radiobtn1textview = radiobtn1textview;
        this.rtadiobtn2textview = rtadiobtn2textview;
    }

    public String getCodenumbr() {
        return codenumbr;
    }

    public void setCodenumbr(String codenumbr) {
        this.codenumbr = codenumbr;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getSuta() {
        return Suta;
    }

    public void setSuta(String suta) {
        Suta = suta;
    }

    public String getBohor() {
        return Bohor;
    }

    public void setBohor(String bohor) {
        Bohor = bohor;
    }

    public String getGOjMullo() {
        return GOjMullo;
    }

    public void setGOjMullo(String GOjMullo) {
        this.GOjMullo = GOjMullo;
    }

    public String getFlactuation() {
        return Flactuation;
    }

    public void setFlactuation(String flactuation) {
        Flactuation = flactuation;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getRadiobtn1textview() {
        return radiobtn1textview;
    }

    public void setRadiobtn1textview(String radiobtn1textview) {
        this.radiobtn1textview = radiobtn1textview;
    }

    public String getRtadiobtn2textview() {
        return rtadiobtn2textview;
    }

    public void setRtadiobtn2textview(String rtadiobtn2textview) {
        this.rtadiobtn2textview = rtadiobtn2textview;
    }
}
