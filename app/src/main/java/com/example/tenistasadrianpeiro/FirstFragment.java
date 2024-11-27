package com.example.tenistasadrianpeiro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tenistasadrianpeiro.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayAdapter<Tenista> adapter;
    private TenistaViewModel model;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater);
        View view = binding.getRoot();

        // Configuramos el adaptador para que nos salga la imagen y el nombre al inicio
        ArrayList<Tenista> tenistas = new ArrayList<>();
        adapter = new TenistaAdapter(
                getContext(),
                R.layout.tenistas_list_item,
                tenistas
        );
        binding.listTenistas.setAdapter(adapter);

        // Configuramos para cuando le damos click a un tenista nos
        // salga toda la informacion que le damos o mas bien hemos puesto
        binding.listTenistas.setOnItemClickListener((adapterView, view1, i, l) -> {
            Tenista tenista = adapter.getItem(i);
            Bundle args = new Bundle();
            args.putSerializable("Tenista", tenista);
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_tenistaDetails, args);
        });

        // Configuraramos el ViewModel
        model = new ViewModelProvider(this).get(TenistaViewModel.class);
        model.getTenistas().observe(getViewLifecycleOwner(), tenistasList -> {
            adapter.clear();
            adapter.addAll(tenistasList);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true); // Esto sirve para habilitar el menu de opciones
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu); // Inflar menú principal
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.REFRESH) {
            Toast.makeText(getContext(), "Se esta haciendo", Toast.LENGTH_SHORT).show();
            Log.d("XXX", "CLICKK!!!");
            refresh();

        } else if (id == R.id.settings) {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        model.reload(); // Recargargamos los  datos desde el ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
