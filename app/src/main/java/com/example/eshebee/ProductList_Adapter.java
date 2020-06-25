package com.example.eshebee;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ProductList_Adapter extends  ArrayAdapter<Product_List> {

    public  String Tag="ProductList_Adapter";
    Context mContext;

    private  List<Product_List> myProductArrayList;

    private  static  class ViewHolder{

        TextView p_name;

//        ImageView image;
    }

    public ProductList_Adapter(List<Product_List> myProductArrayList, Context context) {

        super(context, R.layout.product_name_list_sample, myProductArrayList);

        this.myProductArrayList = myProductArrayList;
        this.mContext=context;
    }
    public Product_List getItem(int position) {
        return myProductArrayList.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Product_List product=getItem(position);
        final ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_name_list_sample, parent, false);
            viewHolder.p_name = convertView.findViewById(R.id.pName);

//            viewHolder.image = convertView.findViewById(R.id.logo);


            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.p_name.setText(product.getProduct_name());

//        viewHolder.image.setImageResource(product.getLogo_image());

        // Return the completed view to render on screen
        return convertView;
    }
}
