//package com.example.eshebee;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class spinner_Adapter extends ArrayAdapter<materialTypeSpinnerClass> {
// List<String> materialTypeSpinnerlist;
//    DatabaseReference materialTypeSpiner;
////    List<materialTypeSpinnerClass> materialTypeSpinnerlist;
//    public String Tag = "spinner_Adapter";
//    Context mContext;
//
//    public spinner_Adapter(int spinner_layout, List<> materialTypeSpinnerlist, Context mContext) {
//        super(mContext, R.layout.spinner_layout, materialTypeSpinnerlist);
//        this.materialTypeSpinnerlist = materialTypeSpinnerlist;
//        this.mContext = mContext;
//    }
//    private static class ViewHolder{
//
//        TextView materialTypeList;
//      Button matreialtypeButton;
//      Spinner materialTypeSpinner,sutaCountSpinner;
//    }
//
//    @NonNull
//    @Override
//    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        final ViewHolder viewHolder;
//        viewHolder=new ViewHolder();
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        final View ListViewitem = inflater.inflate(R.layout.spinner_layout, null, true);
//        viewHolder. materialTypeList = ListViewitem.findViewById(R.id.spinnerTv);
//        viewHolder. matreialtypeButton = ListViewitem.findViewById(R.id.addmatType);
////        viewHolder. materialTypeSpinner=ListViewitem.findViewById(R.id.spinner_listMAt);
//       viewHolder. materialTypeSpinner=ListViewitem.findViewById(R.id.spinner_listMAt);
////         viewHolder.sutaCountSpinner=ListViewitem.findViewById(R.id.spinner_listSuta);
////  materialTypeSpiner = FirebaseDatabase.getInstance().getReference(" Material_Type_Spinner");
//        materialTypeSpinnerlist=new ArrayList<>();
//        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_dropdown_item, materialTypeSpinnerlist);
//
//        viewHolder.materialTypeSpinner.setAdapter(spinner_adapter);
//        materialTypeSpiner.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                materialTypeSpinnerlist.clear();
//                for(DataSnapshot item:dataSnapshot.getChildren()) {
//                    final materialTypeSpinnerClass material_type=item.getValue(materialTypeSpinnerClass.class);
//                    materialTypeSpinnerlist.add(material_type);
//                }
//
//                spinner_adapter.notifyDataSetChanged();
//                Toast.makeText(mContext, "spinner list", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        viewHolder.matreialtypeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder    partEditor_DialogeBox = new AlertDialog.Builder(mContext);
//                                partEditor_DialogeBox.setCancelable(true);
//                                View mView = LayoutInflater.from(mContext).inflate(R.layout.product_sp_add_dialogbox, null);
//                                final AlertDialog alert = partEditor_DialogeBox.create();
//                                alert.setView(mView);
//                                final EditText editText=mView.findViewById(R.id.userAddProductspecification);
//                                Button save=mView.findViewById(R.id.userProductSpSave);
//                                save.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                       String s=editText.getText().toString();
//                                        String mat= materialTypeSpiner.push().getKey();
//                                        materialTypeSpinnerClass material=new materialTypeSpinnerClass(s);
//                                        materialTypeSpiner.child(mat).setValue(material);
//
//                                        Toast.makeText(mContext, "spinner item added", Toast.LENGTH_SHORT).show();
//
//                                        alert.dismiss();
//  }
//                                });
//                                alert.show();
//            }
//        });
//       materialTypeSpinnerClass mtypespinner = materialTypeSpinnerlist.get(position);
//
//        viewHolder.materialTypeList.setText(mtypespinner.getMaterialTypeList());
////        listitem.setText(partselection.getna());
//        return  ListViewitem;
//    }
//}
