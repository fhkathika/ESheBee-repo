package com.example.eshebee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference2;


    Product_List[] myList = new Product_List[0];
    public ListView listitem, listitem_cat, listitem_sp;
    final String Tag = "MainActivity";
    Context context;
    Product_List productlist;
    Product_Catagory_list productCatagoryList;
    product_Specification_list product_specification_list;

    ArrayList<String> myProductArrayList = new ArrayList<String>();


    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
   List<Product_List> posts_Pro = new ArrayList<>();
    List<Product_Catagory_list> posts_Cat = new ArrayList<>();
    List<product_Specification_list> posts_Sp = new ArrayList<>();
    Set set = new HashSet();
    private Button product_name, product_catagory, product_specification,
            product_name_cancel, product_name_add, product_category_slect, product_name_select,
            product_category_add, product_category_cancel, product_specification_slect,
            product_specifiaction_add, product_specifiaction_cancel, productDetailsave, next;
    private EditText productType, productSubType, productName;
    public AlertDialog.Builder ProductName_DialogeBox, product_name_add_box, ProductCategory_DialogeBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference(" insertproduct");
        databaseReference2 = FirebaseDatabase.getInstance().getReference(" insertproduct");

        context = MainActivity.this;
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        product_name = findViewById(R.id.productName);
        product_catagory = findViewById(R.id.productCatagory);
        product_specification = findViewById(R.id.product_Specifiaction);
        productType = findViewById(R.id.pType);
        productSubType = findViewById(R.id.pSubType);
        productName = findViewById(R.id.proN);
        productDetailsave = findViewById(R.id.productDetailSave);
        next = findViewById(R.id.nextpage);

        product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductName_DialogeBox = new AlertDialog.Builder(MainActivity.this);
                ProductName_DialogeBox.setCancelable(true);

                View mView = getLayoutInflater().inflate(R.layout.product_name_dialogbox, null);
                listitem = mView.findViewById(R.id.list);
