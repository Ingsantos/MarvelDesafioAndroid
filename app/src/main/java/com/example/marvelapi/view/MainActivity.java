package com.example.marvelapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.marvelapi.R;
import com.example.marvelapi.model.StaticComicsModel;
import com.example.marvelapi.view.adapters.MarvelAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MarvelAdapter marvelAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        marvelAdapter = new MarvelAdapter(getStaticComicsModel());
        mRecyclerView.setAdapter(marvelAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private List<StaticComicsModel> getStaticComicsModel () {

        List<StaticComicsModel> comicsList = new ArrayList<>();
        comicsList.add( new StaticComicsModel(
                "https://i.annihil.us/u/prod/marvel/i/mg/9/50/4bc39d33d6f05",
                ".jpg","#61"));
        comicsList.add( new StaticComicsModel(
                "https://i.annihil.us/u/prod/marvel/i/mg/6/10/4bc391028a8d3",
                ".jpg","#42"));
        comicsList.add( new StaticComicsModel(
                "https://i.annihil.us/u/prod/marvel/i/mg/8/e0/5d56d0b36b70a",
                ".jpg","#01"));

        return comicsList;
    }
}
