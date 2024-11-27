package com.example.tenistasadrianpeiro;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TenistaAPI {
    private final String Base_url = "https://yaohroqydkqtavmsbohg.supabase.co/rest/v1/Tenistas?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlhb2hyb3F5ZGtxdGF2bXNib2hnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjIwMzEsImV4cCI6MjA0NzIzODAzMX0.U7kZF008-AJaZd0ebkbiy2m5M4KzacxerFHalvewUks";
    
    String getNames(){
        Uri builtUri = Uri.parse(Base_url)
                .buildUpon()
                .appendPath("tenistas")
                .appendQueryParameter("limit","11")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    @OptIn(markerClass = UnstableApi.class) public static ArrayList<Tenista> buscar() {
        ArrayList<Tenista> tenistas = new ArrayList<>();
        //Realizamos  una solicitud HTTP para obtener una lista de tenistas en formato JSON,
        // la procesa y devuelve una lista de objetos Tenista
        try {
            String response = HttpUtils.get("https://yaohroqydkqtavmsbohg.supabase.co/rest/v1/Tenistas?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlhb2hyb3F5ZGtxdGF2bXNib2hnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjIwMzEsImV4cCI6MjA0NzIzODAzMX0.U7kZF008-AJaZd0ebkbiy2m5M4KzacxerFHalvewUks");
            //JSONObject jsonObject = new JSONObject(response);
            //JSONArray resultado = jsonObject.getJSONArray("");
            JSONArray resultado = new JSONArray(response);

            for (int i = 0; i < resultado.length(); i++) {
                JSONObject tenistaObj = resultado.getJSONObject(i);
                String name = tenistaObj.getString("nombre");

                Integer id = tenistaObj.getInt("id");
                String nombre = tenistaObj.getString("nombre");
                Integer age = tenistaObj.getInt("edad");
                String titulos = tenistaObj.getString("titulosGrandSlam");
                String nacimiento = tenistaObj.getString("fechanacimiento");
                String imagen = tenistaObj.getString("imagen");

                Tenista tenista = new Tenista(id,nombre,age,titulos,nacimiento,imagen);
                tenistas.add(tenista);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return tenistas;
    }

    private String doCall (String url){

        // Realizamos una solicitud HTTP a la URL
        try{
            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
