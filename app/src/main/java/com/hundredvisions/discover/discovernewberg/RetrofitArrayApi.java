package com.hundredvisions.discover.discovernewberg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by winikkc on 3/1/2018.
 */

public interface RetrofitArrayApi {
    @GET("wp-json/wp/v2/posts")
    Call<List<WPPost>> getPostInfo();
}
