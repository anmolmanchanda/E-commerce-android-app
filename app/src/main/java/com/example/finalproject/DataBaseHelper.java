package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    //creating static variables for creating databse and table
    public static  final String dbName="Ecommerce.db";
    public static final int version=1;
    public static final String CART_TABLE_NAME="Cart";
    public static final String C_COL1="id";
    public static final String C_COL2="Pname";
    public static final String C_COL3="Description";
    public static final String C_COL4="Price";
    public static final String C_COL5="Quantity";
    //creating string with sql query to create table
    public static final String CART_CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+CART_TABLE_NAME+"("+C_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+C_COL2+" TEXT,"+C_COL3+" TEXT,"+C_COL4+" FLOAT,"+C_COL5+" INTEGER);";
    public static final String CART_DROP_TABLE=" DROP TABLE IF EXISTS "+CART_TABLE_NAME;
    //creating database



    public static final String PRODUCT_TABLE_NAME="Product";
    public static final String P_COL1="id";
    public static final String P_COL2="Pname";
    public static final String P_COL3="Description";
    public static final String P_COL4="Price";
    //creating string with sql query to create table
    public static final String PRODUCT_CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+PRODUCT_TABLE_NAME+"("+P_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+P_COL2+" TEXT,"+P_COL3+" TEXT,"+P_COL4+" FLOAT);";
    public static final String PRODUCT_DROP_TABLE=" DROP TABLE IF EXISTS "+PRODUCT_TABLE_NAME;



    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating table
        sqLiteDatabase.execSQL(CART_CREATE_TABLE);
        sqLiteDatabase.execSQL(PRODUCT_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CART_DROP_TABLE);
        sqLiteDatabase.execSQL(PRODUCT_DROP_TABLE);
    }

    //CART OPERATIONS
    public boolean InsertIntoCart(Cart C1){
        //setting values to insert into table
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(C_COL2,C1.getProduct_name());
        contentvalues.put(C_COL3,C1.getDescription());
        contentvalues.put(C_COL4,C1.getPrice());
        contentvalues.put(C_COL5,C1.getQuantity());
        //inserting into database
        long result=db.insert(CART_TABLE_NAME,null,contentvalues);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor viewCart(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
//executing query to select all rows and setting the cursor to  the returned rows
        cursor=db.rawQuery("SELECT * FROM "+CART_TABLE_NAME,null);
        if(cursor!=null){
            cursor.moveToFirst();//setting the cursor to the first row
        }
        return cursor;
    }

    public void deleteCartItem(Cart item) {
        SQLiteDatabase db = getWritableDatabase();
        int id=(item.getProductIDId());
        String whereClause = "id= ";
       db.delete(CART_TABLE_NAME,whereClause,new String[]{String.valueOf(id)});
    }

    public boolean updateCartData(Cart C1) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(C_COL2,C1.getProduct_name());
        contentvalues.put(C_COL3,C1.getDescription());
        contentvalues.put(C_COL4,C1.getPrice());
        contentvalues.put(C_COL5,C1.getQuantity());
        db.update(CART_TABLE_NAME, contentvalues, C_COL1 + " =? ", new String[]{String.valueOf(C1.getProductIDId())});
        return true;

    }


// PRODUCT OPERATIONS

    public boolean InsertIntoProduct(Cart C1){
        //setting values to insert into table
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(P_COL2,C1.getProduct_name());
        contentvalues.put(P_COL3,C1.getDescription());
        contentvalues.put(P_COL4,C1.getPrice());
        //inserting into database
        long result=db.insert(PRODUCT_TABLE_NAME,null,contentvalues);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor viewProduct(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
//executing query to select all rows and setting the cursor to  the returned rows
        cursor=db.rawQuery("SELECT * FROM "+PRODUCT_TABLE_NAME,null);
        if(cursor!=null){
            cursor.moveToFirst();//setting the cursor to the first row
        }
        return cursor;
    }

}

