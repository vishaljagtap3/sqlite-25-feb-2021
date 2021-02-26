package in.bitcode.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBUtil {

    private static final String DB_NAME = "db_products";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    private static DBUtil dbUtil = null;

    static  {
        dbUtil = null;
    }

    private DBUtil(Context context) {
        db = new ProductsHelper(context, DB_NAME, null, DB_VERSION)
                .getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if(dbUtil == null) {
            dbUtil = new DBUtil(context);
        }
        return dbUtil;
    }

    public boolean addProduct(int id, String name, int price) {

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("price", price);

        long rowNum = db.insert("products", null, values);

        return rowNum > 0;
    }

    public int updateProduct(int id, String name, int price) {

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);

        int rowsUpdated = db.update(
                "products",
                values,
                "id = ?",
                new String[]{id + ""}
        );

        return rowsUpdated;
    }

    public int deleteProduct(int id) {
        int rowsDeleted = db.delete(
                "products",
                "id = ?",
                new String[]{id + ""}
        );
        return rowsDeleted;
    }


    public ArrayList<Product> getProducts() {

        ArrayList<Product> products = new ArrayList<>();

        Cursor c = db.query("products", null, null, null, null, null, "price desc");

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            int price = c.getInt(2);

            products.add(new Product(id, name, price));
        }

        c.close();

        return products;
    }


    private class ProductsHelper extends SQLiteOpenHelper {

        public ProductsHelper(@Nullable Context context, @Nullable String dbName, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table products (id integer primary key, name text, price integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("create table cutomers (id integer primary key, name text)");
            //db.execSQL("alter table products add stock integer");
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
        }
    }
}
