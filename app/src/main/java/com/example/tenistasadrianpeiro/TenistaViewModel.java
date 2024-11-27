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
        this.tenistaDAO = baseDatos.getTenistaDao();
    }


    public LiveData<List<Tenista>> getTenistas() {
        return tenistaDAO.getTenistas();
    }

    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }
    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );

            TenistaAPI api = new TenistaAPI();
            ArrayList<Tenista> result;

            result = api.buscar();

            Log.d("XXX", result.toString());
            tenistaDAO.deleteTenistas();
            tenistaDAO.addTenistas(result);

            return null;
        }
    }
}
