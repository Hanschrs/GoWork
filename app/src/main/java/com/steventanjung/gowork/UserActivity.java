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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

public class UserActivity extends AppCompatActivity {
    Button addBtn;
    ListView userlist;
    ArrayList<User> arrUser = new ArrayList<>();
    UserAdapter adapter;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_user);

        addBtn = findViewById(R.id.addUser_user);
        userlist = findViewById(R.id.listUser_user);

        adapter = new UserAdapter(this, arrUser);
        userlist.setAdapter(adapter);

        registerForContextMenu(userlist);

        //fetch userlist here
        fetchUser();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIntent = new Intent(UserActivity.this, AddUserActivity.class);
                startActivityForResult(addUserIntent, 1);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_user, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.userItem_edit) {

        } else if (id == R.id.userItem_delete) {
            String usernameDelete = (arrUser.get(info.position)).getUsername();
            deleteUser(usernameDelete);
            arrUser.remove(info.position);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    private void fetchUser() {
        String url = "https://www.hanschrs.com/android/uas/fetchUser.php";
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
                                JSONArray jsonArray = jsonObject.getJSONArray("datauser");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject obj_user = jsonArray.getJSONObject(j);
                                    String usernameObject = (obj_user.get("username")).toString();
                                    String password = "lolllll";
                                    String role = (obj_user.get("role")).toString();
                                    User user = new User(usernameObject, password, role);
                                    arrUser.add(user);
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
                Toast.makeText(UserActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void deleteUser(final String usernameChosen) {
        String url = "https://www.hanschrs.com/android/uas/deleteUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", usernameChosen);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1) {
            if (resultCode== Activity.RESULT_OK) {
                arrUser.clear();
                fetchUser();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
