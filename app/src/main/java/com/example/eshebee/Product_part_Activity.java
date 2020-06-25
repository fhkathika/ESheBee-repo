package com.example.eshebee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.eshebee.R.layout.spinner_layout;
import static java.lang.Double.valueOf;
import static java.lang.Integer.parseInt;

public class Product_part_Activity extends AppCompatActivity  {
    public static String m;
   DatabaseReference dbsave, db,layerdb,materialTypeSpiner,lr1,lr2,lr3,productMaterialdb,sutaCountTypeSpiner;
 ValueEventListener listener1,listener2,listener3;
    Partselection partselection;
    Layer layer;
    private ListView partlist;
TextView incrementTextview,decrementTextview;
    TextView partlistEdittext;
int countlist;
RadioButton radioButton;

String Tag="Product_part_Activity";
double count1,count2,count3,gripper,height,weight,result;

  List<Partselection> arrayList;
  List<Layer> layerList;
  List<String> stringArrayList;
    List<String> materialTypeSpinnerlist; ;


 private  listview_Adapter listview_adapter;
 private Layer_Adapter layer_adapter;
//    private ArrayAdapter<Partselection> listview_adapter;

    private ArrayList<String> layerlist1;
    private ArrayList<String> layerlist2=new ArrayList<>();
    private ArrayList<String> layerlist3=new ArrayList<>();
    private ArrayList<String> Insert_data;
    private ArrayAdapter<String> layer1adapter;
    private ArrayAdapter<String> layer2adapter;
    private ArrayAdapter<String> layer3adapter;
    private ArrayAdapter<String> inserdataadapter;
    private ArrayAdapter<String> Stringarrayadapter;
    public AlertDialog.Builder partEditor_DialogeBox;

Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_part_);
        incrementTextview=findViewById(R.id.partlist_increment);
        decrementTextview=findViewById(R.id.partlist_decrement);
        partlistEdittext=findViewById(R.id.partlist_edittxt);
        partlist=findViewById(R.id.partlistview);
        layerlist1=new ArrayList<>();
        layerlist2=new ArrayList<>();
        layerlist3=new ArrayList<>();
        Insert_data=new ArrayList<>();
        Layer1 L1;

        dbsave = FirebaseDatabase.getInstance().getReference(" insert_data_save");
        layerdb = FirebaseDatabase.getInstance().getReference(" insert_layer").child("part");

//commandsRef = db.child("drones").child("command");
        mcontext=Product_part_Activity.this;
       lr1 = FirebaseDatabase.getInstance().getReference(" Layer1_List_Item");
       lr2 = FirebaseDatabase.getInstance().getReference(" Layer2_List_Item");
       lr3 = FirebaseDatabase.getInstance().getReference(" Layer3_List_Item");
        productMaterialdb = FirebaseDatabase.getInstance().getReference(" Materials_Data");

        stringArrayList=new ArrayList<>();
        partselection=new Partselection();
        layer=new Layer();

        arrayList=new ArrayList<>();
        layerList=new ArrayList<>();
        listview_adapter=new listview_Adapter(arrayList,Product_part_Activity.this);
        layer_adapter=new Layer_Adapter(layerList,Product_part_Activity.this);

        Stringarrayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringArrayList);
        layer1adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,layerlist1);
        layer2adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,layerlist2);
        layer3adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,layerlist3);
        inserdataadapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Insert_data);
//        stringArrayList.add("1. Body");
//        stringArrayList.add("2. Gadget");
//        stringArrayList.add("3. Handle");
//        stringArrayList.add("4. Zipper");
//        stringArrayList.add("5. Pocket"
//        partlist.setAdapter(Stringarrayadapter);
        db = FirebaseDatabase.getInstance().getReference(" Part_List_Item");

        incrementTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countlist++;
                partlistEdittext.setText("" + countlist);

