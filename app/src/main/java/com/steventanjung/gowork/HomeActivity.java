package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class HomeActivity extends AppCompatActivity {

    //session
    boolean session;
    String sessionName;
    boolean sessionAdmin;

    //layout stuff
    TextView username_text, barang_text, nota_text, user_text;
    ImageView barang_logo, nota_logo, user_logo;
    Button logout_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //check session, if no session active then go remove from stack and revert back to login.
        username_text = findViewById(R.id.username_home);
        try {
            checkSession();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (session) {
            username_text.setText(sessionName);
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

        username_text = findViewById(R.id.username_home);
        barang_text = findViewById(R.id.textBarang_home);
        nota_text = findViewById(R.id.textNota_home);
        user_text = findViewById(R.id.textUser_home);
        barang_logo = findViewById(R.id.logoBarang_home);
        nota_logo = findViewById(R.id.logoNota_home);
        user_logo = findViewById(R.id.logoUser_home);
        logout_button = findViewById(R.id.logout_home);


        if (!sessionAdmin) {
            user_logo.setVisibility(View.INVISIBLE);
            user_text.setVisibility(View.INVISIBLE);
        }

        barang_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barangIntent = new Intent(HomeActivity.this, BarangActivity.class);
                startActivity(barangIntent);
            }
        });

        barang_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barangIntent = new Intent(HomeActivity.this, BarangActivity.class);
                startActivity(barangIntent);
            }
        });

        nota_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notaIntent = new Intent(HomeActivity.this, LihatNotaActivity.class);
                startActivity(notaIntent);
            }
        });

        nota_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notaIntent = new Intent(HomeActivity.this, LihatNotaActivity.class);
                startActivity(notaIntent);
            }
        });

        user_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userIntent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(userIntent);
            }
        });

        user_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userIntent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(userIntent);
            }
        });


        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = getFilesDir();
                File userfile = new File(dir, "loggedin_user");
                boolean deleted = userfile.delete();
                if (deleted) finish();
                else logout_button.setText("ERROR GAN.");
            }
        });

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
