package com.steventanjung.gowork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hans on 11/29/2017.
 */

public class AddUser extends AppCompatActivity {

    Button btnAddUser;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        btnAddUser = (Button) findViewById(R.id.btn_tambahUser);
        username = (EditText) findViewById(R.id.username_addUser);
        password = (EditText) findViewById(R.id.editText_password_user);
    }

}