//                String name= partlistEdittext.getText().toString();
                String name= "";
                final String key= db.push().getKey();
                partselection=new Partselection(name);
                db.child(key).setValue(partselection);

                Toast.makeText(mcontext, "item added", Toast.LENGTH_SHORT).show();

            }
        });


//        commandsRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String string=   dataSnapshot.getValue(String.class);
//                stringArrayList.add(string);
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                String string=   dataSnapshot.getValue(String.class);
//                stringArrayList.remove(string);
//                Stringarrayadapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    partselection=dataSnapshot1.getValue(Partselection.class);
//                  String s=dataSnapshot1.getValue(String.class);
                    arrayList.add(partselection);

                    Toast.makeText(Product_part_Activity.this, partselection.getName(), Toast.LENGTH_SHORT).show();
//                Log.i(Tag,"abc"+ partselection);
                }
                partlist.setAdapter(listview_adapter);
                listview_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        partlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, final long l) {
            final    Partselection value=arrayList.get(i);
                partEditor_DialogeBox = new AlertDialog.Builder(Product_part_Activity.this);
                partEditor_DialogeBox.setCancelable(true);

                View mView = getLayoutInflater().inflate(R.layout.part_editor_dialogbox, null);
                final AlertDialog alert = partEditor_DialogeBox.create();
                alert.setView(mView);
                final  ListView Llist=mView.findViewById(R.id.layerlist);
               final EditText updatePart=mView.findViewById(R.id.updatePart);
               final EditText LayerEditext=mView.findViewById(R.id.layeredittext);
               final TextView hedittext=mView.findViewById(R.id.h_edittxt);
              final  TextView wedittext=mView.findViewById(R.id.w_edittxt);
               final TextView gedittext=mView.findViewById(R.id.g_edittxt);
            final    TextView Consumption=mView.findViewById(R.id.consumption);
                TextView hIncrement=mView.findViewById(R.id.H_increment);
                TextView hDecrement=mView.findViewById(R.id.H_decrement);
                TextView wIncrement=mView.findViewById(R.id.W_increment);
                TextView wDecrement=mView.findViewById(R.id.W_decrement);
                TextView gIncrement=mView.findViewById(R.id.g_increment);
                TextView gDecrement=mView.findViewById(R.id.g_decrement);
                final TextView LIecrement=mView.findViewById(R.id.layer_increment);
                TextView LDecrement=mView.findViewById(R.id.layer_decrement);
                ImageView AddSpnierList=mView.findViewById(R.id.addspinnerlist);

//                final ImageView settings=mView.findViewById(R.id.settings);
                final TextView save=mView.findViewById(R.id.dataSave);
                Button update=mView.findViewById(R.id.updatebtn);

//                final Spinner spinner1=mView.findViewById(R.id.spinnerl1);


             updatePart.setText(value.getName());
                hIncrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count1=count1 +.5;
                        hedittext.setText("" + count1);
                        String hValue=hedittext.getText().toString();
                        Log.i(Tag,"" + hValue);
                        height=valueOf(hValue);
                        result=height*weight+gripper/100;
                        Consumption.setText(String.valueOf(result));
                    }
                });
                hDecrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count1<=0) {
                            count1=0;
                        }
                        else {
                            count1 =count1-.5;

                        }
                        hedittext.setText("" + count1);
                        String hValue=hedittext.getText().toString();
                        Log.i(Tag,"" + hValue);
                        height=valueOf(hValue);
                        result=height*weight+gripper/100;
                        Consumption.setText(String.valueOf(result));
                    }
                });
                wIncrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count2=count2 +.5;
                        wedittext.setText("" + count2);
                        String wValue=wedittext.getText().toString();
                        Log.i(Tag,"" + wValue);
                        weight= valueOf(wValue);
                        result=height*weight+gripper/100;
                        Consumption.setText(String.valueOf(result));
                    }
                });
                wDecrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count2<=0) {
                            count2=0;
                        }
                        else {
                            count2 =count2-.5;

                        }
                        wedittext.setText("" + count2);
                        String wValue=wedittext.getText().toString();
                        Log.i(Tag,"" + wValue);
                        weight= valueOf(wValue);
                        result=height*weight+gripper/100;
                        Consumption.setText(String.valueOf(result));
                    }
                });

              gIncrement.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    count3 ++;
                      gedittext.setText( ""+count3);
                      String gValue=gedittext.getText().toString();
                      Log.i(Tag,"" + gValue);
                      gripper= valueOf(gValue);
                      result=height*weight+gripper/100;
                      Consumption.setText(String.valueOf(result));

                  }
              });
              gDecrement.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if(count3<=0) {
                          count3=0;
                      }
                      else {
                       count3--;

                      }
                      gedittext.setText(""+ count3);
                      String gValue=gedittext.getText().toString();
                      Log.i(Tag,"" + gValue);
                      gripper= valueOf(gValue);
                      result=height*weight+gripper/100;
                      Consumption.setText(String.valueOf(result));
                  }
              });


              LIecrement.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      countlist++;
                      LayerEditext.setText("Layer" + countlist);
                      String Layername= LayerEditext.getText().toString();

                    final String key=layerdb .push().getKey();
                      layer=new Layer(Layername);
                      layerdb.child(key).setValue(layer);

                      Toast.makeText(mcontext, "layer list added", Toast.LENGTH_SHORT).show();
                  }
              });
                Llist.setAdapter(layer_adapter);
                layer_adapter.notifyDataSetChanged();
                layerdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        layerList.clear();
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            Layer layer=dataSnapshot1.getValue(Layer.class);
//                  String s=dataSnapshot1.getValue(String.class);
                            layerList.add(layer);


