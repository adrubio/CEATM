package com.itt.ceatm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
    }
//Hola esta es una prueba

    @Override
    public void onClick(View v) {

        Intent usuarios = null;

        switch (v.getId()){
            case R.id.btnAdmin:
                usuarios = new Intent(activity_Login.this, Activity_Administrador.class);
                break;

            case R.id.btnAtleta:
                usuarios = new Intent(activity_Login.this, Activity_Atleta.class);
                break;
        }

        startActivity(usuarios);
    }
}
