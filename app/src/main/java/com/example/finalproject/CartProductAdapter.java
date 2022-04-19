package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cart> cartList;

    public CartProductAdapter(List<Cart> list, Context context) {
        super();
        cartList = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartproductlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        Cart cartAdapter = cartList.get(position);
        //setting the viewholder
        ((ViewHolder) holder).PName.setText(cartAdapter.getProduct_name());
        ((ViewHolder) holder).PPrice.setText((int) cartAdapter.getPrice());
        ((ViewHolder) holder).PQuantity.setText(cartAdapter.getQuantity());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //creating recycle view elements
        public ImageButton deleteBtn;
        public ImageButton addBtn;
        public TextView PName;
        public TextView PPrice;
        public TextView PQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            //binding elements
            deleteBtn = (ImageButton) itemView.findViewById(R.id.cardDeleteImageButton);
            addBtn = (ImageButton) itemView.findViewById(R.id.cartAddImageButton);
            PName = (TextView) itemView.findViewById(R.id.CartProductNameTextView);
            PPrice = (TextView) itemView.findViewById(R.id.cartProductPriceTextView);
            PQuantity = (TextView) itemView.findViewById(R.id.CartProductQuantityTextView);
        }
    }
}
