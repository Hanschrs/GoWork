package com.steventanjung.gowork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button login_button;
    EditText username_field, password_field;
    TextView result_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = (Button) findViewById(R.id.buttonLogin);
        username_field = (EditText) findViewById((R.id.inputUsername));
        password_field = (EditText) findViewById(R.id.inputPassword);
        result_view = (TextView) findViewById(R.id.textviewResult);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username_field.getText().toString().equals("") || password_field.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Username and Password cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    loginProcess();
                }
            }
        });

//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (username_field.getText().toString().equals("admin") && password_field.getText().toString().equals("admin")){
//                    Intent secondIntent
//                            = new Intent(Login.this,MenuActivity.class);
//                    startActivity(secondIntent);
//                }else{
//                    Intent secondIntent
//                            = new Intent(Login.this,Login.class);
//                    startActivity(secondIntent);
//                }
//            }
//        });
    }

    public void loginProcess() {
//        String url = "http://10.200.203.223/uasandroid/login.php";
        String url = "https://www.hanschrs.com/android/uas/login.php";

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(Login.this, "login success", Toast.LENGTH_SHORT).show();
//                Intent secondIntent = new Intent(Login.this, MenuActivity.class);
//                startActivity(secondIntent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Login.this, "Database connection not established", Toast.LENGTH_SHORT).show();
//                Log.e("error", error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("username", username_field.getText().toString());
//                params.put("password", password_field.getText().toString());
//                return super.getHeaders();
//            }
//        };
//
//        RequestQueue queue = Volley.newRequestQueue(Login.this);
//        queue.add(stringRequest);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        result_view.setText(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int statusCode = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            if (statusCode == 1) {
                                Toast.makeText(Login.this, "login success", Toast.LENGTH_SHORT).show();
                                Intent secondIntent = new Intent(Login.this, MenuActivity.class);
                                startActivity(secondIntent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Database connection not established", Toast.LENGTH_SHORT).show();
                Log.e("error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username_field.getText().toString());
                params.put("password", password_field.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
