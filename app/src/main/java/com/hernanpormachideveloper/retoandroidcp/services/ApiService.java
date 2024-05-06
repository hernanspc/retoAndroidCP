package com.hernanpormachideveloper.retoandroidcp.services;
import com.hernanpormachideveloper.retoandroidcp.models.CandyItem;
import com.hernanpormachideveloper.retoandroidcp.models.CandyStoreResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v1/candystore")
    Call<CandyStoreResponse> getCandyStoreItems();
}
