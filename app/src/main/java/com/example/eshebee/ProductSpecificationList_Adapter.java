package com.example.eshebee;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductSpecificationList_Adapter extends  ArrayAdapter<product_Specification_list> {

    public  String Tag="ProductList_Adapter";
    Context mContext;

    private  List<product_Specification_list> myProductArrayList;

    private  static  class ViewHolder{

        TextView s_name;

//        ImageView image;
    }

    public ProductSpecificationList_Adapter(List<product_Specification_list> myProductArrayList, Context context) {

        super(context, R.layout.product_name_list_sample, myProductArrayList);

        this.myProductArrayList = myProductArrayList;
        this.mContext=context;
    }
    public product_Specification_list getItem(int position) {
        return myProductArrayList.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final product_Specification_list productS=getItem(position);
        final ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_name_list_sample, parent, false);
            viewHolder.s_name = convertView.findViewById(R.id.pName);

//            viewHolder.image = convertView.findViewById(R.id.logo);


            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.s_name.setText(productS.getProduct_specification());

//        viewHolder.image.setImageResource(product.getLogo_image());

        // Return the completed view to render on screen
        return convertView;
    }
}
