package com.corebit.assignment3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListDetails extends AppCompatActivity {

    ListView showlists;

    List<listshow> detailsList;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);


        showlists = findViewById(R.id.listId);

        db = FirebaseDatabase.getInstance().getReference("details");

        detailsList = new ArrayList<>();



    }

    @Override
    protected void onStart(){
        super.onStart();

        db.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailsList.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    listshow detail = postSnapshot.getValue(listshow.class);
                    detailsList.add(detail);
                }

                adapList myArtistAdapter =
                        new adapList(ListDetails.this,detailsList);
                showlists.setAdapter(myArtistAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
