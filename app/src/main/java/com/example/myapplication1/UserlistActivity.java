package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
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
            EmployeeAdapterClass employeeAdapterClass = new EmployeeAdapterClass(employeeModelClasses, UserlistActivity.this);
            recyclerView.setAdapter(employeeAdapterClass);
        }else {
            Toast.makeText(this, "There are no Employees in the database", Toast.LENGTH_SHORT).show();
        }

    }
}