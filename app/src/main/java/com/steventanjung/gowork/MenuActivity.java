package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MenuActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btnLogout;
    TextView helloUser;
    boolean session;
    String sessionName;
    boolean sessionAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        check session, if no session active then go remove from stack and revert back to login.
        helloUser = findViewById(R.id.hiUser);
        try {
            checkSession();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
//                PrintWriter writer = null;
//                try {
//                    writer = new PrintWriter("loggedin_user");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                writer.close();
                File userfile = new File("loggedin_user");
                userfile.delete();
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

    public void checkSession() throws JSONException {
        FileInputStream in = null;
        try {
            in = openFileInput("loggedin_user");
        } catch (FileNotFoundException e) {
            finish();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                session = true;
            }
        } catch (IOException e) {
            session = false;
            sessionName = "";
            e.printStackTrace();
        }
        JSONObject jObjUser = new JSONObject(String.valueOf(sb));
        sessionName = jObjUser.getString("username");
        if ((jObjUser.getString("pangkat")).equals("1")) {
            sessionAdmin = true;
        } else {
            sessionAdmin = false;
        }
    }


}
