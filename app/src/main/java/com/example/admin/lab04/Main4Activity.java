package com.example.admin.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    ListView lst;
    ArrayList<Book> arr;
    DBHandler mydb;
    ArrayAdapter<Book> adapter;
    int i;

    public static final int RESULT_ADD = 0;
    public static final int RESULT_EDIT = 1;
    public static final int RESULT_DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main4Activity.this, Main5Activity.class);
                startActivityForResult(i, 0);
            }
        });

        mydb = new DBHandler(this);
        lst = findViewById(R.id.lsvShow1);
        arr = mydb.getAll();
        adapter = new ArrayAdapter<Book>(this, R.layout.support_simple_spinner_dropdown_item, arr);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                Book b = arr.get(position);
                i = position;
                intent.putExtra("book", b);
                intent.putExtra("flag", 1);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_ADD) {
            Book b = (Book)data.getSerializableExtra("book");
            mydb.insertBook(b);
            arr.add(b);
            adapter.notifyDataSetChanged();
        }

        if(resultCode == RESULT_EDIT) {
            Book b = (Book)data.getSerializableExtra("book");
            mydb.updateBook(b);
            arr.set(i, b);
            adapter.notifyDataSetChanged();
        }

        if(resultCode == RESULT_DELETE) {
            Book b = arr.get(i);
            mydb.deleteBook(b.getId());
            arr.remove(i);
            adapter.notifyDataSetChanged();
        }
    }
}
