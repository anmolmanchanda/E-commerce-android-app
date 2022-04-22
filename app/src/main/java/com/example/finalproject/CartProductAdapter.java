package com.example.finalproject;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//cart Adapter
public class CartProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cart> cartList;
    private Context context;
    private int quantity=0;
    public CartProductAdapter(List<Cart> list, Context context) {
        super();
        this.cartList = list;
        this.context = context;
    }


//viewholder for the data in the recycler view

    class ViewHolder extends RecyclerView.ViewHolder {
        //creating recycle view elements
        public ImageButton deleteBtn;
        public ImageButton addBtn;
        public ImageButton removeBtn;
        public TextView PName;
        public TextView PPrice;
        public TextView PQuantity;
        public ImageView cartImage;
        public ViewHolder(View itemView) {
            super(itemView);
            //binding elements
            //attaching Ui elements
            removeBtn = (ImageButton) itemView.findViewById(R.id.cardRemoveImageButton);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.deleteCartimageButton);
            addBtn = (ImageButton) itemView.findViewById(R.id.cartAddImageButton);
            PName = (TextView) itemView.findViewById(R.id.CartProductNameTextView);
            PPrice = (TextView) itemView.findViewById(R.id.cartProductPriceTextView);
            PQuantity = (TextView) itemView.findViewById(R.id.CartProductQuantityTextView);
            cartImage=(ImageView) itemView.findViewById(R.id.cartProductImage);

            //cartImage.setImageResource(R.drawable.harrypotter);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//inflating view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartproductlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //binding elements
        Cart cartAdapter = cartList.get(position);
        int temp=cartAdapter.getUrl();
        //setting the viewholder
        ((ViewHolder) viewHolder).PName.setText(cartAdapter.getProduct_name());
        ((ViewHolder) viewHolder).PPrice.setText("PRICE  :$"+Integer.toString((int) cartAdapter.getPrice()));
        ((ViewHolder) viewHolder).PQuantity.setText(Integer.toString( cartAdapter.getQuantity()));
        ((ViewHolder) viewHolder).addBtn.setImageResource(R.drawable.plus);
        ((ViewHolder) viewHolder).deleteBtn.setImageResource(R.drawable.bin);
        ((ViewHolder) viewHolder).removeBtn.setImageResource(R.drawable.minus);
        ((ViewHolder) viewHolder).cartImage.setImageResource(cartAdapter.getUrl());
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context.getApplicationContext());
        DataBaseHelper databaseHelper = new DataBaseHelper(context);
        ((ViewHolder) viewHolder).addBtn.setOnClickListener(new View.OnClickListener() {//onclick for adding quantity
            @Override
            public void onClick(View view) {

                Cart C1=new Cart(cartAdapter.getProductIDId(), cartAdapter.getProduct_name(),cartAdapter.getDescription(),cartAdapter.getPrice(),(cartAdapter.getQuantity()+1),cartAdapter.getUrl());
                databaseHelper.updateCartData(C1);
                Toast.makeText(context, "Added, Please refresh page", Toast.LENGTH_SHORT).show();


            }
        });

        ((ViewHolder) viewHolder).removeBtn.setOnClickListener(new View.OnClickListener() {//onclick for removing quantity
            @Override
            public void onClick(View view) {

                Cart C1=new Cart(cartAdapter.getProductIDId(), cartAdapter.getProduct_name(),cartAdapter.getDescription(),cartAdapter.getPrice(),(cartAdapter.getQuantity()-1),cartAdapter.getUrl());
                databaseHelper.updateCartData(C1);
                Toast.makeText(context, "Removed Please Refresh page", Toast.LENGTH_SHORT).show();


            }
        });
        ((ViewHolder) viewHolder).deleteBtn.setOnClickListener(new View.OnClickListener() {//onclick for deleting product from cart
            @Override
            public void onClick(View view) {

                Cart C1=new Cart(cartAdapter.getProductIDId(), cartAdapter.getProduct_name(),cartAdapter.getDescription(),cartAdapter.getPrice(),(cartAdapter.getQuantity()),cartAdapter.getUrl());
                databaseHelper.deleteCartItem(C1);
                Toast.makeText(context, "Deleted Please Refresh page", Toast.LENGTH_SHORT).show();
                cartList.remove(C1);

            }
        });




    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


}
