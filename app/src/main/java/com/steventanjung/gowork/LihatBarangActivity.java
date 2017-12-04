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
import android.widget.Button;
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

public class LihatBarangActivity extends AppCompatActivity {
    Button addBtn;
    ListView baranglist;
    ArrayList<Barang> arrBarang= new ArrayList<>();
    BarangAdapter adapter;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        addBtn = findViewById(R.id.addBarang_barang);
        baranglist = findViewById(R.id.listBarang_barang);

        adapter = new BarangAdapter(this, arrBarang);
        baranglist.setAdapter(adapter);

        registerForContextMenu(baranglist);

        //fetch userlist here
        fetchBarang();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIntent = new Intent(LihatBarangActivity.this, BarangActivity.class);
                startActivityForResult(addUserIntent, 1);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_barang, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.barangItem_edit) {

        } else if (id == R.id.barangItem_delete) {
            String nameDelete = (arrBarang.get(info.position)).getNama();
            deleteBarang(nameDelete);
            arrBarang.remove(info.position);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
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
                            Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (statusCode == 1) {
                                JSONArray jsonArray = jsonObject.getJSONArray("databarang");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject obj_user = jsonArray.getJSONObject(j);
                                    String idBarang = (obj_user.get("id")).toString();
                                    String namaBarang = (obj_user.get("nama")).toString();
                                    String deskripsiBarang = (obj_user.get("deskripsi")).toString();
                                    String hargaBarang = (obj_user.get("harga")).toString();
                                    String jumlahBarang = (obj_user.get("jumlah")).toString();
                                    Barang barang = new Barang(idBarang, namaBarang, deskripsiBarang, hargaBarang, jumlahBarang);
                                    arrBarang.add(barang);
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
//                showSpace.setText("ERROR");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void deleteBarang(final String barangChosen) {
//        String url = "https://www.hanschrs.com/android/uas/deleteUser.php";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(response);
//                    int statusCode = jsonObject.getInt("code");
//                    String message = jsonObject.getString("message");
//                    Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(UserActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
//                Log.e("error", error.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("username", usernameChosen);
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1) {
            if (resultCode== Activity.RESULT_OK) {
                arrBarang.clear();
                fetchBarang();
            }
        }
    }
}
