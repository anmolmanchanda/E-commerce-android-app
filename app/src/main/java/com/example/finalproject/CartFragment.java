package com.example.finalproject;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//Used for cart operations
public class CartFragment extends Fragment {
    public CartProductAdapter NewAdapter;
    //creating an adapter
    DataBaseHelper db;
    RecyclerView R2;
    TextView priceCart;
    Button btnCheckout;
    //creating list
    private List<Cart> cartList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//inflating the view
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id1, quantity, url;
        float price;
        db = new DataBaseHelper(getActivity());
        String productName, productDescription;
        priceCart = (TextView) getView().findViewById(R.id.priceCartTextView);
        String toPrint;
        toPrint = "SUB-TOTAL :" + db.sumCart();//taking the sum for the final price from the database
        priceCart.setText(toPrint);
        R2 = (RecyclerView) getView().findViewById(R.id.cartRecyclerView);

        Cursor cursor = db.viewCart();
        if (cursor == null) {
            Toast.makeText(getContext(), "Empty Table", Toast.LENGTH_SHORT).show();

        } else {
            if (cursor.moveToFirst()) {
                do {
                    //retriving all the data of the rows
                    id1 = (cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    productName = cursor.getString(cursor.getColumnIndexOrThrow("Pname"));
                    productDescription = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                    price = cursor.getFloat(cursor.getColumnIndexOrThrow("Price"));
                    quantity = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));
                    url = cursor.getInt(cursor.getColumnIndexOrThrow("url"));
                    Cart C = new Cart(id1, productName, productDescription, price, quantity, url);//setting object with values retrived from table
                    //adding object to object list

                    cartList.add(C);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bindAdapter();
            db.close();
        }
        //checking button click for checkout
        btnCheckout = (Button) getView().findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new CheckoutFragment());
            }//switching fragment for checkout
        });
    }

    public void switchFragment(Fragment fragment){//fragment switching function
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.FrameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void bindAdapter() {// binging adapter to recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        R2.setLayoutManager(layoutManager);
        NewAdapter = new CartProductAdapter(cartList, getContext());
        R2.setAdapter(NewAdapter);
        NewAdapter.notifyDataSetChanged();
    }
    public void refresh()
    {
    NewAdapter.notifyDataSetChanged();
    }


}