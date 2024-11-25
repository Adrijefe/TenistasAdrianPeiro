package com.example.tenistasadrianpeiro;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.tenistasadrianpeiro.databinding.FragmentTenistasDetails2Binding;
import com.example.tenistasadrianpeiro.databinding.FragmentTenistasDetails2Binding;
import com.example.tenistasadrianpeiro.Tenista;
import com.example.tenistasadrianpeiro.TenistaDetailsViewModel;
import com.example.tenistasadrianpeiro.databinding.FragmentTenistasDetails2Binding;

public class TenistaDetailsFragment extends Fragment {

    private TenistaDetailsViewModel mViewModel;
    private Activity convertView;

    public static TenistaDetailsFragment newInstance() {
        return new TenistaDetailsFragment();
    }

    public TenistaDetailsFragment(){}

    private FragmentTenistasDetails2Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= FragmentTenistasDetails2Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args!=null){
            Tenista tenista = (Tenista) args.getSerializable("Tenista");
            if (tenista != null){
                Log.d("XXXX",tenista.toString());
                updateUi(tenista);
            }
        }

    }

    private void updateUi(Tenista tenista){
        Log.d("Tenista",tenista.toString());
        binding.TenistaNamesDetails .setText(tenista.getName());
        binding.TenistaAgeDetails.setText(tenista.getAge());
        binding.TenistaGrandSlamsDetails.setText(tenista.getGrandSlams());
        binding.TenistasDateDetails.setText(tenista.getDate());

        Glide.with(getContext()).load(tenista.getSprite()).into(binding.imageTenistaDetails);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      mViewModel = new ViewModelProvider(this).get(TenistaDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}