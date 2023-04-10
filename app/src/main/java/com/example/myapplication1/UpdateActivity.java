package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public abstract class UpdateActivity extends Activity implements OnClickListener{

    private EditText usernameText;
    private Button updatebtn, deletebtn;

    private long _id;

    private DBManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbManager = new DBManager(this);
        dbManager.open();

        usernameText = (EditText) findViewById(R.id.edit_username);

        updatebtn = (Button) findViewById(R.id.btn_update);
        deletebtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String username = intent.getStringExtra("username");

        _id = Long.parseLong(id);

        usernameText.setText(username);

        updatebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_update:
                String username = usernameText.getText().toString();

                dbManager.update(_id,username);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(),HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}