package com.example.marvelapi.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.marvelapi.R;
import com.example.marvelapi.model.Result;
import com.squareup.picasso.Picasso;

import static com.example.marvelapi.view.activities.DetailsActivity.DETAILS_KEY;
import static com.example.marvelapi.view.activities.MainActivity.COMICS_KEY;

public class ImageActivity extends AppCompatActivity {

    private Button bntClose;
    private ImageView ivComicsDetail;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initViews();

        if (getIntent() != null){
            Bundle bundle = getIntent().getExtras();
            result = bundle.getParcelable(DETAILS_KEY);
            String imageURL = result.getThumbnail()
                    .getPath().replace("http://", "https://");
            Picasso.get().load(imageURL+"."
                    +result.getThumbnail().getExtension()).into(ivComicsDetail);
        }

        bntClose.setOnClickListener(v -> finish());

    }

    private void initViews(){
        bntClose = findViewById(R.id.buttonClose);
        ivComicsDetail = findViewById(R.id.imageViewComicsBundleDetail);
    }
}
