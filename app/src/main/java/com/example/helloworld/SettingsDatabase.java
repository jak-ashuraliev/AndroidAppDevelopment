package com.example.helloworld;
import androidx.room.Database;

import androidx.room.RoomDatabase;

@Database(entities = {Settings.class}, version = 2, exportSchema = false)
public abstract class SettingsDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}
