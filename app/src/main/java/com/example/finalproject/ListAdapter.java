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

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvProductID;
        public TextView tvProductName;
        public TextView tvProductDescription;
        public TextView tvProductPrice;
        public ImageView imProductImage;
        public Button btnAddCart;
        public Button btnRemoveCart;

        public ViewHolder( View itemView) {
            super(itemView);
            tvProductID = (TextView) itemView.findViewById(R.id.tvProductID);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            imProductImage=(ImageView) itemView.findViewById(R.id.productImageRecordLayout);
            //imProductImage.setImageResource(R.drawable.books1);
             btnAddCart = (Button) itemView.findViewById(R.id.btnAddCart);
             btnRemoveCart = (Button) itemView.findViewById(R.id.btnRemoveCart);

            //imProductImage.setImageResource(context.getResources().getIdentifier(
                    //"com.example.finalproject:drawable/"+url,null,null));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product itemAdapter = mList.get(position);
        ((ViewHolder) holder).tvProductID.setText(String.valueOf(itemAdapter.getProductID()));
        ((ViewHolder) holder).tvProductName.setText(itemAdapter.getProduct_name());
        ((ViewHolder) holder).tvProductDescription.setText(itemAdapter.getDescription());
        ((ViewHolder) holder).tvProductPrice.setText(String.valueOf(itemAdapter.getPrice()));

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context.getApplicationContext());
        Cursor cursor = dataBaseHelper.viewProduct();
        @SuppressLint("Range") String url = (cursor.getString(cursor.getColumnIndex("url")));
        Log.i("URL","URL: "+url);
        ((ViewHolder) holder).imProductImage.setImageResource(context.getResources().getIdentifier
                ("com.example.finalproject:drawable/"+itemAdapter.getUrl(),null,null));

        DataBaseHelper databaseHelper = new DataBaseHelper(context);
        Product product = new Product();
        ((ViewHolder) holder).btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart C1=new Cart(product.getProductID(), product.getProduct_name(),product.getDescription(),product.getPrice(),1,product.getUrl());
                databaseHelper.InsertIntoCart(C1);
            }
        });

        ((ViewHolder) holder).btnRemoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart C1=new Cart(6,"TV44","Electronics",1200,1,"books1");
                databaseHelper.deleteCartItem(C1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
