package com.hernanpormachideveloper.retoandroidcp.models;
import com.google.gson.annotations.SerializedName;

public class CandyItem {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private String price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
