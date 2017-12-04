package com.steventanjung.gowork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

/**
 * Created by Hans on 11/29/2017.
 */

public class AddUserActivity extends AppCompatActivity {

    Button btnAddUser;
    EditText username, password;
    CheckBox isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        btnAddUser = findViewById(R.id.button_tambah);
        username = findViewById(R.id.username_addUser);
        password = findViewById(R.id.editText_password_user);
        isAdmin = findViewById(R.id.checkbbox_isAdmin_user);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(username.getText().toString().equals("")) && !(password.getText().toString().equals(""))) {
                    String usernameInput = username.getText().toString();
                    String passwordInput = username.getText().toString();
                    boolean isAdminInput;
                    if (isAdmin.isChecked()) {
                        isAdminInput = true;
                    } else {
                        isAdminInput = false;
                    }

                    addUser(usernameInput, passwordInput, isAdminInput);
                } else {
                    Toast.makeText(AddUserActivity.this, "Please input username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addUser(final String username_param, final String password_param, final boolean isAdmin_param) {
        String url = "https://www.hanschrs.com/android/uas/addUser.php";
//        btnAddUser.setText("masuk1");
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(AddUserActivity.this, message, Toast.LENGTH_SHORT).show();
//                    btnAddUser.setText("masuk2");

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
                Toast.makeText(AddUserActivity.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username_param);
                params.put("password", password_param);
                if (isAdmin_param) {
                    params.put("isAdmin", "true");
                } else {
                    params.put("isAdmin", "false");
                }
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}


