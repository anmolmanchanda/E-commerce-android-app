package com.example.finalproject;

public class Product {
    private int productID;
    private String product_name;
    private String description;
    private float price;
    private String url;

    public Product() {
    }

    //student class constructor to set values
    public Product(int ProductID, String PName, String PDescription, float PPrice,String url) {
        this.productID = ProductID;
        this.product_name = PName;
        this.description = PDescription;
        this.price = PPrice;
        this.url=url;
    }

    //functions to get data objects of class
    public int getProductID() {
        return this.productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}