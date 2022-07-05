package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinal2022.DataBase.AppDatabase;
import com.example.examenfinal2022.dao.LibroDao;
import com.example.examenfinal2022.entities.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class minListLibros extends AppCompatActivity {


    ImageView imgA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_list_libros);



        String pokemonJson = getIntent().getStringExtra("Pokemon");
        Libro libro = new Gson().fromJson(pokemonJson,Libro.class);

        imgA=findViewById(R.id.imgAvatar);
        TextView tvTitulo=findViewById(R.id.tvTitulomin);
        TextView tvmResumen=findViewById(R.id.tvResumenmin);


        Picasso.get().load(libro.caratula).into(imgA);

        tvTitulo.setText(libro.titulo);
        tvmResumen.setText(libro.resumen);

        Button btn1T=findViewById(R.id.btnEditar);
        Button btn2T=findViewById(R.id.btnDondeComprar);
        Button btn3T=findViewById(R.id.btnAniadirFav);

        btn1T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
//                intent.putExtra("tienda1",libro.tienda1);
//                startActivity(intent);
                //EDITAR
            }
        });

        btn2T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("tienda1",libro.tienda1);
                startActivity(intent);
            }
        });
        btn3T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),favoritosLibros.class);
                intent.putExtra("favoritos",libro.titulo);
                libro.favoritos=true;
                Log.e("cd", String.valueOf(libro.favoritos));
                startActivity(intent);
            }
        });


    }

}