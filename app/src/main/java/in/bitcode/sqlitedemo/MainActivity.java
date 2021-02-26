package in.bitcode.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBUtil dbUtil = DBUtil.getInstance(this);
        boolean isAdded = dbUtil.addProduct(10, "Demo Product", 345);

        for(Product product : dbUtil.getProducts()) {
            mt(product.toString());
        }

        if(isAdded) {

        }
        else {

        }


        /*SQLiteOpenHelper helper =
                new ProductsHelper(this, "db_products", null, 2);
        db = helper.getWritableDatabase();
        */

        /*
        db = openOrCreateDatabase("db_products", Activity.MODE_PRIVATE, null);


        try {
            db.execSQL("create table products (id integer primary key, name text, price integer)");
        }
        catch(Exception e) {
        }
         */

        /*insertProducts();
        queryProducts();
        updateAndDelete();
        mt("---------------------");
        queryProducts();*/

        //db.rawQuery("select e.*, d.* from emp e, dept d where e.deptno = d.deptno", null);

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

        db = openOrCreateDatabase("db_products", Activity.MODE_PRIVATE, new MyCursorFactory());
    }

    private void mt(String text) {
        Log.e("tag", text);
    }

    class MyCursorFactory implements SQLiteDatabase.CursorFactory {

        @Override
        public Cursor newCursor(SQLiteDatabase sqLiteDatabase, SQLiteCursorDriver sqLiteCursorDriver, String s, SQLiteQuery sqLiteQuery) {
            return null;
        }
    }

    class MyCursor implements Cursor {

        private FileInputStream fin;

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public int getPosition() {
            return 0;
        }

        @Override
        public boolean move(int i) {
            return false;
        }

        @Override
        public boolean moveToPosition(int i) {
            return false;
        }

        @Override
        public boolean moveToFirst() {
            return false;
        }

        @Override
        public boolean moveToLast() {
            return false;
        }

        @Override
        public boolean moveToNext() {
            return false;
        }

        @Override
        public boolean moveToPrevious() {
            return false;
        }

        @Override
        public boolean isFirst() {
            return false;
        }

        @Override
        public boolean isLast() {
            return false;
        }

        @Override
        public boolean isBeforeFirst() {
            return false;
        }

        @Override
        public boolean isAfterLast() {
            return false;
        }

        @Override
        public int getColumnIndex(String s) {
            return 0;
        }

        @Override
        public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
            return 0;
        }

        @Override
        public String getColumnName(int i) {
            return null;
        }

        @Override
        public String[] getColumnNames() {
            return new String[0];
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public byte[] getBlob(int i) {
            return new byte[0];
        }

        @Override
        public String getString(int i) {
            //read using fin a tag and return
            return null;
        }

        @Override
        public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {

        }

        @Override
        public short getShort(int i) {
            return 0;
        }

        @Override
        public int getInt(int i) {
            return 0;
        }

        @Override
        public long getLong(int i) {
            return 0;
        }

        @Override
        public float getFloat(int i) {
            return 0;
        }

        @Override
        public double getDouble(int i) {
            return 0;
        }

        @Override
        public int getType(int i) {
            return 0;
        }

        @Override
        public boolean isNull(int i) {
            return false;
        }

        @Override
        public void deactivate() {

        }

        @Override
        public boolean requery() {
            return false;
        }

        @Override
        public void close() {

        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public void registerContentObserver(ContentObserver contentObserver) {

        }

        @Override
        public void unregisterContentObserver(ContentObserver contentObserver) {

        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {

        }

        @Override
        public Uri getNotificationUri() {
            return null;
        }

        @Override
        public boolean getWantsAllOnMoveCalls() {
            return false;
        }

        @Override
        public void setExtras(Bundle bundle) {

        }

        @Override
        public Bundle getExtras() {
            return null;
        }

        @Override
        public Bundle respond(Bundle bundle) {
            return null;
        }
    }

}