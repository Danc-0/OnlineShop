package com.danc.onlineshop.model;

public class AlcoholItemModel {

    private String category;
    private String name;
    private String price;
    private String description;
    private String size;
    private String imageUrl;


    public AlcoholItemModel(String category, String name, String price, String description, String size, String imageUrl) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.size = size;
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
