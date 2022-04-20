package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivHome;
    ImageView ivAccount;
    ImageView ivCart;
    ImageView ivMore;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

primer();

        switchFragment(new HomeFragment());
        ivHome = (ImageView) findViewById(R.id.ivHome);
        ivAccount = (ImageView) findViewById(R.id.ivAccount);
        ivCart = (ImageView) findViewById(R.id.ivCart);
        ivMore = (ImageView) findViewById(R.id.ivMore);

        ivHome.setImageResource(R.drawable.home_active);
        ivAccount.setImageResource(R.drawable.account);
        ivCart.setImageResource(R.drawable.cart);
        ivMore.setImageResource(R.drawable.more);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new HomeFragment());
                ivHome.setImageResource(R.drawable.home_active);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new AccountFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account_active);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new CartFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart_active);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new MoreFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more_active);
            }
        });
    }

    public void switchFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.FrameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
    //Insert initial data
    public void primer()
    {
        Product P1=new Product(3,"TV","Electronics",1200);
        DataBaseHelper db=new DataBaseHelper(MainActivity.this);
        boolean a=db.InsertIntoProduct(P1);

        if(a)
        {
            Toast.makeText(this, "Inserted into database product", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "product fail", Toast.LENGTH_SHORT).show();
        }



    }
}
