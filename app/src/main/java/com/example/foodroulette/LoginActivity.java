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

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        button = findViewById(R.id.Login);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().toString().equalsIgnoreCase("user") && Password.getText().toString().equals("password")){

                    Intent intent = new Intent(LoginActivity.this,SelectActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Log.v(" ",""+Username.getText());
                    Log.v(" ",""+Password.getText());
                    Toast.makeText(LoginActivity.this,"Invalid Username/Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
