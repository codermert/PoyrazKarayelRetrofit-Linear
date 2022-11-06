package com.poyrazkarayel.retrofit.service;

import com.poyrazkarayel.retrofit.model.PoyrazModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PoyrazAPI {

    //GET, POST, UPDATE, DELETE

    //URL BASE -> www.website.com
    // GET -> price?key=xxx

    //https://raw.githubusercontent.com/
    //codermert/image-name-changer/main/poyrazkarayel.json


    @GET("codermert/image-name-changer/main/poyrazkarayel.json")
    Observable<List<PoyrazModel>> getData();

    //Call<List<PoyrazModel>> getData();

}
