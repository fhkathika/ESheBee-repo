package com.example.eshebee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class test_Activity extends AppCompatActivity {
    DatabaseReference db,updateRef,lr1,lr2,lr3;
    ValueEventListener listener1,listener2,listener3;
    private ListView partlist;
    TextView incrementTextview,decrementTextview;
    TextView partlistEdittext;
    private List<Partselection> arrayList;
    private  listview_Adapter listview_adapter;

    int countlist;
    Context mcontext;
    Partselection p;
    String Tag="test_Activity";
    double count1,count2,count3,gripper,height,weight,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);
        incrementTextview=findViewById(R.id.partlist_increment);
        decrementTextview=findViewById(R.id.partlist_decrement);
        partlistEdittext=findViewById(R.id.partlist_edittxt);
        partlist=findViewById(R.id.partlistview);
        db = FirebaseDatabase.getInstance().getReference(" Part_List_Item");
        final DatabaseReference commandsRef = db.child("drones").child("commands");
        mcontext=test_Activity.this;
        arrayList=new ArrayList<>();

        listview_adapter=new listview_Adapter(arrayList, mcontext);

        incrementTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countlist++;
                partlistEdittext.setText("" + countlist);


//                String id= partlistEdittext.getText().toString();
                String name= partlistEdittext.getText().toString();
                String key= db.push().getKey();
                Partselection partselection=new Partselection(name);
                db.child(key).setValue(partselection);

                Toast.makeText(test_Activity.this, "item added", Toast.LENGTH_SHORT).show();

            }
        });
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Partselection partselection = ds.getValue(Partselection.class);
                    arrayList.add(partselection);
                    Toast.makeText(mcontext, partselection.getName(), Toast.LENGTH_SHORT).show();
                    Log.i(Tag,"abc"+ partselection);

                }

                partlist.setAdapter(listview_adapter);
                listview_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commandsRef.addValueEventListener(eventListener);

//        db.addValueEventListener(new ValueEventListener() {
//            private Partselection partselection;
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//                {
//                    String name=dataSnapshot1.getValue(String.class);
//                    arrayList.add(name);
//                }
//                partlist.setAdapter(listview_adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
   protected  void onStart(){

        super.onStart();
   }
}
