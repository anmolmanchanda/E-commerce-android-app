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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {//for showing the product based on category
        super.onViewCreated(view, savedInstanceState);
        DataBaseHelper databaseHelper = new DataBaseHelper(getContext());
        //Created a cursor object to get all the records saved in SQL DB
        cursor = databaseHelper.viewProduct(this.type);
        if(cursor!=null && cursor.getCount() > 0) {
            int id, url;
            String name;
            String desc;
            float price;
            //checking if the cursor is at first position
            if (cursor.moveToFirst()) {
                do {
                    //getting the string stored in the cursor at the column index which has the respective string
                    Product product = new Product();
                    id = (Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));

                    name = (cursor.getString(cursor.getColumnIndex("Pname")));

                    desc = (cursor.getString(cursor.getColumnIndex("Description")));

                    price = (Float.parseFloat(cursor.getString(cursor.getColumnIndex("Price"))));

                    //as long as there's a next record to move to
                    url = cursor.getInt(cursor.getColumnIndexOrThrow("url"));


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





    private void BindAdapter()//binding to the recycler view
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
                             Bundle savedInstanceState) {//inflating view
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }
}