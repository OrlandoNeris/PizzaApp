package com.example.usuario.pizzasistem;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by USUARIO on 19/10/2017.
 */

public class PeticionRegistro extends StringRequest {

    private static final String PETICION_REGISTRO_URL = "http://192.168.1.10/Registro.php";
    private Map<String, String> params;
    public PeticionRegistro(String Usuario, String Contrasena, String Email, Response.Listener<String> listener){
        super(Method.POST, PETICION_REGISTRO_URL, listener, null);
        params = new HashMap<>();
        params.put("Usuario",Usuario);
        params.put("Contrasena",Contrasena);
        params.put("Email",Email);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
