package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.examenfinal2022.DataBase.AppDatabase;
import com.example.examenfinal2022.adapters.libroAdapter;
import com.example.examenfinal2022.dao.LibroDao;
import com.example.examenfinal2022.entities.Libro;
import com.example.examenfinal2022.services.servicesWeb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mostrarLibros extends AppCompatActivity {

    public RecyclerView rv;
    FloatingActionButton btn;
    List<Libro> libros= new ArrayList<>();
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_libros);

       // db = AppDatabase.getDatabase(getApplicationContext());

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://62c370d9ff594c656773c4b9.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
     //   sincronizar(libros);
        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Libro>> call=services.getContacts();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("mostrando", new Gson().toJson(response.body()));
                    Log.i("app", "Respuesta correcta");

                    libros=response.body();

                   // saveInDatabase(libros);
                    libroAdapter adapter=new libroAdapter(libros);


                    rv= findViewById(R.id.rvLibro);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }


            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {

            }
        });
        btn=findViewById(R.id.btnCrear);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mostrarLibros.this,crear_libro.class);
                startActivity(intent);
            }
        });

    }
//    private void saveInDatabase(List<Libro> libros) {
//        LibroDao dao = db.userDao();
//        for (Libro libro : libros) {
//            dao.create(libro);
//        }
//    }
//    private void sincronizar(List<Libro> libros) {
//        LibroDao dao = db.userDao();
//        List<Libro> libross = dao.getAll();
//        Log.i("datos", new Gson().toJson(libross));
//    }
}