//                Log.i(Tag,"abc"+ partselection);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//        final Partselection value = arrayList.get(i);
//        final String upValue = value.getName();
//        String name = updatePart.getText().toString();
//        if (valueOf(upValue) != valueOf(name)) {
////      arrayList.get(i).setName(name);
////        upadatelist("countlist",name);
//        }
//        partselection=(Partselection) getIntent().getSerializableExtra("tem");
                        final String name = updatePart.getText().toString();


                      db.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

for(DataSnapshot ds:dataSnapshot.getChildren())
{
        partselection=new Partselection(value.name);

          db.child(ds.getKey()).child("name").setValue(partselection.getName());
          Log.i(Tag, name);
//          arrayList.add(partselection);


}
//                                final Partselection partselection =new Partselection(name);
//                             String id = LayerEditext.getText().toString();
//                             db.child("key").child("Partselection").setValue(partselection);
//                                arrayList.add(partselection);
//                                partlist.setAdapter(layer_adapter);
//                                layer_adapter.notifyDataSetChanged();
////                db.setValue(updatePart.getText().toString());
//                                String key=db.getKey();
//                                Partselection value1 = dataSnapshot.getValue(Partselection.class);
////                                String name = dataSnapshot.child(name2).getValue().toString();
//                              partselection=new Partselection(name);
//                           db.child(key).setValue(partselection);
                                Toast.makeText(Product_part_Activity.this, value.getName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
//        final Partselection partselection =new Partselection(name);
//        db.child("key").setValue(partselection);
//        arrayList.clear();
//db.child("parts").setValue(partselection);
//        arrayList.add(dataSnapshot.getValue(Partselection.class));
//        arrayList.set(i,partselection);
//
//                    String name = updatePart.getText().toString();
//                    Partselection partselection =new Partselection(name);
//                    db.setValue(partselection);
//                    Toast.makeText(Product_part_Activity.this, "data has been updated", Toast.LENGTH_SHORT).show();
//                    arrayList.set(i,partselection);
//                Log.i(Tag,"abc"+ partselection);

//                partlist.setAdapter(listview_adapter);
//                listview_adapter.notifyDataSetChanged();

//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
//
//                    String name = updatePart.getText().toString();
//                    Partselection partselection =new Partselection(name);
//                    db.setValue(partselection);
//                    Toast.makeText(Product_part_Activity.this, "data has been updated", Toast.LENGTH_SHORT).show();
//                    arrayList.set(i,partselection);
////                Log.i(Tag,"abc"+ partselection);
//
//                partlist.setAdapter(listview_adapter);
//                listview_adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


                        alert.dismiss();
                    }
                });
                LDecrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(countlist<=0) {
                            countlist=0;
                        }
                        else {
                            countlist --;

                            layerdb.removeValue();
                        }


                    }
                });


                AddSpnierList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder    partEditor_DialogeBox = new AlertDialog.Builder(Product_part_Activity.this);
                        partEditor_DialogeBox.setCancelable(true);
                        View mView = LayoutInflater.from(Product_part_Activity.this).inflate(R.layout.settings_editor_dialogbox, null);

                        final AlertDialog alert = partEditor_DialogeBox.create();
                        alert.setView(mView);

                        final EditText codenumbr=mView.findViewById(R.id.code);
                        final EditText material_type=mView.findViewById(R.id.material);
                        final EditText Suta=mView.findViewById(R.id.suta);
