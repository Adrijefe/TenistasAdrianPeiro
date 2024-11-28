package com.example.tenistasadrianpeiro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class TenistaAdapter extends ArrayAdapter<Tenista> {
    @NonNull
@Override

public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obtengo la posicion del tenista
    Tenista tenista =getItem(position);
    //Hago un layaoutInflater para las vistas
    LayoutInflater inflater = LayoutInflater.from(getContext());
    // Inflamos el layout personalizado, la lista de los tenistas para cada elemento
    convertView= inflater.inflate(R.layout.tenistas_list_item,parent,false);
    //Obtengo el nombre de TextView del nombre del tenista
    TextView TenistaName =convertView.findViewById(R.id.textTenistaName);
    //I con esto establezco el nombre del tenista en el TextView
    TenistaName.setText(tenista.getName());
    // I a qui hago lo mismo que el nombre pero con la imagen
    ImageView imgTenista =convertView.findViewById(R.id.imageView);
    // I aqui volvemos a hacer un glide para coger la imagen
    Glide.with(getContext()).load(tenista.getSprite()).into(imgTenista);


    //Devolvemos la lista completa
    return convertView;


}
    public TenistaAdapter(@NonNull Context context, int resource, @NonNull List<Tenista> objects) {
        super(context, resource, objects);
    }
}
