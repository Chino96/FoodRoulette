package com.example.foodroulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    private ArrayList<String> selected = new ArrayList<>();
    private RecyclerView mView;
    private CustomAdapter mAdapter;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mView = findViewById(R.id.recyclerView);
        btnNext = findViewById(R.id.next);
        mAdapter = new CustomAdapter(mRestaurants);


        mView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mView.setItemAnimator( new DefaultItemAnimator());
        mView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mView.setAdapter(mAdapter);



        for(int i = 0; i< 10; i++){
            mRestaurants.add(new Restaurant("Food Type",i+"",1));
            selected.add(mRestaurants.get(i).getmName());
        }
        mAdapter.notifyDataSetChanged();

        //prepareData();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RouletteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("selected",selected);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
