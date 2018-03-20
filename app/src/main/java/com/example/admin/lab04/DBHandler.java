package com.example.admin.lab04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 3/20/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    //khai bao cac hang so co so du lieu
    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;
    //khai bao hang so bang
    public static final String TBL_NAME = "table_book";
    public static final String COL_ID = "_id";
    public static final String COL_BOOKNAME = "bookname";
    public static final String COL_BOOKPAGE = "bookpage";
    public static final String COL_BOOKPRICE = "bookprice";
    public static final String COL_BOOKDESC = "bookdescription";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqltext = "DROP TABLE IF EXISTS " + TBL_NAME + ";\n" + "CREATE TABLE " + TBL_NAME + "(" + COL_ID +
                " integer PRIMARY KEY AUTOINCREMENT," + COL_BOOKNAME + " text," + COL_BOOKPAGE + " integer," + COL_BOOKPRICE +
                " Float," + COL_BOOKDESC + " text);\n" + "INSERT INTO " + TBL_NAME + " VALUES (1,'Java',100,9.99,'Sach ve Java');\n"
                + "INSERT INTO " + TBL_NAME + " VALUES (2,'Android',320,19.00,'Android co ban');\n" + "INSERT INTO " + TBL_NAME +
                " VALUES (3,'Hoc lam giau',120,0.99,'Sach doc cho vui');\n" + "INSERT INTO " + TBL_NAME + " VALUES (4,'Tu dien AnhViet',1000,29.99,'Tu dien 1000000 tu');\n" + "INSERT INTO " + TBL_NAME + " VALUES (5,'CNXH',1,1,'Chuyen co tich');";
        for (String sql : sqltext.split("\n")) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TBL_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    //Them - insert
    int insertBook(Book b) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BOOKNAME, b.getBookName());
        values.put(COL_BOOKPAGE, b.getPage());
        values.put(COL_BOOKPRICE, b.getPrice());
        values.put(COL_BOOKDESC, b.getDescription());
        int id = (int) database.insert(TBL_NAME, null, values);
        database.close();
        return id;
    }

    //Sua - update
    int updateBook(Book b) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BOOKNAME, b.getBookName());
        values.put(COL_BOOKPAGE, b.getPage());
        values.put(COL_BOOKPRICE, b.getPrice());
        values.put(COL_BOOKDESC, b.getDescription());
        int num = (int) database.update(TBL_NAME, values, COL_ID + "=?", new String[]{b.getId() + ""});
        database.close();
        return num;
    }

    //Xoa - Delete
    int deleteBook(int book_id) {
        SQLiteDatabase database = this.getWritableDatabase();
        int num = database.delete(TBL_NAME, COL_ID + "=?", new String[]{book_id + ""});
        database.close();
        return num;
    }

    //truy van lay ra 1 phan tu -- sua
    Book getBook(int book_id) {
        SQLiteDatabase database = this.getReadableDatabase();
        //truy van bang rawQuery
        String sql = "SELECT * FROM " + TBL_NAME + " WHERE " + COL_ID + "=?";
        Cursor c = database.rawQuery(sql, new String[]{book_id + ""});
        if (c != null)
            c.moveToFirst();
        Book b = new Book();
        b.setId(c.getInt(c.getColumnIndex(COL_ID)));
        b.setBookName(c.getString(1));
        b.setPage(c.getInt(2));
        b.setPrice(c.getFloat(3));
        b.setDescription(c.getString(4));
        //dong ket noi
        c.close();
        database.close();
        return b;
    }

    //truy van lay toan bo bang de dua len ListView
    ArrayList<Book> getAll() {
        ArrayList<Book> arr = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM " + TBL_NAME, null);
        if (c != null) {
            c.moveToFirst();
        }
        Book b;
        do {
            b = new Book(c.getInt(0), c.getString(1), c.getInt(2), c.getFloat(3), c.getString(4));
            arr.add(b);
        } while (c.moveToNext());
        c.close();
        database.close();
        return arr;
    }
}
