package com.steventanjung.gowork;

import android.content.Context;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
//        checkSession();
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
    }

    public void loginProcess() {
        String url = "https://www.hanschrs.com/android/uas/login.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result_view.setText(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int statusCode = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    if (statusCode == 1) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        String FILENAME = "loggedin_user";
                        String string = jsonObject.toString();
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(string.getBytes());
                        fos.close();

                        Intent mainmenuIntent = new Intent(Login.this, MenuActivity.class);
                        mainmenuIntent.putExtra("userDetails", jsonObject.toString());
                        startActivity(mainmenuIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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

    public void checkSession() {
        try {
            FileInputStream in = null;
            in = openFileInput("loggedin_user");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jObjUser = new JSONObject(String.valueOf(sb));
            Intent mainmenuIntent = new Intent(Login.this, MenuActivity.class);
            mainmenuIntent.putExtra("userDetails", jObjUser.toString());
            startActivity(mainmenuIntent);
        } catch (FileNotFoundException e) {
            //do nothing
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
