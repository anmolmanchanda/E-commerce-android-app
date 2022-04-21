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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultsFragment( ) {

        // Required empty public constructor
    }
    public ResultsFragment( String Type) {
        this.type=Type;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("Range")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataBaseHelper databaseHelper = new DataBaseHelper(getContext());
        //Created a cursor object to get all the records saved in SQL DB
        cursor = databaseHelper.viewProduct(this.type);
        sbProductID = new StringBuffer();
        sbProductName = new StringBuffer();
        sbProductDescription = new StringBuffer();
        sbProductPrice = new StringBuffer();


        //checking if the cursor is at first position
        if (cursor.moveToFirst()) {
            do {
                //getting the string stored in the cursor at the column index which has the respective string
                Product product = new Product();
                sbProductID.append(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                sbProductID.append(" " + "\n");
                sbProductName.append(cursor.getString(cursor.getColumnIndex("Pname")));
                sbProductName.append(" " + "\n");
                sbProductDescription.append(cursor.getString(cursor.getColumnIndex("Description")));
                sbProductDescription.append(" " + "\n");
                sbProductPrice.append(Float.parseFloat(cursor.getString(cursor.getColumnIndex("Price"))));
                sbProductPrice.append(" " + "\n");
                //as long as there's a next record to move to
            } while (cursor.moveToNext());
        }
        Log.i("cursor", "" + cursor.getCount());
        Log.i("cursor", "" + cursor);
        Log.i("cursor", "" + cursor.toString());
        //Log.i("cursor", "" + cursor.getString(1));

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        AddListItems();
        BindAdapter();
    }

    private void AddListItems(){
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
    }

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