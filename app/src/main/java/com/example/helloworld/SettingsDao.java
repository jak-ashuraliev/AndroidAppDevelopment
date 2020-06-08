package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM settings WHERE email = :email")
    LiveData<List<Settings>> loadByEmail(String[] email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings... settings);
}