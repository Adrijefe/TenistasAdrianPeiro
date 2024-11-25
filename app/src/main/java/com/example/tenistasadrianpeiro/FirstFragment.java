package com.example.tenistasadrianpeiro ;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tenistasadrianpeiro.R;
import com.example.tenistasadrianpeiro.Tenista;
import com.example.tenistasadrianpeiro.TenistaAPI;
import com.example.tenistasadrianpeiro.TenistaAdapter;
import com.example.tenistasadrianpeiro.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList<Tenista> tenistas;
    ArrayAdapter<Tenista> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        tenistas = new ArrayList<>();
        setHasOptionsMenu(true);
        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tenistas = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Tenista> pokemons = TenistaAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Tenista p : pokemons) {
                    tenistas.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });

        adapter = new TenistaAdapter(getContext(),
                R.layout.tenistas_list_item,
                tenistas);
        binding.listTenistas.setAdapter(adapter);


        binding.listTenistas.setOnItemClickListener((adapter, fragment, i, l) -> {
                    Tenista capital = (Tenista) adapter.getItemAtPosition(i);
                    Bundle args = new Bundle();
                    args.putSerializable("Tenista", capital);

                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_tenistasDetailsFrag, args);
                }
        );

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.REFRESH){
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Tenista> pokemons = TenistaAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Tenista p : pokemons) {
                    Log.d("XXX", p.toString());
                    pokemons.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });


        binding.listTenistas.setOnItemClickListener((adapterView, fragment, i, l) -> {
            Tenista capital = adapter.getItem(i);
            Bundle args = new Bundle();
            args.putSerializable("Capital", capital);
            Log.d("XXX", capital.toString());
        });
    }
}