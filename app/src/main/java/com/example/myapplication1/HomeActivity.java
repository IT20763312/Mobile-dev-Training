package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] {DatabaseHelperClass.ID,DatabaseHelperClass.USERNAME,DatabaseHelperClass.PASSWORD};
    final int[] to = new int[] {R.id.id,R.id.username,R.id.password};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = getIntent().getStringExtra("Username");
        TextView user;
        user = findViewById(R.id.loggedusername);
        user.setText("Welcome "+username);

        Button addUser = (Button) findViewById(R.id.adduserbtn);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });
    }
}