package com.example.usuario.pizzasistem;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText Usuario, Contra, Correo;
    Button Registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Usuario = (EditText) findViewById(R.id.Edit_usuarioreg);
        Contra = (EditText) findViewById(R.id.Edit_contrareg);
        Correo = (EditText) findViewById(R.id.Edit_correoreg);

        Registrar = (Button) findViewById(R.id.Boton_registrar);


        Registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // CREO VARIABLES LOCALES PARA UTILIZAR DENTRO DE LA FUNCION ONCLICK

            final String usuario = Usuario.getText().toString();
            final String contra = Contra.getText().toString();
            final String correo = Correo.getText().toString();

        Response.Listener<String> respuestaListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonRespuesta = new JSONObject(response);
                    boolean success = jsonRespuesta.getBoolean("success");

                    if (success){

                        Intent intent = new Intent(Registro.this,MainActivity.class);
                        Registro.this.startActivity(intent);

                    }else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("ERROR EN EL REGISTRO")
                                .setNegativeButton("REINTENTAR",null)
                                .create().show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        // <---------------------------------------------

        // CREO UN OBJETO PETICIONREGISTRO QUE ESTA EN LA CLASE PETICIONREGISTRO
        // Y PASO COMO PARAMETROS LAS VARIABLES LOCALES DE LA FUNCION

        PeticionRegistro peticionRegistro = new PeticionRegistro(usuario, contra, correo, respuestaListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(peticionRegistro);

        // <------------------------------------------------

    }
}
