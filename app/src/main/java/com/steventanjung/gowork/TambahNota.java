package com.steventanjung.gowork;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TambahNota extends AppCompatActivity {

    Spinner spin;
    EditText jumlah;
    Button tambah, done;
    int counter = 0;
    ListView list;
    ArrayList<Barang> arrBarang = new ArrayList<>();
    ArrayList<Belanjaan> arrBelanja = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    BelanjaanAdapter adapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_nota);

        jumlah = findViewById(R.id.jumlah_nota);
        tambah = findViewById(R.id.tambahBtn_nota);
        done = findViewById(R.id.doneButton_nota);
        list=findViewById(R.id.listView_nota);
        adapater=new BelanjaanAdapter(this, arrBelanja);
        list.setAdapter(adapater);

        fetchBarang();
        spin = (Spinner) findViewById(R.id.spinner_nota);
//        for (int i=0;i<arrBarang.size();i++){
//            arr.add(arrBarang.get(i).getNama());
//        }
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x=spin.getSelectedItem().toString();
                int f=0;
                for (int i=0;i<arr.size();i++){
                    if(arrBarang.get(i).getNama().equals(x)){
                        f=i;
                    }
                }
                String xnama=arrBarang.get(f).getNama().toString();
                int xjumlah=arrBarang.get(f).getJumlah();
                int xharga=arrBarang.get(f).getHarga();

                Belanjaan belanja=new Belanjaan(xnama,xjumlah,xharga);
                arrBelanja.add(belanja);
                adapater.notifyDataSetChanged();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNota();
            }
        });
    }

    public void addItemSpinner() {
//        spin= (Spinner) findViewById(R.id.spinner_nota);
//        for (int i=0;i<arrBarang.size();i++){
//
//            arr.add(arrBarang.get(i).getNama());
//        }
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, arr);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(dataAdapter);
    }

    public void addNota() {
        String url = "https://www.hanschrs.com/android/uas/addNota.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(TambahNota.this, message, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(TambahNota.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("nama", nama_param);
//                params.put("deskripsi", deskripsi_param);
//                params.put("harga", harga_param);
//                params.put("jumlah", jumlah_param);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void fetchBarang() {
        String url = "https://www.hanschrs.com/android/uas/fetchBarang.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int statusCode = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(TambahNota.this, message, Toast.LENGTH_SHORT).show();
                            if (statusCode == 1) {
                                JSONArray jsonArray = jsonObject.getJSONArray("databarang");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject obj_user = jsonArray.getJSONObject(j);
                                    String idBarang = (obj_user.get("id")).toString();
                                    String namaBarang = (obj_user.get("nama")).toString();
                                    String deskripsiBarang = (obj_user.get("deskripsi")).toString();
                                    int hargaBarang = Integer.parseInt((obj_user.get("harga")).toString());
                                    int jumlahBarang = Integer.parseInt((obj_user.get("jumlah")).toString());
                                    Barang barang = new Barang(idBarang, namaBarang, deskripsiBarang, hargaBarang, jumlahBarang);
                                    arrBarang.add(barang);
                                    arr.add(barang.getNama());
                                    counter++;
                                }
                            }
                            dataAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahNota.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
//


}
