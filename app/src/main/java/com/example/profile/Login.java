package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.profile.MainActivity.s_email;
import static com.example.profile.MainActivity.s_fname;
import static com.example.profile.MainActivity.s_pass;

public class Login extends AppCompatActivity {
    Button button,button2;
    EditText username,password;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        username=findViewById(R.id.uname);
        password=findViewById(R.id.upass);
        db = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                if(pass.equals(s_pass)){


                    Toast.makeText(getApplicationContext(),  s_pass+"Login Successfully", Toast.LENGTH_LONG).show();
                    Intent intentx = new Intent(Login.this,Profile.class);
                    startActivity(intentx);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }


            }
        });
    }

    public void registerUser(View view) {
        Intent intent_my = new Intent(Login.this,MainActivity.class);
        startActivity(intent_my);

    }
}