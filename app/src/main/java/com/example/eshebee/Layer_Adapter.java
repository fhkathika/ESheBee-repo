package com.example.eshebee;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Layer_Adapter extends ArrayAdapter<Layer> {

    public String Tag = "List_Adapter";

    Context mContext;
    Material material;
    DatabaseReference productMaterialdb;
    List<String> layerspinnerlist;

    private ArrayAdapter<String> layerspinneradapter;
    String code, mat_type, suta, gojmullo, brnd, clr, bhr, flc, wst;
    private List<Layer> layerList;

    //    productMaterialdb = FirebaseDatabase.getInstance().getReference(" Materials_Data");
    private static class ViewHolder {

        TextView Layername;
        Spinner spinner;
        ImageView layerSetting;

        String material_type;
    }

    public Layer_Adapter(List<Layer> layerList, Context context) {
        super(context, R.layout.layer_listview, layerList);
        this.layerList = layerList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View ListViewitem = inflater.inflate(R.layout.layer_listview, null, true);
        viewHolder.Layername = ListViewitem.findViewById(R.id.layertv);
        viewHolder.spinner = ListViewitem.findViewById(R.id.spinner_list);
        viewHolder.layerSetting = ListViewitem.findViewById(R.id.settings);

        productMaterialdb = FirebaseDatabase.getInstance().getReference(" Materials_Data");
        layerspinnerlist = new ArrayList<>();
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, layerspinnerlist);

        viewHolder.spinner.setAdapter(spinnerAdapter);

        productMaterialdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layerspinnerlist.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    final String material_type = item.child("material_type").getValue().toString();
                    layerspinnerlist.add(material_type);
                }
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String s = spinnerAdapter.getItem(i);
                viewHolder.layerSetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder partEditor_DialogeBox = new AlertDialog.Builder(mContext);
                        partEditor_DialogeBox.setCancelable(true);
                        View mView = LayoutInflater.from(mContext).inflate(R.layout.settings_editor_dialogbox2, null);

                        final AlertDialog alert = partEditor_DialogeBox.create();
                        alert.setView(mView);

//               String s= layerspinnerlist.get(i);

//                Toast.makeText(mContext, s+"item seletced", Toast.LENGTH_SHORT).show();

                        final EditText codenumbr = mView.findViewById(R.id.code_update);
                        final EditText material_type = mView.findViewById(R.id.material_update);
                        final EditText Suta = mView.findViewById(R.id.suta_update);
                        final EditText GOjMullo = mView.findViewById(R.id.gojmullo_update);
                        final EditText Brand = mView.findViewById(R.id.brand_update);
                        final EditText Color = mView.findViewById(R.id.color_update);
                        final EditText Bohor = mView.findViewById(R.id.bohor_update);
                        final TextView Flactuation = mView.findViewById(R.id.flactiation);
                        final TextView Waste = mView.findViewById(R.id.waste);
//                        final TextView codenumberText=mView.findViewById(R.id.t1);
                        final TextView materialText = mView.findViewById(R.id.t2_update);
                        final TextView sutaText = mView.findViewById(R.id.t3_update);
                        final TextView radiobtn1textview = mView.findViewById(R.id.t4_update);
                        final TextView rtadiobtn2textview = mView.findViewById(R.id.t5_update);
