package com.example.examenfinal2022;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examenfinal2022.dao.LibroDao;
import com.example.examenfinal2022.entities.Libro;
import com.example.examenfinal2022.services.servicesWeb;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class crear_libro extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_PICK_IMAGE = 1001;

    static final int REQUEST_CAMERA_PERMISSION = 100;
    ImageView imgA;
    ImageView imgB;
    String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libro);

        Button btn = findViewById(R.id.btnCrearLib);
        imgA=findViewById(R.id.imgCrearLibro);
        EditText etTitulo=findViewById(R.id.etTitulo);
        EditText etresumen=findViewById(R.id.etResumen);
        EditText etTienda1=findViewById(R.id.etTienda1);
        EditText etUrl=findViewById(R.id.etCaratula);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://62c370d9ff594c656773c4b9.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);

                Libro libro = new Libro();


                libro.titulo=String.valueOf(etTitulo.getText());
                libro.resumen=String.valueOf(etresumen.getText());
                libro.tienda1=String.valueOf(etTienda1.getText());
                libro.caratula=String.valueOf(etUrl.getText());

                services.create(libro);
                Call<Libro> call = services.create(libro);

                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {

                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });
            }
        });
        Button btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){


            Bundle  extras = data.getExtras();
            Bitmap imageBitmap =(Bitmap) extras.get("data");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            imgA.setImageBitmap(imageBitmap);



        }

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null){

            try {

                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap imageBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                imgA.setImageBitmap(imageBitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }

    }
}