//                        final TextView codenumberText=mView.findViewById(R.id.t1);
                        final TextView materialText=mView.findViewById(R.id.t2);
                        final TextView sutaText=mView.findViewById(R.id.t3);
                        final TextView radiobtn1textview=mView.findViewById(R.id.t4);
                        final TextView rtadiobtn2textview=mView.findViewById(R.id.t5);
                        final EditText Bohor=mView.findViewById(R.id.bohor);
                        final TextView Flactuation=mView.findViewById(R.id.flactiation);
                        final TextView Waste=mView.findViewById(R.id.waste);
                        final EditText GOjMullo=mView.findViewById(R.id.gojmullo);
                        final EditText Brand=mView.findViewById(R.id.brand);
                        final EditText Color=mView.findViewById(R.id.color);
                        Button savematerial=mView.findViewById(R.id.save);
                        final RadioGroup radioGroup=mView.findViewById(R.id.radiogroup);
                        final RadioGroup radioGroup2=mView.findViewById(R.id.radiogroup2);
                       final Spinner materialTypeSpinner=mView.findViewById(R.id.spinner_listMAt);
//                       final Spinner sutaCountSpinner=mView.findViewById(R.id.spinner_listSuta);
                       final TextView texting=mView.findViewById(R.id.matlistTv);
                       final TextView sutacounttexting=mView.findViewById(R.id.sutalistTv);
                        materialTypeSpiner = FirebaseDatabase.getInstance().getReference(" Material_Type_Spinner");
                        sutaCountTypeSpiner = FirebaseDatabase.getInstance().getReference(" SutaCount_Type_Spinner");

                        texting.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder    partEditor_DialogeBox = new AlertDialog.Builder(Product_part_Activity.this);
                                partEditor_DialogeBox.setCancelable(true);
                                View mView = LayoutInflater.from(Product_part_Activity.this).inflate(R.layout.product_sp_add_dialogbox, null);
                                final AlertDialog alert = partEditor_DialogeBox.create();
                                alert.setView(mView);
                                final EditText materialTypeList=mView.findViewById(R.id.userAddProductspecification);
                                Button save=mView.findViewById(R.id.userProductSpSave);
                                save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        String  material_type_list= materialTypeList.getText().toString();
                                        String key= materialTypeSpiner.push().getKey();
