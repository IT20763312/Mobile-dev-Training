package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText add_username, add_password;
    private Button btn_add;

    private DBManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String username = getIntent().getStringExtra("Username");

        add_username = (EditText) findViewById(R.id.regusername);
        add_password = (EditText) findViewById(R.id.regpassword);
        btn_add = (Button) findViewById(R.id.regbtn);

        dbManager = new DBManager(this);
        dbManager.open();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String stringusername = add_username.getText().toString();
                final String stringpassword = add_password.getText().toString();

                if (stringusername.length() <= 0 || stringpassword.length() <= 0 ){
                    Toast.makeText(RegisterActivity.this, "Enter All Data !!!", Toast.LENGTH_SHORT).show();
                }else {
                    dbManager.insert(stringusername,stringpassword);
                    Intent main = new Intent(RegisterActivity.this,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    main.putExtra("Username", username);
                    startActivity(main);
                }
            }
        });
    }
}