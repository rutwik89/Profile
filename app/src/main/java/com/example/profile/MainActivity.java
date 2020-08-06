package com.example.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import  android.content.DialogInterface;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextF, editTextLast, editTextLoc, editTextPhn, editTextEmail, editTextPass, editTextCnfPass;
    static String s_fname, s_lname, s_loc, s_email, s_phone, s_pass, s_cnf_pass;
    Button buttonregister;
    SQLiteDatabase db;
    int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextF = findViewById(R.id.edFisrtPersonName);
        editTextLast = findViewById(R.id.edLastTextPersonName);
        editTextLoc = findViewById(R.id.edLocationPerson);
        editTextPhn = findViewById(R.id.edPhone);
        editTextEmail = findViewById(R.id.edEmailIDPerson);
        editTextPass = findViewById(R.id.edPassword);
        editTextCnfPass = findViewById(R.id.edConfPassword);
        buttonregister = findViewById(R.id.signupbutton);
        db=openOrCreateDatabase("mydb", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS mydb_test(userfirstname VARCHAR,userlastname VARCHAR,useremail VARCHAR,userphone VARCHAR,userlocation VARCHAR,userpassword VARCHAR);");
    }

    public void registeruser(View view) {
        s_fname = editTextF.getText().toString().toUpperCase().trim();
        s_lname = editTextLast.getText().toString().toUpperCase().trim();
        s_email = editTextEmail.getText().toString().toLowerCase().trim();
        s_phone = editTextPhn.getText().toString().trim();
        s_loc = editTextLoc.getText().toString().toUpperCase().trim();
        s_pass = editTextPass.getText().toString().trim();
        s_cnf_pass = editTextCnfPass.getText().toString().trim();

        if (s_fname.isEmpty() || s_lname.isEmpty() || s_loc.isEmpty() || s_email.isEmpty() || s_phone.isEmpty()) {
            show_Message("Registration", "Invalid Input");
        } else if (s_pass.isEmpty() || s_cnf_pass.isEmpty()) {
            show_Message("Registration", "Invalid Input");
        } else if (s_pass.equals(s_cnf_pass)) {
            show_Message("Registration", "Valid Input");
            flag=1;

        } else {
            show_Message("Registration", "Password Not Valid ");
        }
    }

    private void show_Message(String title, String input) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(input);
        builder.setIcon(R.drawable.ic_baseline_insert_emoticon_24);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(flag==1)
                {
                    db.execSQL("INSERT INTO mydb_test VALUES('"+s_fname+"','"+s_lname+"','"+s_email+"','"+s_phone+"','"+s_loc+"','"+s_pass+"');");
                    show_Message_Confirmation("Success","Registration Done");

                }else
                {
                    show_Message_Confirmation("Failed","Registration Not Done");
                }


            }
        });
        builder.setNegativeButton("RE-TRY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // finish();
            }
        });
        builder.show();
    }

    private void show_Message_Confirmation(String success, String o) {
        final AlertDialog.Builder builder_1 = new AlertDialog.Builder(this);
        builder_1.setTitle(success);
        builder_1.setMessage(o);
        builder_1.setIcon(R.drawable.ic_baseline_insert_emoticon_24);
        flag=0;
        builder_1.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent_my = new Intent(MainActivity.this,Login.class);
                startActivity(intent_my);
                builder_1.setCancelable(true);
            }
        });
        builder_1.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder_1.show();

    }

}