//                                        materialTypeSpinnerClass material=new materialTypeSpinnerClass(ma);
                                        materialTypeSpiner.child(key).setValue(material_type_list);

                                        Toast.makeText(mcontext, "spinner item added", Toast.LENGTH_SHORT).show();

                                        alert.dismiss();

                                    }
                                });
                                alert.show();
                            }
                        });

                        materialTypeSpinnerlist=new ArrayList<>();
                        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mcontext,
                                android.R.layout.simple_spinner_dropdown_item, materialTypeSpinnerlist);

                        materialTypeSpinner.setAdapter(spinnerAdapter);

                        materialTypeSpiner.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                materialTypeSpinnerlist.clear();
                                for(DataSnapshot item:dataSnapshot.getChildren()) {
                                    final String material_type=item.getValue(String.class);
                                    materialTypeSpinnerlist.add(material_type);
                                }
                                spinnerAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        sutacounttexting.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder    partEditor_DialogeBox = new AlertDialog.Builder(Product_part_Activity.this);
                                partEditor_DialogeBox.setCancelable(true);
                                View mView = LayoutInflater.from(Product_part_Activity.this).inflate(R.layout.product_sp_add_dialogbox, null);
                                final AlertDialog alert = partEditor_DialogeBox.create();
                                alert.setView(mView);
                                final EditText materialTypeList=mView.findViewById(R.id.userAddProductspecification);
                                Button save=mView.findViewById(R.id.userProductSpSave);
                                save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        String  material_type_list= materialTypeList.getText().toString();
                                        String key= sutaCountTypeSpiner.push().getKey();
//                                        materialTypeSpinnerClass material=new materialTypeSpinnerClass(ma);
                                        sutaCountTypeSpiner.child(key).setValue(material_type_list);

                                        Toast.makeText(mcontext, "spinner item added", Toast.LENGTH_SHORT).show();

                                        alert.dismiss();

                                    }
                                });
                                alert.show();
                            }
                        });

                        materialTypeSpinnerlist=new ArrayList<>();
                        final ArrayAdapter<String> spinnerAdaptersuta = new ArrayAdapter<String>(mcontext,
                                android.R.layout.simple_spinner_dropdown_item, materialTypeSpinnerlist);

                        materialTypeSpinner.setAdapter(spinnerAdapter);

                        materialTypeSpiner.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                materialTypeSpinnerlist.clear();
                                for(DataSnapshot item:dataSnapshot.getChildren()) {
                                    final String material_type=item.getValue(String.class);
                                    materialTypeSpinnerlist.add(material_type);
                                }
                                spinnerAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                int id=radioGroup.getCheckedRadioButtonId();
                                View radioButton=radioGroup.findViewById(id) ;
                                if(radioButton.getId()==R.id.radio_id1){
                                    radiobtn1textview.setText("ডাইং");
                                }
                                else

                                {
                                    radiobtn1textview.setText("");
                                }
                            }
                        });

                        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup2, int i) {
                                int id2=radioGroup2.getCheckedRadioButtonId();
                                View radioButton2=radioGroup2.findViewById(id2) ;
                                if(radioButton2.getId()==R.id.radiobtn2){
                                    rtadiobtn2textview.setText("লেমিনেশন");
                                }
                                else

                                {
                                    rtadiobtn2textview.setText("");
                                }
                            }
                        });

//                        materialTypeSpinnerlist=new ArrayList<>();
////                        final spinner_Adapter spinner_adapter=new spinner_Adapter(materialTypeSpinnerlist,mcontext);
//                        final spinner_Adapter spinner_adapter=new spinner_Adapter(R.layout.spinner_layout,materialTypeSpinnerlist,mcontext);
//                    materialTypeSpinner.setAdapter(spinner_adapter);
//                        materialTypeSpiner.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                materialTypeSpinnerlist.clear();
//                                for(DataSnapshot item:dataSnapshot.getChildren()) {
//                                    final materialTypeSpinnerClass materialTypeList=item.getValue(materialTypeSpinnerClass.class);
//                                    materialTypeSpinnerlist.add(materialTypeList);
//                                }
//                                spinner_adapter.notifyDataSetChanged();
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//                        materialTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                                materialTypeSpinnerClass materialSpnner=spinner_adapter.getItem(i);
//                                Toast.makeText(Product_part_Activity.this, "material spinner list", Toast.LENGTH_SHORT).show();
////                                material_type.setText(materialSpnner);
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> adapterView) {
//
//                            }
//                        });

                        savematerial.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               String c=codenumbr.getText().toString();
                               m=material_type.getText().toString();
                                String s=Suta.getText().toString();
                                String b=Bohor.getText().toString();
                                String g=GOjMullo.getText().toString();
                                String f=Flactuation.getText().toString();
                                String col=Color.getText().toString();
                                String br=Brand.getText().toString();
                                String w=Waste.getText().toString();
                                String dying=radiobtn1textview.getText().toString();
                                String lamination=rtadiobtn2textview.getText().toString();

                                materialText.setText(m);
                                sutaText.setText(s);
                                String mat= productMaterialdb.push().getKey();
                               Material material=new Material(c,m,s,b,g,f,col,br,w,dying,lamination);
                                productMaterialdb.child(mat).setValue(material);

                                Toast.makeText(mcontext, "item added", Toast.LENGTH_SHORT).show();

                        alert.dismiss();
                            }

                        });


                        materialText.setText("");
                        sutaText.setText("");
                        alert.show();
                    }
                });


