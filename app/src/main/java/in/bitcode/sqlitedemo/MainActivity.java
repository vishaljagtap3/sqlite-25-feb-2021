package in.bitcode.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("db_products", Activity.MODE_PRIVATE, null);

        try {
            db.execSQL("create table products (id integer primary key, name text, price integer)");
        }
        catch(Exception e) {
        }

        //insertProducts();
        queryProducts();
        updateAndDelete();
        mt("---------------------");
        queryProducts();

    }

    private void updateAndDelete() {

        ContentValues values = new ContentValues();
        values.put("price", 3000);
        values.put("name", "Macbook");

        int rowsUpdated = db.update(
                "products",
                values,
                "id = ?",
                new String [] {"1"}
        );
        mt("updated: " + rowsUpdated);

        rowsUpdated = db.update(
                "products",
                values,
                "id = ?",
                new String [] {"11"}
        );
        mt("updated: " + rowsUpdated);


        int rowsDeleted = db.delete(
                "products",
                "id = ?",
                new String [] {"4"}
        );
        mt("rows deleted: " + rowsDeleted);

        rowsDeleted = db.delete(
                "products",
                "id = ?",
                new String [] {"4"}
        );
        mt("rows deleted: " + rowsDeleted);

    }

    private void queryProducts() {


        Cursor c = db.query("products", null, null, null, null, null, "price desc");

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            int price = c.getInt(2);

            mt(id + " " + name + " " + price);
        }

        c.close();

        /*
        String [] columns = {"id", "name", "price"};
        String where = "price > ? and dept = ?";
        String [] whereArgs = {"1000", "electronics"};
        String orderBy = "price asc, dept desc";
        Cursor c = db.query("products", columns, where, whereArgs, null, null, orderBy);
        */


    }

    private void insertProducts() {

        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "laptop");
        values.put("price", 1000);

        long rowNum = db.insert("products", null, values);
        mt("rownum: " + rowNum);

        values.put("id", 2);
        values.put("name", "Android Phone");
        values.put("price", 1200);

        rowNum = db.insert("products", null, values);
        mt("rownum: " + rowNum);

        values.put("id", 3);
        values.put("name", "Monitor");
        values.put("price", 900);

        rowNum = db.insert("products", null, values);
        mt("rownum: " + rowNum);

        values.put("id", 4);
        values.put("name", "Bluetooth Speaker");
        values.put("price", 1050);

        rowNum = db.insert("products", null, values);
        mt("rownum: " + rowNum);

        rowNum = db.insert("products", null, values);
        mt("rownum: " + rowNum);

        //String query = "insert into products(id, name, price) values(11, 'Dell laptop', 1000)";
        //String query = "insert into products values(?, ?, ?)";
        //String query = "insert into products values(" + productId + ", " + productName + ", " price)";
        //String [] params = { "1", "Laptop", "1000" };
        //db.rawQuery(query, params);
    }

    private void mt(String text) {
        Log.e("tag", text);
    }
}