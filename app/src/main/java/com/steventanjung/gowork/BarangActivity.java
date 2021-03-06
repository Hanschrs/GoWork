package com.steventanjung.gowork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangActivity extends AppCompatActivity {

    EditText nama,deskripsi,harga,jumlah;
    Button add;
    int id = 1;
    String kode;
    ArrayList<Barang> array = new ArrayList<>();
    BarangAdapter adapter;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new BarangAdapter(this,array);
        nama=(EditText) findViewById(R.id.namabarang_barang);
        harga= (EditText) findViewById(R.id.harga_barang);
        jumlah =  (EditText) findViewById(R.id.jumlah_barang);
        deskripsi=(EditText) findViewById(R.id.deskripsi_barang);
        add= (Button) findViewById(R.id.tambahBtn_barang);

        add.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                String namaInput=nama.getText().toString();
                String deskripsiInput=deskripsi.getText().toString();
                String hargaInput=String.valueOf(harga.getText().toString());
                String jumlahInput=String.valueOf(jumlah.getText().toString());

                addBarang(namaInput, deskripsiInput, hargaInput, jumlahInput);

            }
        });

    }

    public void addBarang(final String nama_param, final String deskripsi_param, final String harga_param, final String jumlah_param) {
        String url = "https://www.hanschrs.com/android/uas/addBarang.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(BarangActivity.this, message, Toast.LENGTH_SHORT).show();

                    Intent returnIntent=new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BarangActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama_param);
                params.put("deskripsi", deskripsi_param);
                params.put("harga", harga_param);
                params.put("jumlah", jumlah_param);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
