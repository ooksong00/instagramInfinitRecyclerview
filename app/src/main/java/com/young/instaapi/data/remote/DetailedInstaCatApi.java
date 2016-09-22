package com.young.instaapi.data.remote;



import com.young.instaapi.data.response.InstaMediaResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface DetailedInstaCatApi {

    @GET("https://www.instagram.com/{user_id}/media")
    Observable<InstaMediaResponse> getInstaCatMedia(@Path("user_id") String userId, @Query("max_id") String maxId);

}
