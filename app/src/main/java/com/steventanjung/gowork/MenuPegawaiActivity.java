package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPegawaiActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pegawai);
        btn1= (Button) findViewById(R.id.buttonAdd);
        btn2= (Button) findViewById(R.id.buttonNota);
        btn3= (Button) findViewById(R.id.buttonCicilan);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuPegawaiActivity.this,TambahNota.class);
                startActivity(secondIntent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent
                        = new Intent(MenuPegawaiActivity.this,LihatNotaActivity.class);
                startActivity(secondIntent);
            }
        });

//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent secondIntent
//                        = new Intent(MenuPegawaiActivity.this,LihatNotaActivity.class);
//                startActivity(secondIntent);
//            }
//        });

    }
}
