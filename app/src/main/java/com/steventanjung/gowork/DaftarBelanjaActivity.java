package com.steventanjung.gowork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

public class DaftarBelanjaActivity extends AppCompatActivity {

    ListView lvBelanja;
    ArrayList<Belanjaan> arrBelanjaan = new ArrayList<>();
    BelanjaanAdapter adapter;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_daftar_belanja);

        lvBelanja = findViewById(R.id.lv_belanja);

//        arrBelanjaan.add(new Belanjaan("hans", 1, 1));

        adapter = new BelanjaanAdapter(this, arrBelanjaan);
        lvBelanja.setAdapter(adapter);

//        fetch baranglist here
        fetchBelanjaan();
    }

    private void fetchBelanjaan() {
        final int no_nota = getIntent().getIntExtra("no_nota", 0);
        String url = "https://www.hanschrs.com/android/uas/fetchNotaList.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            int statusCode = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(DaftarBelanjaActivity.this, message, Toast.LENGTH_LONG).show();
                            if (statusCode == 1) {
                                JSONArray jsonArray = jsonObject.getJSONArray("datanota");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject obj_nota = jsonArray.getJSONObject(j);
                                    String nama = obj_nota.getString("nama_barang");
                                    int jumlah = Integer.parseInt((obj_nota.getString("jumlah_barang")));
                                    int harga = Integer.parseInt((obj_nota.getString("harga_satuan")));
                                    Belanjaan belanjaan = new Belanjaan(nama, jumlah, harga);
                                    arrBelanjaan.add(belanjaan);
                                    counter++;
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DaftarBelanjaActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("no_nota", String.valueOf(no_nota));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
