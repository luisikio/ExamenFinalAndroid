package com.example.examenfinal2022.DataBase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinal2022.dao.LibroDao;

public abstract class  AppDatabase extends RoomDatabase {
    public abstract LibroDao userDao();

    public static AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "examenfinal2022.DataBase")
                .allowMainThreadQueries()
                .build();
    }
}
