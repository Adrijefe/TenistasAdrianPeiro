package com.example.tenistasadrianpeiro;

import androidx.lifecycle.LiveData;
import  androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface TenistaDAO {
    @Query("select * from tenista")
    LiveData<List<Tenista>> getTenistas();

    @Insert
    void addTenista(Tenista tenista);

    @Insert
    void addTenistas (List<Tenista> tenistas);

    @Delete
    void deleteTenista(Tenista tenista);

    @Query("Delete from Tenista")
    void deleteTenistas();

}