//        arrayList.removeAll(arrayList);
                listitem.setAdapter(adapter);
                Log.i(Tag, "inside api listsize " + arrayList.size());
                final AlertDialog alert = ProductName_DialogeBox.create();
                alert.setView(mView);
                product_name_add = mView.findViewById(R.id.product_add);
                product_name_select = mView.findViewById(R.id.product_select);
                product_name_cancel = mView.findViewById(R.id.product_cancel);
                product_name_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        product_name_add_box = new AlertDialog.Builder(MainActivity.this);
                        product_name_add_box.setCancelable(true);

                        View mView2 = getLayoutInflater().inflate(R.layout.product_add_dialogbox, null);
                        Button save = mView2.findViewById(R.id.userProductNameSave);
                        Button cancelD2 = mView2.findViewById(R.id.userProductNameCancel);
                        final EditText editproduct_name = mView2.findViewById(R.id.userAddProduct);
                        final AlertDialog alert2 = product_name_add_box.create();
                        alert2.setView(mView2);
                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String product_name = editproduct_name.getText().toString();
                                if (TextUtils.isEmpty(product_name)) {
                                    editproduct_name.setError("Please input Product Name");
                                    return;
                                }
                                databaseReference2.push().setValue(product_name);
                                arrayList.clear();

                                alert2.dismiss();
                                alert.dismiss();


                            }
                        });

                        databaseReference2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                String string=   dataSnapshot.getValue(String.class);
                                arrayList.add(string);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                                String string=   dataSnapshot.getValue(String.class);
                                arrayList.remove(string);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        cancelD2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert2.dismiss();
                            }
                        });

                        alert2.show();
                    }
                });

                product_name_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();

                    }
                });
                alert.show();
            }
        });

        product_specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Gson gson_sp = new GsonBuilder()
                        .setLenient()
                        .create();
                final Retrofit retrofit_sp = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.106/")
                        .addConverterFactory(GsonConverterFactory.create(gson_sp))
                        .build();
                final product_Interface Sp_Interface = retrofit_sp.create(product_Interface.class);
                Call<List<product_Specification_list>> call_sp = Sp_Interface.getProductspecification();
                call_sp.enqueue(new Callback<List<product_Specification_list>>() {
                    @Override
                    public void onResponse(Call<List<product_Specification_list>> call, Response<List<product_Specification_list>> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        Log.i(Tag, "body : " + response.body());
                        posts_Sp = response.body();

                        ProductCategory_DialogeBox = new AlertDialog.Builder(MainActivity.this);
                        ProductCategory_DialogeBox.setCancelable(true);

                        View mViewSp = getLayoutInflater().inflate(R.layout.product_specification_dialogbox, null);
                        listitem_sp = mViewSp.findViewById(R.id.sp_list);
                        Log.i(Tag, "inside api listsize " + posts_Sp.size());
                        ProductSpecificationList_Adapter productSpecificationListAdapter = new ProductSpecificationList_Adapter(posts_Sp, context);
                        listitem_sp.setAdapter(productSpecificationListAdapter);
                        listitem_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int sp, long l) {
                                final product_Specification_list SpValue = posts_Sp.get(sp);
                                Toast.makeText(MainActivity.this, "cicked" + SpValue.getProduct_specification(), Toast.LENGTH_SHORT).show();
                                Intent intentS = new Intent(getApplicationContext(), MainActivity.class);
                                intentS.putExtra("a", SpValue);
                                startActivity(intentS);
                            }
                        });

                        final AlertDialog alert = ProductCategory_DialogeBox.create();
                        alert.setView(mViewSp);

                        product_specifiaction_add = mViewSp.findViewById(R.id.product_sp_add);
                        product_specifiaction_cancel = mViewSp.findViewById(R.id.product_sp_cancel);
                        product_specification_slect = mViewSp.findViewById(R.id.product_sp_select);
                        product_specifiaction_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                product_name_add_box = new AlertDialog.Builder(MainActivity.this);
                                product_name_add_box.setCancelable(true);

                                View mViewSpAdd = getLayoutInflater().inflate(R.layout.product_sp_add_dialogbox, null);
                                Button save_sp = mViewSpAdd.findViewById(R.id.userProductSpSave);
                                final Button cancel_sp = mViewSpAdd.findViewById(R.id.userProductSpCancel);
                                final EditText editproduct_sp = mViewSpAdd.findViewById(R.id.userAddProductspecification);
                                final AlertDialog alert2 = product_name_add_box.create();
                                alert2.setView(mViewSpAdd);
                                alert2.show();
                                save_sp.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String product_specification = editproduct_sp.getText().toString();
                                        if (TextUtils.isEmpty(product_specification)) {
                                            editproduct_sp.setError("Please input Product specification");
                                            return;
                                        }

                                        final Gson gson_add_sp = new GsonBuilder()
                                                .setLenient()
                                                .create();

                                        Retrofit retrofit_add_sp = new Retrofit.Builder()
                                                .baseUrl("http://192.168.0.106/")
                                                .addConverterFactory(GsonConverterFactory.create(gson_add_sp))
                                                .build();
                                        product_Interface productInterfaceSp = retrofit_add_sp.create(product_Interface.class);
                                        final Call<String> addProductSpecification = productInterfaceSp.addProductSpecification(product_specification, "");
                                        addProductSpecification.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                if (!response.isSuccessful()) {
                                                    return;
                                                }
                                                String res_sp = response.body();
                                                Toast.makeText(MainActivity.this, "product added " + res_sp, Toast.LENGTH_SHORT).show();

                                            }


                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {

                                            }
                                        });
                                        alert2.dismiss();
                                        alert.dismiss();


                                    }
                                });

                                cancel_sp.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alert2.dismiss();
                                    }
                                });
                                alert.show();
                            }
                        });
                        product_specifiaction_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert.dismiss();
                            }
                        });
                        alert.show();

                    }

                    @Override
                    public void onFailure(Call<List<product_Specification_list>> call, Throwable t) {

                    }
                });


            }
        });


        product_catagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Gson gson_cat = new GsonBuilder()
                        .setLenient()
                        .create();
                final Retrofit retrofit_cat = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.106/")
                        .addConverterFactory(GsonConverterFactory.create(gson_cat))
                        .build();
                final product_Interface Interface = retrofit_cat.create(product_Interface.class);
                Call<List<Product_Catagory_list>> call_cat = Interface.getProductcategory();
                call_cat.enqueue(new Callback<List<Product_Catagory_list>>() {
                    @Override
                    public void onResponse(Call<List<Product_Catagory_list>> call, Response<List<Product_Catagory_list>> response2) {
                        if (!response2.isSuccessful()) {
                            return;
                        }
                        Log.i(Tag, "body : " + response2.body());
                        posts_Cat = response2.body();

                        ProductCategory_DialogeBox = new AlertDialog.Builder(MainActivity.this);
                        ProductCategory_DialogeBox.setCancelable(true);

                        View mView2 = getLayoutInflater().inflate(R.layout.product_category_dialogbox, null);
                        listitem_cat = mView2.findViewById(R.id.cat_list);

                        Log.i(Tag, "inside api listsize " + posts_Cat.size());
                        ProductCatagoryList_Adapter productCatagoryList_adapter = new ProductCatagoryList_Adapter(posts_Cat, context);
                        listitem_cat.setAdapter(productCatagoryList_adapter);

                        listitem_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int c, long l) {
                                final Product_Catagory_list CatValue = posts_Cat.get(c);
                                Toast.makeText(MainActivity.this, "cicked" + CatValue.getProduct_category(), Toast.LENGTH_SHORT).show();
                                Intent intentC = new Intent(getApplicationContext(), MainActivity.class);
                                intentC.putExtra("b", CatValue);
                                startActivity(intentC);
                                posts_Cat.clear();
                                posts_Cat.addAll(set);
                                listitem_cat.refreshDrawableState();
                            }
                        });

                        final AlertDialog alert = ProductCategory_DialogeBox.create();
                        alert.setView(mView2);

                        product_category_add = mView2.findViewById(R.id.product_cat_add);
                        product_category_slect = mView2.findViewById(R.id.product_cat_select);
                        product_category_cancel = mView2.findViewById(R.id.product_cat_cancel);

                        product_category_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                product_name_add_box = new AlertDialog.Builder(MainActivity.this);
                                product_name_add_box.setCancelable(true);

                                View mViewadd = getLayoutInflater().inflate(R.layout.product_cat_add_dialogbox, null);
                                Button save_cat = mViewadd.findViewById(R.id.userProductCatSave);
                                final Button cancel_cat = mViewadd.findViewById(R.id.userProductCatCancel);
                                final EditText editproduct_category = mViewadd.findViewById(R.id.userAddProductcatagory);
                                final AlertDialog alert2 = product_name_add_box.create();
                                alert2.setView(mViewadd);
                                alert2.show();
                                save_cat.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String product_category = editproduct_category.getText().toString();
                                        if (TextUtils.isEmpty(product_category)) {
                                            editproduct_category.setError("Please input Product category");
                                            return;
                                        }

                                        final Gson gson_add_cat = new GsonBuilder()
                                                .setLenient()
                                                .create();

                                        Retrofit retrofit_add_cat = new Retrofit.Builder()
                                                .baseUrl("http://192.168.0.106/")
                                                .addConverterFactory(GsonConverterFactory.create(gson_add_cat))
                                                .build();
                                        product_Interface productInterfaceCat = retrofit_add_cat.create(product_Interface.class);
                                        final Call<String> addProductcategory = productInterfaceCat.addProductcategory(product_category, "");
                                        addProductcategory.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                if (!response.isSuccessful()) {
                                                    return;
                                                }
                                                String res_cat = response.body();
                                                Toast.makeText(MainActivity.this, "product added " + res_cat, Toast.LENGTH_SHORT).show();

                                            }


                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {

                                            }
                                        });
                                        alert2.dismiss();
                                        alert.dismiss();


                                    }
                                });

                                cancel_cat.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alert2.dismiss();
                                    }
                                });
                                alert.show();

                            }

                        });
                        product_category_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                alert.dismiss();
                            }
                        });
                        alert.show();
                    }

                    @Override
                    public void onFailure(Call<List<Product_Catagory_list>> call, Throwable t) {

                    }
                });
            }
        });

        if (getIntent().getExtras() != null) {
            productlist = (Product_List) getIntent().getSerializableExtra("n");
            product_specification_list = (product_Specification_list) getIntent().getSerializableExtra("a");
            productCatagoryList = (Product_Catagory_list) getIntent().getSerializableExtra("b");
            if (productlist != null) {
                productType.setText(productlist.getProduct_name());


            } else if (productCatagoryList != null) {
                productSubType.setText(productCatagoryList.getProduct_category());
            } else if (product_specification_list != null) {

                productName.setText(product_specification_list.getProduct_specification());
            }
        }


        productDetailsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_category = productType.getText().toString();
                String product_sub_category = productSubType.getText().toString();
                String product_name = productName.getText().toString();
                if (TextUtils.isEmpty(product_category)) {
                    productType.setError("Please input Product Category");
                    return;
                }
                if (TextUtils.isEmpty(product_sub_category)) {
                    productSubType.setError("Please input Product sub category");
                    return;
                }
                if (TextUtils.isEmpty(product_name)) {
                    productName.setError("Please input Product Name");
                    return;
                }
                String key = databaseReference.push().getKey();
                userInserData inserData = new userInserData(product_category, product_sub_category, product_name);
                databaseReference.child(key).setValue(inserData);
                Toast.makeText(context, "product detail is added", Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Product_part_Activity.class);
                startActivity(intent);
            }
        });
    }


    public void insertData() {



    }




public void onBackPressed(){
        super.onBackPressed();
    Intent intent=new Intent(context,MainActivity.class);
    startActivity(intent);
    finish();
}
}
