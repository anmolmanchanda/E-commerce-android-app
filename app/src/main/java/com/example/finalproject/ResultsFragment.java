package com.example.finalproject;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class ResultsFragment extends Fragment {
    RecyclerView recyclerView;
    public String type;
    private List<Product> list = new ArrayList<>();
    private ListAdapter listAdapter;
    Cursor cursor;
    StringBuffer sbProductID;
    StringBuffer sbProductName;
    StringBuffer sbProductDescription;
    StringBuffer sbProductPrice;

    public ResultsFragment( ) {

        // Required empty public constructor
    }
    public ResultsFragment(String Type) {
        this.type=Type;
        // Required empty public constructor
    }



    @SuppressLint("Range")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataBaseHelper databaseHelper = new DataBaseHelper(getContext());
        //Created a cursor object to get all the records saved in SQL DB
        cursor = databaseHelper.viewProduct(this.type);
        if(cursor!=null && cursor.getCount() > 0) {
            int id;
            String name;
            String desc, url;
            float price;
            //checking if the cursor is at first position
            if (cursor.moveToFirst()) {
                do {
                    //getting the string stored in the cursor at the column index which has the respective string
                    Product product = new Product();
                    id = (Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                    //sbProductID.append(" " + "\n");
                    name = (cursor.getString(cursor.getColumnIndex("Pname")));
                    //sbProductName.append(" " + "\n");
                    desc = (cursor.getString(cursor.getColumnIndex("Description")));
                    //sbProductDescription.append(" " + "\n");
                    price = (Float.parseFloat(cursor.getString(cursor.getColumnIndex("Price"))));
                    //sbProductPrice.append(" " + "\n");
                    //as long as there's a next record to move to
                    url = (cursor.getString(cursor.getColumnIndex("url")));
                    Log.i("URL","IN RF URL: "+url);

                    Product p = new Product(id, name, desc, price, url);
                    list.add(p);
                } while (cursor.moveToNext());
            }
        }
        Log.i("cursor count", "" + cursor.getCount());
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        //AddListItems();
        BindAdapter();
    }

    /*private void AddListItems(){
        Product product = new Product();
        product.setProductID(sbProductID.toString());
        product.setProduct_name(sbProductName.toString());
        product.setDescription(sbProductDescription.toString());
        product.setPrice(sbProductPrice.toString());
        Log.i("productID", "" + String.valueOf(sbProductID));
        Log.i("productName", "" + sbProductName);
        Log.i("productDescription", "" + sbProductDescription);
        Log.i("productPrice", "" + String.valueOf(sbProductPrice));
        Log.i("product", "" + product);
        list.add(product);
        Log.i("list", "" + list);
//        product = new Product();
//        product.setProductID(productID);
//        product.setProduct_name(productName);
//        product.setDescription(productDescription);
//        product.setPrice(productPrice);
//        list.add(product);
    }*/



    private void BindAdapter()
    {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(list, getContext());
        Log.i("listAdapter", "" + listAdapter);
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }
}