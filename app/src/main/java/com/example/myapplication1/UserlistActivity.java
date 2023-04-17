package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class UserlistActivity extends AppCompatActivity{

    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBManager dbManager = new DBManager(this);
        List<EmployeeModelClass> employeeModelClasses = dbManager.getUsersList();

        if(employeeModelClasses.size()>0){
            EmployeeAdapterClass employeeAdapterClass = new EmployeeAdapterClass(employeeModelClasses, UserlistActivity.this,
                    poss -> {
                        Log.e("tag","poss"+poss);
                        Toast.makeText(UserlistActivity.this, "poss : "+poss, Toast.LENGTH_SHORT).show();
                        EmployeeModelClass object = employeeModelClasses.get(poss);
                        Log.e("tag","object : "+object.id);

                        /*Intent modify_intent = new Intent(UserlistActivity.this,UpdateActivity.class);

                        modify_intent.putExtra("id",object.id);
                        modify_intent.putExtra("username",object.username);
                        modify_intent.putExtra("password",object.password);

                        startActivity(modify_intent);*/

            }
            );
            recyclerView.setAdapter(employeeAdapterClass);

//            recyclerView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    TextView idTextView = (TextView) view.findViewById(R.id.text_id);
//                    TextView usernameTextView = (TextView) view.findViewById(R.id.text_username);
//                    TextView passwordTextView = (TextView) view.findViewById(R.id.text_password);
//
//                    String id = idTextView.getText().toString();
//                    String username = usernameTextView.getText().toString();
//                    String password = passwordTextView.getText().toString();
//
//                    /*Intent modify_intent = new Intent(getApplicationContext(),UpdateActivity.class);
//                    modify_intent.putExtra("id",id);
//                    modify_intent.putExtra("username",username);
//                    modify_intent.putExtra("password",password);
//
//                    startActivity(modify_intent);*/
//
//                }
//            });

        }else {
            Toast.makeText(this, "There are no Employees in the database", Toast.LENGTH_SHORT).show();
        }

    }
}