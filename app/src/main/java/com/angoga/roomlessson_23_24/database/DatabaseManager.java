package com.angoga.roomlessson_23_24.database;

import android.content.Context;

import androidx.room.RoomDatabase;

public class DatabaseManager {
    private static DatabaseManager manager;
    private AppDatabase database;

    public DatabaseManager(Context context) {
        database = new RoomDatabase
                .Builder<AppDatabase>(context, AppDatabase.class, "PublicationAppDB")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public static DatabaseManager getInstance(Context context) {
        if (manager == null) manager = new DatabaseManager(context);
        return manager;
    }
}
