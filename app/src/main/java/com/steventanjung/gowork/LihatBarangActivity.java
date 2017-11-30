package com.steventanjung.gowork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LihatBarangActivity extends AppCompatActivity {

    ListView listBrag;
    ArrayList<Barang> brg=new ArrayList<>();
    BarangAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        listBrag = findViewById(R.id.listBrg);
        adapter = new BarangAdapter(this,brg);
        listBrag.setAdapter(adapter);

    }
}
