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
import com.example.marvelapi.model.StaticComicsModel;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder>{

    private List<StaticComicsModel> listStaticComicsModels;

    public MarvelAdapter(List<StaticComicsModel> listStaticComicsModels) {
        this.listStaticComicsModels = listStaticComicsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StaticComicsModel staticComicsModel = listStaticComicsModels.get(position);
        holder.bind(staticComicsModel);
    }

    @Override
    public int getItemCount() {
        return listStaticComicsModels.size();
    }

    public void comicsListUpdate (List<StaticComicsModel> newList){
        if (this.listStaticComicsModels.isEmpty())
            this.listStaticComicsModels = newList;
        else{
            this.listStaticComicsModels.addAll(newList);
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

        public void bind (StaticComicsModel staticComicsModel){
            mTextView.setText(staticComicsModel.getComicsNumber());
            Picasso.get().load(staticComicsModel.getImageURL()+staticComicsModel.getImageExtension())
                    .into(mImageView);
            Log.i(staticComicsModel.getImageURL()+staticComicsModel.getImageExtension(), "imageString");
        }
    }

}
