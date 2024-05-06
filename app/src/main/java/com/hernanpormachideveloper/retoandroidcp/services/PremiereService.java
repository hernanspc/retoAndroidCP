package com.hernanpormachideveloper.retoandroidcp.services;

import com.hernanpormachideveloper.retoandroidcp.models.PremiereResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PremiereService {
    @GET("v1/premieres")
    Call<PremiereResponse> getPremieres();
}
