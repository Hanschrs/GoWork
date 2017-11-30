package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MenuActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5,btnLogout;
    TextView helloUser;
    boolean session;
    String sessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        check session, if no session active then go remove from stack and revert back to login.
        helloUser = findViewById(R.id.hiUser);
        checkSession();
        if (session) {
            helloUser.setText("Hello, " + sessionName + " !");
        } else {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("loggedin_user");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            writer.close();
            finish();
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = (Button) findViewById(R.id.tambahBarang);
        btn2 = (Button) findViewById(R.id.buttonList);
        btn3 = (Button) findViewById(R.id.buttonNota);
        btn4 = (Button) findViewById(R.id.buttonCicilan);
        btn5 = (Button) findViewById(R.id.btn_tambahUser);
        btnLogout = (Button) findViewById(R.id.button_logout);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this, BarangActivity.class);
                startActivity(secondIntent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this, LihatBarangActivity.class);
                startActivity(secondIntent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this, LihatNotaActivity.class);
                startActivity(secondIntent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this, AddUser.class);
                startActivity(secondIntent);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("loggedin_user");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                writer.close();
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

//    prevent going back to login page
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void checkSession() {
        FileInputStream in = null;
        try {
            in = openFileInput("loggedin_user");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                session = true;
                sessionName = line;
            }
        } catch (IOException e) {
            session = false;
            sessionName = "";
            e.printStackTrace();
        }
    }


}
