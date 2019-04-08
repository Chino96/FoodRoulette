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
import android.widget.ImageView;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    private ArrayList<FoodGenre> mGenre = new ArrayList<>();
    private ArrayList<String> selected = new ArrayList<>();
    private Button mNext;
    private Button mAddType;
    private RecyclerView mView2;
    private SelectTypeAdapter typeAdapter;
    private ImageView imageView;

    private int [] images= {R.drawable.fastfood,R.drawable.italian,R.drawable.bbq,R.drawable.chinese};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imageView = findViewById(R.id.imageView);
        mView2 = findViewById(R.id.recyclerView2);

        typeAdapter = new SelectTypeAdapter(mGenre);
        mNext= findViewById(R.id.next2);
        mAddType = findViewById(R.id.addType);

        mView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mView2.setItemAnimator( new DefaultItemAnimator());
        mView2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mView2.setAdapter(typeAdapter);

        //imageView.setImageResource(images[0]);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(SelectActivity.this,HomeActivity.class);
                startActivity(j);
            }
        });

        mAddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(SelectActivity.this,SearchActivity.class);
                search.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(search);
            }
        });

        mGenre.add(new FoodGenre("Fast Food",images[0]));
        selected.add(mGenre.get(0).getGenreName());
        mGenre.add(new FoodGenre("Italian",images[1]));
        selected.add(mGenre.get(1).getGenreName());
        mGenre.add(new FoodGenre("BBQ",images[2]));
        selected.add(mGenre.get(2).getGenreName());
        mGenre.add(new FoodGenre("Chinese",images[3]));
        selected.add(mGenre.get(3).getGenreName());

        typeAdapter.notifyDataSetChanged();

    }
}
