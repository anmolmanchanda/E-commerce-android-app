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
//linking UI
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
            public void onClick(View view) {//Functions for switching fragments
                switchFragment(new HomeFragment());
                ivHome.setImageResource(R.drawable.home_active);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Functions for switching fragments
                switchFragment(new AccountFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account_active);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Functions for switching fragments
                switchFragment(new CartFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart_active);
                ivMore.setImageResource(R.drawable.more);
            }
        });
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Functions for switching fragments
                switchFragment(new MoreFragment());
                ivHome.setImageResource(R.drawable.home);
                ivAccount.setImageResource(R.drawable.account);
                ivCart.setImageResource(R.drawable.cart);
                ivMore.setImageResource(R.drawable.more_active);
            }
        });
    }

    public  void switchFragment(Fragment fragment){//Functions for switching fragments
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.FrameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
    //Insert initial data
    public void primer()
    {//intialisation of db with values
        Product P1=new Product(1,"The Burger Book","Books",120,R.drawable.theburgerbookimage);
        Product P2=new Product(2,"The Cat In The Hat","Books",10,R.drawable.thecatinthehatbookimage);
        Product P3=new Product(3,"The Last Book On The Left","Books",200,R.drawable.thelastbookontheleftbookimage);

        Product P4=new Product(4,"FitBit Smart Watch","Electronics",400,R.drawable.fitbitsmartwatchelectronicsimage);
        Product P5=new Product(5,"Samsung S22","Electronics",1700,R.drawable.samsungs22electronicsimage);
        Product P6=new Product(6,"Sony Headphone","Electronics",200,R.drawable.sonyheadphoneelectronicsimage);

        Product P7=new Product(7,"Cap","Clothing",20,R.drawable.capclothingimage);
        Product P8=new Product(8,"Jacket","Clothing",200,R.drawable.jacketclothingimage);
        Product P9=new Product(9,"Shirt","Clothing",120,R.drawable.shirtclothingimage);

        DataBaseHelper db=new DataBaseHelper(MainActivity.this);
        if(db.viewProduct().getCount()==0) {
            boolean a = db.InsertIntoProduct(P1);
             a = db.InsertIntoProduct(P1);
             a = db.InsertIntoProduct(P2);
             a = db.InsertIntoProduct(P3);
             a = db.InsertIntoProduct(P4);
             a = db.InsertIntoProduct(P5);
            a = db.InsertIntoProduct(P6);
            a = db.InsertIntoProduct(P7);
            a = db.InsertIntoProduct(P8);
            a = db.InsertIntoProduct(P9);

            if (a) {
                Toast.makeText(this, "Inserted into database product", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "product fail", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