//                        Button savematerial=mView.findViewById(R.id.save);
                        Button updatematerial = mView.findViewById(R.id.update_materialData);
                        final RadioGroup radioGroup = mView.findViewById(R.id.radiogroup);
                        final RadioGroup radioGroup2 = mView.findViewById(R.id.radiogroup2);


                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                int id = radioGroup.getCheckedRadioButtonId();
                                View radioButton = radioGroup.findViewById(id);
                                if (radioButton.getId() == R.id.radio_id1) {
                                    radiobtn1textview.setText("ডাইং");
                                } else {
                                    radiobtn1textview.setText("");
                                }
                            }
                        });

                        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup2, int i) {
                                int id2 = radioGroup2.getCheckedRadioButtonId();
                                View radioButton2 = radioGroup2.findViewById(id2);
                                if (radioButton2.getId() == R.id.radiobtn2) {
                                    rtadiobtn2textview.setText("লেমিনেশন");
                                } else {
                                    rtadiobtn2textview.setText("");
                                }
                            }
                        });


                        productMaterialdb.orderByChild("material_type").equalTo(s).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot item : dataSnapshot.getChildren()) {
                                    code = item.child("codenumbr").getValue(String.class);
                                    mat_type = item.child("material_type").getValue(String.class);
                                    gojmullo = item.child("gojMullo").getValue(String.class);
                                    brnd = item.child("brand").getValue(String.class);
                                    clr = item.child("color").getValue(String.class);
                                    bhr = item.child("bohor").getValue(String.class);
                                    flc = item.child("waste").getValue(String.class);
                                    wst = item.child("flactuation").getValue(String.class);
                                    suta = item.child("suta").getValue(String.class);
//                                    suta=item.child("radiobtn1textview").getValue(String.class);
//                                    suta=item.child("radiobtn1textview").getValue(String.class);

                                    codenumbr.setText(code);
                                    material_type.setText(mat_type);
                                    GOjMullo.setText(gojmullo);
                                    Brand.setText(brnd);
                                    Color.setText(clr);
                                    Bohor.setText(bhr);
                                    Suta.setText(suta);
                                    Waste.setText(wst);
                                    Flactuation.setText(flc);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
//Update Materials_settings
                        updatematerial.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i(Tag, "hit update");
                                String c = codenumbr.getText().toString();
                               final String m = material_type.getText().toString();
                                String st = Suta.getText().toString();
                                String b = Bohor.getText().toString();
                                String g = GOjMullo.getText().toString();
                                String f = Flactuation.getText().toString();
                                String col = Color.getText().toString();
                                String br = Brand.getText().toString();
                                String w = Waste.getText().toString();
//                                String dying = radiobtn1textview.getText().toString();
//                                String lamination = rtadiobtn2textview.getText().toString();

                                materialText.setText(m);
                                sutaText.setText(s);

//                                String mat= productMaterialdb.push().getKey();
//                           material = new Material(c, m, st, b, g, f, col, br, w, dying, lamination);
//                                productMaterialdb.child(mat).setValue(material);
//
//                                Log.i(Tag, "material_type"+m);
//                                productMaterialdb.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                        for(DataSnapshot ds:dataSnapshot.getChildren())
//                                        {
//                                             material=new Material(m);
//
//                                            productMaterialdb.child(ds.getKey()).child("material_type").setValue(m).toString();
//
//                                            Log.i(Tag, m);
////          arrayList.add(partselection);
//
//
//                                        }
//                                    }

//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                                productMaterialdb.child("-M8s75JrlvByDzlCUXXk").child("material_type").setValue("hello world");
                                productMaterialdb.child("material_type").orderByKey().equalTo(m).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Log.i(Tag, "inside ");
                                        try {
                                            String key = dataSnapshot.getKey();
                                            Log.i(Tag, "key : " + key);

                                            //here you need this key (-M8s75JrlvByDzlCUXXk) for update data
                                            // but you get here (Materials_Data)

                                            String name = dataSnapshot.child("material_type").getValue().toString();
                                            String Bohor = dataSnapshot.child("bohor").getValue().toString();
                                            productMaterialdb.child(key).child("material_type").setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getContext(), "material_type update", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(getContext(), "update error", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                            productMaterialdb.child(key).child("bohor").setValue(Bohor);
// productMaterialdb.child(key).child("material_type").setValue(material);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                                alert.dismiss();

                            }


                        });

//                        codenumberText.setText("");
//                        materialText.setText("");
//                        sutaText.setText("");
                        alert.show();
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Layer layer = layerList.get(position);

        viewHolder.Layername.setText(layer.getLayername());

//        listitem.setText(partselection.getna());
        return ListViewitem;

    }
}
