package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> mList;
    private Context context;

    public ListAdapter(List<Product> list, Context context){
        super();
        mList = list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{//linking Ui
        public TextView tvProductID;
        public TextView tvProductName;
        public TextView tvProductDescription;
        public TextView tvProductPrice;
        public ImageView imProductImage;
        public Button btnAddCart;


        public ViewHolder( View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            imProductImage=(ImageView) itemView.findViewById(R.id.productImageRecordLayout);
             btnAddCart = (Button) itemView.findViewById(R.id.buttonAddCart);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//inflating view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product itemAdapter = mList.get(position);
//setting value into UI
        ((ViewHolder) holder).tvProductName.setText(itemAdapter.getProduct_name());
        ((ViewHolder) holder).tvProductDescription.setText(itemAdapter.getDescription());
        ((ViewHolder) holder).tvProductPrice.setText("PRICE  :$"+String.valueOf(itemAdapter.getPrice()));
        ((ViewHolder) holder).imProductImage.setImageResource(itemAdapter.getUrl());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context.getApplicationContext());

        DataBaseHelper databaseHelper = new DataBaseHelper(context);
        Product product = new Product();
        ((ViewHolder) holder).btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Onclick fro adding item into cart
                Toast.makeText(context, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                Cart C1=new Cart(itemAdapter.getProductID(), itemAdapter.getProduct_name(),itemAdapter.getDescription(),itemAdapter.getPrice(),1,itemAdapter.getUrl());
                databaseHelper.InsertIntoCart(C1);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
