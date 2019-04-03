package com.example.foodroulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recyclerview.R;

public class SelectActivity extends AppCompatActivity {

    private Button mNext;
    private Button mAddType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mNext= findViewById(R.id.next2);
        mAddType = findViewById(R.id.addType);
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
                startActivity(search);
            }
        });

    }
}
