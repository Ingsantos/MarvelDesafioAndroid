package com.example.marvelapi.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.marvelapi.R;
import com.example.marvelapi.model.Result;
import com.example.marvelapi.view.interfaces.ComicsListener;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder>{
    private List<Result> comicsModelsList;
    private ComicsListener comicsListenerComics;

    public MarvelAdapter(List<Result> comicsModelsList, ComicsListener comicsListenerComics) {
        this.comicsModelsList = comicsModelsList;
        this.comicsListenerComics = comicsListenerComics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result staticComicsModel = comicsModelsList.get(position);
        holder.bind(staticComicsModel);
        holder.itemView.setOnClickListener(v -> {
            comicsListenerComics.SendComicsOnClick(staticComicsModel);
        });
    }

    @Override
    public int getItemCount() {
        return comicsModelsList.size();
    }

    public void comicsListUpdate (List<Result> newList){
        if (this.comicsModelsList.isEmpty())
            this.comicsModelsList = newList;
        else{
            this.comicsModelsList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewComics);
            mTextView = itemView.findViewById(R.id.textViewComics);
        }

        public void bind (Result staticComicsModel){
            String imageURL = staticComicsModel.getThumbnail()
                    .getPath().replace("http://", "https://");
            mTextView.setText("#"+staticComicsModel.getIssueNumber().toString());
            Picasso.get().load(imageURL+"."
                    +staticComicsModel.getThumbnail().getExtension()).into(mImageView);
        }
    }
}
