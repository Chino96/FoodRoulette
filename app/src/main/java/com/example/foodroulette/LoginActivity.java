package com.example.foodroulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button button;
    DBHelper dbHelper;
    ArrayList<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper =new DBHelper(getApplicationContext());
        users = dbHelper.getAllUsers();
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        button = findViewById(R.id.Login);
        Log.v("Login user list",""+dbHelper.getAllUsers());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Login Values", ""+Password.getText().toString().equals(users.get((users.indexOf(Username.getText().toString()) + 1))));
                if(users.contains(Username.getText().toString()) && Password.getText().toString().equals(users.get((users.indexOf(Username.getText().toString()) + 1)))){

                    Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.v(" ", "" + Username.getText());
                    Log.v(" ", "" + Password.getText());
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password\nBoth fields are case sensitive!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
