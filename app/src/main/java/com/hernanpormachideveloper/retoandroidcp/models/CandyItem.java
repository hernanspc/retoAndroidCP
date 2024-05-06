package com.hernanpormachideveloper.retoandroidcp.models;
import com.google.gson.annotations.SerializedName;

public class CandyItem {
    private String name;
    private String description;
    private double price;
    private boolean isSelected;

    // Constructor
    public CandyItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isSelected = false; // Por defecto, no está seleccionado
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

