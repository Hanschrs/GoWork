package com.steventanjung.gowork;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class EditBarangActivity extends AppCompatActivity {

    TextView nama;
    EditText harga,jumlah,deskripsi;
    Button edit;
    Barang barang;
    BarangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        nama =  (TextView) findViewById(R.id.namabarang_edit);
        harga = (EditText) findViewById(R.id.hargabarang_edit);
        jumlah = (EditText) findViewById(R.id.jumlahbarang_edit);
        deskripsi = (EditText) findViewById(R.id.deskripsibarang_edit);
        edit = (Button) findViewById(R.id.editbarang_btn);

        Intent intent = getIntent();
        String namax= intent.getStringExtra("nama");
        String descx= intent.getStringExtra("desc");
        String hargax= intent.getStringExtra("harga");
        String jumlahx= intent.getStringExtra("jumlah");

        nama.setText(namax);
        deskripsi.setText(descx);
        harga.setText(hargax);
        jumlah.setText(jumlahx);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaInput=nama.getText().toString();
                String deskripsiInput=deskripsi.getText().toString();
                String hargaInput=String.valueOf(harga.getText().toString());
                String jumlahInput=String.valueOf(jumlah.getText().toString());

                editBarang(namaInput, deskripsiInput, hargaInput, jumlahInput);
            }
        });
    }


    public void editBarang(final String nama_param,final String deskripsi_param, final String harga_param, final String jumlah_param) {
        String url = "https://www.hanschrs.com/android/uas/editBarang.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(EditBarangActivity.this, message, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(EditBarangActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama",nama_param);
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
