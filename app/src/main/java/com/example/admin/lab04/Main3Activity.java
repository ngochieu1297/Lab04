package com.example.admin.lab04;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor c;
    Button btnPrev, btnNext;
    TextView txt_id, txt_name, txt_page, txt_price, txt_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_page = findViewById(R.id.txt_page);
        txt_desc = findViewById(R.id.txt_desc);
        txt_price = findViewById(R.id.txt_price);

        createDB();
        c = db.rawQuery("Select * from books", null);
        c.moveToNext();
        updateRecord();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToNext();
                updateRecord();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToPrevious();
                updateRecord();
            }
        });
    }

    private void createDB() {
        String sqltext = "DROP TABLE IF EXISTS BOOKS;\n" + "CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text," +
                "Page integer, Price Float,Description text);\n" + "INSERT INTO BOOKS VALUES (1,'Java',100,9.99,'Sach ve Java');\n"
                + "INSERT INTO BOOKS VALUES (2,'Android',320,19.00,'Android co ban');\n" + "INSERT INTO BOOKS VALUES (3,'Hoc lam giau',120,0.99,'Sach doc cho vui');\n"
                + "INSERT INTO BOOKS VALUES (4,'Tu dien Anh Viet',1000,29.99,'Tu dien 1000000 tu');\n" + "INSERT INTO BOOKS VALUES (5,'CNXH',1,1,'Chuyen co tich');";
        db=openOrCreateDatabase("books.db", MODE_PRIVATE,null);
        for (String sql:sqltext.split("\n"))
            db.execSQL(sql);
    }

    private void updateRecord() {
        txt_id.setText(c.getString(0));
        txt_name.setText(c.getString(1));
        txt_page.setText(c.getString(2));
        txt_price.setText(c.getString(3));
        txt_desc.setText(c.getString(4));
        btnPrev.setEnabled(!c.isFirst());
        btnNext.setEnabled(!c.isLast());
    }
}
