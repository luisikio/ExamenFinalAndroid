package com.example.examenfinal2022.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.examenfinal2022.entities.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Query("SELECT * FROM libros")
    List<Libro> getAll();

    @Query("SELECT * FROM libros WHERE id=:id")
    Libro find(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Libro libros);
}
