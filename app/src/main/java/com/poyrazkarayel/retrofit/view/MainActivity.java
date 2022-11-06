package com.poyrazkarayel.retrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.CrossProcessCursor;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poyrazkarayel.retrofit.R;
import com.poyrazkarayel.retrofit.adapter.RecyclerViewAdapter;
import com.poyrazkarayel.retrofit.model.PoyrazModel;
import com.poyrazkarayel.retrofit.service.PoyrazAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<PoyrazModel> poyrazModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // https://raw.githubusercontent.com/codermert/image-name-changer/main/poyrazkarayel.json

        recyclerView = findViewById(R.id.recyclerView);

        //Retrofit & JSON

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        loadData();

    }

    private void loadData(){

        final PoyrazAPI poyrazAPI = retrofit.create(PoyrazAPI.class);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(poyrazAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));

    }


    private void handleResponse(List<PoyrazModel> cryptoModelList) {
        poyrazModels = new ArrayList<>(cryptoModelList);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(poyrazModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}