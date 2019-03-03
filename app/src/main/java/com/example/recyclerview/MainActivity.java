package com.example.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    private ArrayList<Restaurant> selected = new ArrayList<>();
    private RecyclerView mView;
    private CustomAdapter mAdapter;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.recyclerView);
        btnNext = findViewById(R.id.next);
        mAdapter = new CustomAdapter(mRestaurants);


        mView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mView.setItemAnimator( new DefaultItemAnimator());
        mView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mView.setAdapter(mAdapter);



        for(int i = 0; i< 10; i++){
            mRestaurants.add(new Restaurant("chino",i+"",1));
        }
        mAdapter.notifyDataSetChanged();

        //prepareData();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mRestaurants.size();
                Restaurant restaurant = mRestaurants.get(((int)Math.random()*size));
            }
        });
    }





}