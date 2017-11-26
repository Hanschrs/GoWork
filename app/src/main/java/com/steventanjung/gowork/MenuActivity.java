package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1= (Button) findViewById(R.id.tambahBarang);
        btn2= (Button) findViewById(R.id.buttonList);
        btn3= (Button) findViewById(R.id.buttonNota);
        btn4= (Button) findViewById(R.id.buttonCicilan);
        btn5= (Button) findViewById(R.id.button_logout);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this,BarangActivity.class);
                startActivity(secondIntent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this,LihatBarangActivity.class);
                startActivity(secondIntent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this,LihatNotaActivity.class);
                startActivity(secondIntent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuActivity.this,Login.class);
                startActivity(secondIntent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }




}
