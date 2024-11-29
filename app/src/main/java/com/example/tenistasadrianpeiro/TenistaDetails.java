package com.example.tenistasadrianpeiro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.tenistasadrianpeiro.databinding.FragmentTenistaDetailsBinding;
import com.example.tenistasadrianpeiro.databinding.FragmentTenistasDetails2Binding;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link TenistaDetails#newInstance} factory method to
// * create an instance of this fragment.
// */
public class TenistaDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TenistaDetails() {
        // Required empty public constructor
    }
    private FragmentTenistaDetailsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTenistaDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtenemos los argumentos pasados al fragmento
        Bundle args = getArguments();
        if (args != null) {
            // Esto el objeto Tenista serializado.
            Tenista tenista = (Tenista) args.getSerializable("Tenista");

            if (tenista != null) {
                Log.d("XXX", tenista.toString());
                //Actualiza con los datos del Tenista
                updateApi(tenista);
            }

        }

    }

    private void updateApi(Tenista tenista) {
        Log.d("Tenista", tenista.toString());
        // Actualiza el TextView con el nombre del tenista.
        binding.TenistaNameDetails.setText("Nombre: " + tenista.getName().toString());
        // Actualiza el TextView la edad del tenista.
        binding.TenistaAgeDetail.setText("Edad : " + tenista.getAge());
        // Actualiza el TextView los grand Slams del tenista.
        binding.TenistaGrandSlamDetails.setText("Grand Slams: " + tenista.getGrandSlams());
        // Actualiza el TextView el nacimiento del tenista.
        binding.TenistasDateDetail.setText("Nacimiento : "+tenista.getDate());
        //Usamos el glide para coger y cargar la imagen del tenista
        Glide.with(getContext()).load(tenista.getSprite()).into(binding.imgTenistaDetails);
    }


}
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment TenistaDetails.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static TenistaDetails newInstance(String param1, String param2) {
//        TenistaDetails fragment = new TenistaDetails();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tenista_details, container, false);
//    }
