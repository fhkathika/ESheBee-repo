package com.example.eshebee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listview_Adapter extends ArrayAdapter<Partselection> {

    public String Tag = "List_Adapter";
    Context mContext;

    private List<Partselection> ArrayList;


    public listview_Adapter(List<Partselection> Arraylist, Context context) {
        super(context, R.layout.product_name_list_sample, Arraylist);
        this.ArrayList = Arraylist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View ListViewitem = inflater.inflate(R.layout.product_name_list_sample, null, true);
        TextView listitem = ListViewitem.findViewById(R.id.pName);
        Partselection partselection = ArrayList.get(position);

        listitem.setText(partselection.getName());
//        listitem.setText(partselection.getna());
        return  ListViewitem;
    }



}
