package com.itt.ceatm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.itt.ceatm.Clases.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_Login extends AppCompatActivity implements View.OnClickListener {

    EditText user, password;
    RequestQueue Rq;
    JsonRequest Jrq;
    Intent usuarios = null;
    Button login;
    EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        Rq = Volley.newRequestQueue(this);
        usuario = findViewById(R.id.et_user);
        contrasena = findViewById(R.id.password2);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                iniciarSesion();
                break;
        }
    }

    public void iniciarSesion(){
        String url = "https://qr-pay.000webhostapp.com/ceatm/Login.php?usuario="+usuario.getText().toString()+"&password="+contrasena.getText().toString();
        Jrq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Login login = new Login();
                JSONArray jsonArray =response.optJSONArray("login");
                JSONObject jsonObject = null;

                try {
                    jsonObject = jsonArray.getJSONObject(0);
                    login.setUsuario(jsonObject.optString("usuario"));
                    login.setAcceso(jsonObject.optString("id_acceso"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(login.getAcceso().equals("3")){
                    usuarios = new Intent(activity_Login.this, Activity_Administrador.class);
                    usuarios.putExtra(Activity_Administrador.lusuarios, login.getUsuario());//se envia el usuario al BottomAtivityAdministrador
                }else if(login.getAcceso().equals("2")){
                }else if(login.getAcceso().equals("1")){
                    usuarios = new Intent(activity_Login.this, Activity_Atleta.class);
                    usuarios.putExtra(Activity_Administrador.lusuarios, login.getUsuario());//se envia el usuario al BottomAtivityAtleta
                }else {
                    Toast.makeText(getApplicationContext(),"¡NO Estas registrado!",Toast.LENGTH_SHORT).show();
                }
                startActivity(usuarios);
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Usuario o contraseña incorectos.",Toast.LENGTH_SHORT).show();
            }
        });
        Rq.add(Jrq);
    }

}
