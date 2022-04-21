package com.example.finalproject;

public class Product {
    private String productID;
    private String product_name;
    private String description;
    private String price;

    public Product() {
    }

    //student class constructor to set values
    public Product(String ProductID, String PName, String PDescription, String PPrice) {
        this.productID = ProductID;
        this.product_name = PName;
        this.description = PDescription;
        this.price = PPrice;
    }

    //functions to get data objects of class
    public String getProductID() {
        return this.productID;
    }
    public void setProductID(String productID) {
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
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}