package com.steventanjung.gowork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    Button addBtn;
    ListView userlist;
    ArrayList<User> arrUser = new ArrayList<>();
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        addBtn = findViewById(R.id.addUser_user);
        userlist = findViewById(R.id.listUser_user);

        //fetch userlist here

        adapter = new UserAdapter(this, arrUser);
        userlist.setAdapter(adapter);

    }
}
