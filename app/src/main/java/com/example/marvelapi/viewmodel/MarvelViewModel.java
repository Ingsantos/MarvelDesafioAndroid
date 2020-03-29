package com.example.marvelapi.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.marvelapi.data.network.MarvelRetrofitService;
import com.example.marvelapi.model.Result;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MarvelViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> comicsList = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public static final String PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0";
    public static final String PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f";
    String ts = Long. toString (System. currentTimeMillis () / 1000 );
    String hash = md5 (ts + PRIVATE_KEY + PUBLIC_KEY );

    public MarvelViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getComicsList() {
        return this.comicsList;
    }

    public void getComics() {
        mCompositeDisposable.add(MarvelRetrofitService.getApiService().getComics("magazine",
                "comic", true, "focDate", ts, hash, PUBLIC_KEY)
                .subscribeOn(Schedulers. newThread ()).observeOn(AndroidSchedulers. mainThread ())
                .subscribe(comicsResponse -> comicsList.setValue(comicsResponse.getData().getResults()),
                        throwable -> {
                            Log.i("LOG", "erro" + throwable.getMessage());
                        })
        );
    }

    public static String md5(String s) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
