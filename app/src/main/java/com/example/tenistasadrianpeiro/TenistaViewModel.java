package com.example.tenistasadrianpeiro;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class TenistaViewModel extends AndroidViewModel {
    private final Application app;
    private final BaseDatos baseDatos;
    private final TenistaDAO tenistaDAO;

    public TenistaViewModel(Application app) {
        super(app);

        this.app = app;
        this.baseDatos = BaseDatos.getDatabase(this.getApplication());
        // Inicializa el DAO para acceder a los datos de la entidad Tenista.
        this.tenistaDAO = baseDatos.getTenistaDao();
    }

    // Retorna una lista  de tenistas desde la base de datos.
    public LiveData<List<Tenista>> getTenistas() {
        return tenistaDAO.getTenistas();
    }

    public void reload() {
        // Recargamos los datos desde la API
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }
    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            // Creo que no se utiliza
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );

            //Llamamos a la API para obtener una lista de tenistas
            TenistaAPI api = new TenistaAPI();
            ArrayList<Tenista> result;
            result = api.buscar();

            Log.d("XXX", result.toString());
            //Eliminamos los datos existentes y luego añadimos los nuevos datos de la API en la base de dato
            tenistaDAO.deleteTenistas();
            tenistaDAO.addTenistas(result);

            return null;
        }
    }
}
