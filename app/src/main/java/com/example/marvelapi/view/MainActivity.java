package com.example.marvelapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.marvelapi.R;
import com.example.marvelapi.model.Result;
import com.example.marvelapi.model.StaticComicsModel;
import com.example.marvelapi.view.adapters.MarvelAdapter;
import com.example.marvelapi.viewmodel.MarvelViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MarvelAdapter marvelAdapter;
    private List<Result> comicsList = new ArrayList<>();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MarvelViewModel marvelViewModel = new MarvelViewModel(getApplication());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marvelViewModel.getComics();

        marvelViewModel.getComicsList().observe(this, (List<Result> comicsList) -> {
            marvelAdapter.comicsListUpdate(comicsList);
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        marvelAdapter = new MarvelAdapter(comicsList);
        mRecyclerView.setAdapter(marvelAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

}
