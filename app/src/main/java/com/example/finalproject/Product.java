package com.example.finalproject;

public class Product {


    private int productID;
    private String product_name;
    private  String description;
    private float price;
    public Product(){

    }
    public Product(int ProductID,String PName, String PDescription,float PPrice){//student class constructor to set values
        this.productID=ProductID;
        this.product_name=PName;
        this.description=PDescription;
        this.price=PPrice;
    }

    //functions to get data objects of class
    public int getProductIDId(){
        return this.productID;
    }
    public String getProduct_name(){
        return this.product_name;
    }
    public String getDescription(){
        return this.description;
    }
    public float getPrice(){
        return this.price;
    }

}
