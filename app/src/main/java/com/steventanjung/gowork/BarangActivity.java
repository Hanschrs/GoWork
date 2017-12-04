package com.steventanjung.gowork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BarangActivity extends AppCompatActivity {
    EditText nama, harga, jumlah;
    Button add;
    ListView listview;

    int id = 1;
    String kode;
    ArrayList<Barang> array = new ArrayList<>();
    BarangAdapter adapter;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = (EditText) findViewById(R.id.namabarang_barang);
        harga = (EditText) findViewById(R.id.harga_barang);
        jumlah = (EditText) findViewById(R.id.jumlah_barang);
        add = (Button) findViewById(R.id.tambahBtn_barang);

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (id < 9) {
                    kode = "00" + String.valueOf(id);
                    id++;
                } else if (id < 100) {
                    kode = "0" + String.valueOf(id);
                    id++;
                } else {
                    kode = String.valueOf(id);
                    id++;
                }

                Barang barang = new Barang(
                        (nama.getText().toString().substring(0, 2).toUpperCase() + kode),
                        nama.getText().toString(),
                        "Rp. " + harga.getText().toString(),
                        Integer.parseInt(jumlah.getText().toString())
                );
                array.add(barang);
                adapter.notifyDataSetChanged();
                index = -1;
            }
        });

    }
}
