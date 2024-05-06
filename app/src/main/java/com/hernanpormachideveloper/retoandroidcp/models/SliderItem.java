package com.hernanpormachideveloper.retoandroidcp.models;

public class SliderItem {

    String image;

    String description;

    public SliderItem(){

    }

    public SliderItem(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