//              spinner1.setAdapter(layer1adapter);

//
//                addbtn3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        final String data3=layer3.getText().toString();
//                        if (TextUtils.isEmpty(data3)) {
//                            layer3.setError("Please input layer item");
//                            return;
//                        }
//                        lr3.push().setValue(data3).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                layer3.setText("");
//                                layer3adapter.notifyDataSetChanged();
//                                Toast.makeText(Product_part_Activity.this, "data inserted", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                // TODO Auto-generated method stub
//                                String spinner_item3= layerlist3.get(position);
//                                layer3Textview.setText(spinner_item3);
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//                                // TODO Auto-generated method stub
//
//                            }
//                        });
//                        listener3=lr3.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                for(DataSnapshot item:dataSnapshot.getChildren()){
//                                    layerlist3.add(item.getValue().toString());
//                                }
//                                layer3adapter.notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//
//                    }
//                });


save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final String d1=  updatePart.getText().toString();
//        final String d2=  layer1Textview.getText().toString();
//        final String d3= layer2Textview.getText().toString();
//        final String d4 = layer3Textview.getText().toString();
//        if (TextUtils.isEmpty(d1)) {
//            updatePart.setError("Please input Product Name");
//            return;
//        }
//        if (TextUtils.isEmpty(d2)) {
//            layer1Textview.setError("Please input Product Name");
//            return;
//        }
//        if (TextUtils.isEmpty(d3)) {
//            layer2Textview.setError("Please input Product Name");
//            return;
//        }
//        if (TextUtils.isEmpty(d4)) {
//            layer3Textview.setError("Please input Product Name");
//            return;
//        }

//        String key = dbsave.push().getKey();
//        partEdit_insertData insertuserData = new partEdit_insertData (d1, d2, d3,d4);
//        dbsave.child(key).setValue(insertuserData);
//        Toast.makeText(Product_part_Activity.this, "product detail is added", Toast.LENGTH_SHORT).show();

        alert.dismiss();
    }
});



  alert.show();
            }
        });


//        ValueEventListener eventListener=new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Partselection partselection = ds.getValue(Partselection.class);
//                arrayList.add(partselection);
//                Toast.makeText(Product_part_Activity.this, partselection.getName(), Toast.LENGTH_SHORT).show();
//                Log.i(Tag,"abc"+ partselection);
//                }
//                partlist.setAdapter(listview_adapter);
//                listview_adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        };
//        commandsRef.addValueEventListener(eventListener);

        decrementTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countlist<=0) {
                    countlist=0;
                }
                else {
                    countlist --;
                    db.removeValue( );
                }
                partlistEdittext.setText("" + countlist);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

//private boolean upadatelist(String id,String name){
//        if(update)
//    Partselection partselection=new Partselection(id,name);
//    db.setValue(partselection);
//    Toast.makeText(this, "item updated", Toast.LENGTH_SHORT).show();
//    return true;
//}
}
