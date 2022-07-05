package com.example.examenfinal2022.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libros")
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "caratula")
    public String caratula;
    @ColumnInfo(name = "titulo")
    public String titulo;
    @ColumnInfo(name = "resumen")
    public String resumen;
    @ColumnInfo(name = "tienda1")
    public String tienda1;
    @ColumnInfo(name = "favoritos")
    public boolean favoritos;

    public Libro() {
    }

    public Libro(int id, String caratula, String titulo, String resumen, String tienda1, boolean favoritos) {
        this.id = id;
        this.caratula = caratula;
        this.titulo = titulo;
        this.resumen = resumen;
        this.tienda1 = tienda1;
        this.favoritos = favoritos;
    }
}
