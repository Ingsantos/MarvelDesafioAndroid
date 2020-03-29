package com.example.marvelapi.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelapi.R;
import com.example.marvelapi.model.Result;
import com.example.marvelapi.view.interfaces.ComicsListener;
import com.squareup.picasso.Picasso;

import static com.example.marvelapi.view.activities.MainActivity.COMICS_KEY;

public class DetailsActivity extends AppCompatActivity{

    private Button btnBack;
    private ImageView ivBackground;
    private ImageView ivComics;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvPublishedDate;
    private TextView tvPrice;
    private TextView tvPages;
    private Result result;
    private ComicsListener comicsListenerComics;
    public static final String DETAILS_KEY = "details";

    public DetailsActivity(Result result, ComicsListener comicsListenerComics) {
        this.result = result;
        this.comicsListenerComics = comicsListenerComics;
    }

    public DetailsActivity() { }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();

        if (getIntent() != null){
            Bundle bundle = getIntent().getExtras();
            result = bundle.getParcelable(COMICS_KEY);
            String imageURL = result.getThumbnail()
                    .getPath().replace("http://", "https://");
            Picasso.get().load(imageURL+"."
                    +result.getThumbnail().getExtension()).into(ivBackground);
            Picasso.get().load(imageURL+"."
                    +result.getThumbnail().getExtension()).into(ivComics);
            tvTitle.setText(result.getTitle());
            tvDescription.setText(result.getDescription());
            tvPublishedDate.setText(result.getDates().get(0).getDate());
            tvPrice.setText(result.getPrices().get(0).getPrice().toString());
            tvPages.setText(result.getPageCount().toString());
        }

        btnBack.setOnClickListener(v -> finish());

        ivComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DETAILS_KEY, result);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initViews (){
        btnBack = findViewById(R.id.buttonBack);
        ivBackground = findViewById(R.id.imageViewBackground);
        ivComics = findViewById(R.id.imageViewComicsBundle);
        tvTitle = findViewById(R.id.textViewTitle);
        tvDescription = findViewById(R.id.textViewDescription);
        tvPublishedDate = findViewById(R.id.textViewDate);
        tvPrice = findViewById(R.id.textViewValue);
        tvPages = findViewById(R.id.textViewPageNumber);
    }

}
