package com.young.instaapi.data.remote;



import com.young.instaapi.data.response.InstaShopResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface InstaShopApi {

    @GET("insta_girls/instaGirlsShop/getInstaShopsByCat/{cat}")
    Observable<InstaShopResponse> getInstaShopsByCat(@Path("cat") String cat);
}
