package com.example.eshebee;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pro_Sp {
    List<product_Specification_list> posts_Sp=new ArrayList<>();
    public AlertDialog.Builder ProductSpName_DialogeBox;

    final Gson gson_sp = new GsonBuilder()
            .setLenient()
            .create();
    final Retrofit retrofit_sp = new Retrofit.Builder()
            .baseUrl("http://192.168.0.106/")
            .addConverterFactory(GsonConverterFactory.create(gson_sp))
            .build();
    final product_Interface Sp_Interface = retrofit_sp.create(product_Interface.class);
    Call<List<product_Specification_list>> call_sp = Sp_Interface.getProductspecification();




}
