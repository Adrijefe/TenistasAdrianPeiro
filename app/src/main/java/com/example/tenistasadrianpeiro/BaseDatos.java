package com.example.tenistasadrianpeiro;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tenista.class}, version = 1)
public abstract class BaseDatos extends RoomDatabase {

    private static BaseDatos INSTANCE;

    public static BaseDatos getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            BaseDatos.class, "db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract TenistaDAO getTenistaDao();
}
