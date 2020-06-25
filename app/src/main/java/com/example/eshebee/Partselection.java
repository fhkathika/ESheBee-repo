package com.example.eshebee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Partselection implements Serializable {

   public String name;
//    String  listitem;
   public  Partselection(){

    }

    public Partselection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
