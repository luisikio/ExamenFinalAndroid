package com.example.examenfinal2022.services;

import com.example.examenfinal2022.entities.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface servicesWeb {
    @GET("libs/")
    Call<List<Libro>> getContacts();

    @GET("libs/{id}")
    Call<Libro> findContact(@Path("id") int id);

    @POST("libs")
    Call<Libro> create(@Body Libro libro);
}
