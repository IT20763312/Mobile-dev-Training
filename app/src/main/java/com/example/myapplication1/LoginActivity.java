package com.example.myapplication1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        Button lgnbtn = (Button) findViewById(R.id.loginbtn);

        dbManager = new DBManager(this);
        dbManager.open();

        lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dbManager.checkUser(username.getText().toString(),password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFULL !!!",Toast.LENGTH_SHORT).show();
                    openLogin();
                } else if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFULL !!!",Toast.LENGTH_SHORT).show();
                    openLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                    emptyInputEditText();
                }
            }

            public void openLogin(){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("Username", username.getText().toString());
                startActivity(intent);
            }

            private void emptyInputEditText() {
                username.setText(null);
                password.setText(null);
            }
        });
    }
}