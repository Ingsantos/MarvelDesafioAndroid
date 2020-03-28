package com.example.marvelapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StaticComicsModel implements Parcelable {

    private String imageURL, imageExtension, comicsNumber;

    public StaticComicsModel(String imageURL, String imageExtension, String comicsNumber) {
        this.imageURL = imageURL;
        this.imageExtension = imageExtension;
        this.comicsNumber = comicsNumber;
    }

    protected StaticComicsModel(Parcel in) {
        imageURL = in.readString();
        imageExtension = in.readString();
        comicsNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageURL);
        dest.writeString(imageExtension);
        dest.writeString(comicsNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StaticComicsModel> CREATOR = new Creator<StaticComicsModel>() {
        @Override
        public StaticComicsModel createFromParcel(Parcel in) {
            return new StaticComicsModel(in);
        }

        @Override
        public StaticComicsModel[] newArray(int size) {
            return new StaticComicsModel[size];
        }
    };

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getComicsNumber() {
        return comicsNumber;
    }

    public void setComicsNumber(String comicsNumber) {
        this.comicsNumber = comicsNumber;
    }
}
