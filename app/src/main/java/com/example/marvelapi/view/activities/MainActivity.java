package com.example.marvelapi.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.marvelapi.R;
import com.example.marvelapi.model.Result;
import com.example.marvelapi.view.adapters.MarvelAdapter;
import com.example.marvelapi.view.interfaces.ComicsListener;
import com.example.marvelapi.viewmodel.MarvelViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ComicsListener {
    private RecyclerView mRecyclerView;
    private MarvelAdapter marvelAdapter;
    private List<Result> comicsList = new ArrayList<>();
    public static final String COMICS_KEY = "comics";

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
        marvelAdapter = new MarvelAdapter(comicsList, this);
        mRecyclerView.setAdapter(marvelAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void SendComicsOnClick(Result result) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMICS_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
