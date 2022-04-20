package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    DataBaseHelper db;
    RecyclerView R2;
    TextView priceCart;
    private List<Cart> cartList=new ArrayList<>();
    public CartProductAdapter NewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id1,quantity;
        float price;
        String productName,productDescription;
        priceCart=(TextView) getView().findViewById(R.id.priceCartTextView);
        R2=(RecyclerView) getView().findViewById(R.id.cartRecyclerView);
        db=new DataBaseHelper(getActivity());
        priceCart.setText("Inside fn");
        Cursor cursor=db.viewCart();
        if(cursor==null){
            Toast.makeText(getContext(), "Empty Table", Toast.LENGTH_SHORT).show();

        }
        else{
            if(cursor.moveToFirst()){
                do{
                    //retriving all the data of the rows
                    id1=(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    productName=cursor.getString(cursor.getColumnIndexOrThrow("Pname"));
                    productDescription=cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                    price=cursor.getFloat(cursor.getColumnIndexOrThrow("Price"));
                    quantity=cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));
                    Cart C=new Cart(id1,productName,productDescription,price,quantity);//setting object with values retrived from table
                    //adding object to object list
                    cartList.add(C);
                }while(cursor.moveToNext());
            }
            cursor.close();
            bindAdapter();
            db.close();
        }


    }
    public void bindAdapter()
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        R2.setLayoutManager(layoutManager);
        NewAdapter=new CartProductAdapter(cartList,getContext());
        R2.setAdapter(NewAdapter);
        NewAdapter.notifyDataSetChanged();
    }


}