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
    Tenista tenista =getItem(position);

    LayoutInflater inflater = LayoutInflater.from(getContext());

    convertView= inflater.inflate(R.layout.tenistas_list_item,parent,false);

    TextView TenistaName =convertView.findViewById(R.id.textTenistaName);

    TenistaName.setText(tenista.getName());

    ImageView imgTenista =convertView.findViewById(R.id.imageView);


    Glide.with(getContext()).load(tenista.getSprite()).into(imgTenista);


    return convertView;

}
    public TenistaAdapter(@NonNull Context context, int resource, @NonNull List<Tenista> objects) {
        super(context, resource, objects);
    }
}
