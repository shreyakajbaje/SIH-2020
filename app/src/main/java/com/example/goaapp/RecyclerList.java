package com.example.goaapp;



import java.util.ArrayList;
import java.util.List;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerList extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FeedBackList> myfeedbackList;
    FeedBackList mFeedData;
    EditText txt_Search;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    FloatingActionButton f1;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);




        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerList.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search=(EditText)findViewById(R.id.txt_searchtext);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Places....");

        myfeedbackList = new ArrayList<>();

        myAdapter = new MyAdapter(RecyclerList.this, myfeedbackList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("TouristPlaces");
        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myfeedbackList.clear();

                for (DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    FeedBackList fd = itemSnapshot.getValue(FeedBackList.class);


                    myfeedbackList.add(fd);


                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });



    }

    private void filter(String text) {

        ArrayList<FeedBackList> filterList=new ArrayList<>();

        for(FeedBackList item:myfeedbackList){

            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item);



            }

        }

        myAdapter.filteredList(filterList);
    }



}

