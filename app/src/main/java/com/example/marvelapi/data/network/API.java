package com.example.marvelapi.data.network;

import com.example.marvelapi.model.ComicsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET( "comics?" )
    Single<ComicsResponse> getComics(
            @Query( "format" ) String format,
            @Query ( "formatType" ) String formatType,
            @Query ( "noVariants" ) boolean noVariants,
            @Query ( "orderBy" ) String orderBy,
            @Query ( "ts" ) String ts,
            @Query ( "hash" ) String hash,
            @Query ( "apikey" ) String apikey);
}
