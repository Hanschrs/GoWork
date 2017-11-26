package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    Button butn1;
    EditText username,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        butn1= (Button) findViewById(R.id.buttonLogin);
        username = (EditText) findViewById((R.id.inputUsername));
        password = (EditText) findViewById(R.id.inputPassword);

        butn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Intent secondIntent
                            = new Intent(Login.this,MenuActivity.class);
                    startActivity(secondIntent);
                }else{
                    Intent secondIntent
                            = new Intent(Login.this,Login.class);
                    startActivity(secondIntent);
                }
            }
        });

    }
}
