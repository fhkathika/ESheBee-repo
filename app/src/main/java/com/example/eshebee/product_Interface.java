package com.example.eshebee;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface product_Interface {

    ///product name

    @GET("phpFolder/esheBee/productName.php")
    Call<List<Product_List>> getProduct();

    @POST("phpFolder/esheBee/productNameInsert.php")
    Call<String> addProduct(@Query("product_name") String product_name,
                            @Query("id") String id);

    ////product category

    @GET("phpFolder/esheBee/productCategory.php")
    Call<List<Product_Catagory_list>> getProductcategory();

    @POST("phpFolder/esheBee/productCategoryInsert.php")
    Call<String> addProductcategory(@Query("product_category") String product_category,
                            @Query("id") String id);


    ///productSpecfication

    @GET("phpFolder/esheBee/productSpecification.php")
    Call<List<product_Specification_list>> getProductspecification();

    @POST("phpFolder/esheBee/productSpecificationInsert.php")
    Call<String> addProductSpecification(@Query("product_specification") String product_specification,
                            @Query("id") String id);
    @POST(" phpFolder/esheBee/userDataInsert.php")
    Call<String> adduserdata(@Query("product_category") String product_category,
                                         @Query("product_sub_category") String  product_sub_category,
                                         @Query("product_name") String product_name,
                                         @Query("id") String id);

}
