package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.examenfinal2022.entities.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class favoritosLibros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_libros);
        Intent intent=getIntent();
        String tit=intent.getStringExtra("favoritos");

   //     Log.e("fav",tit);
        TextView tvTitulo=findViewById(R.id.tvTitfav);
        TextView tvmResumen=findViewById(R.id.tvResumenmin);



        tvTitulo.setText(tit);

    }
}