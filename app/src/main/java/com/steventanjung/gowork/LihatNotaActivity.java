package com.steventanjung.gowork;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class LihatNotaActivity extends AppCompatActivity {
    ListView notalist;
    ArrayList<Nota> arrNota = new ArrayList<>();
    NotaAdapter adapter;
    int counter = 0;
    TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_lihat_nota);

        judul = findViewById(R.id.title_nota);
        notalist = findViewById(R.id.listNota_nota);

        adapter = new NotaAdapter(this, arrNota);
        notalist.setAdapter(adapter);

        registerForContextMenu(notalist);

//        fetch baranglist here
        fetchNota();

        notalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nota nota = (Nota) adapterView.getItemAtPosition(i);
                Intent belanjaanIntent = new Intent(LihatNotaActivity.this, DaftarBelanjaActivity.class);
                belanjaanIntent.putExtra("no_nota", nota.getNo_nota());
                startActivity(belanjaanIntent);
            }
        });
    }


    private void fetchNota() {
        String url = "https://www.hanschrs.com/android/uas/fetchNota.php";
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
                            Toast.makeText(LihatNotaActivity.this, message, Toast.LENGTH_LONG).show();
                            if (statusCode==1) {
                                JSONArray jsonArray = jsonObject.getJSONArray("datanota");
                                for (int j = 0; j<jsonArray.length(); j++) {
                                    JSONObject obj_nota = jsonArray.getJSONObject(j);
                                    int id = Integer.parseInt((obj_nota.getString("id")));
                                    String tanggal = obj_nota.getString("tanggal");
                                    ArrayList<Belanjaan> arrNotaDB = new ArrayList<Belanjaan>();
                                    arrNotaDB.add(new Belanjaan("hans", 1, 1));
                                    int no_nota = Integer.parseInt((obj_nota.getString("no_nota")));
                                    int jumlah_harga = Integer.parseInt((obj_nota.getString("jumlah_harga")));
                                    int diskon = Integer.parseInt((obj_nota.getString("diskon")));
                                    Nota nota = new Nota(tanggal, null, id, no_nota, diskon, jumlah_harga);
                                    arrNota.add(nota);
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
                Toast.makeText(LihatNotaActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        });

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
