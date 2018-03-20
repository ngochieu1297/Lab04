package com.example.admin.lab04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtPage, edtPrice, edtDesc;
    Button btnAdd, btnEdit, btnDelete;
    TextView txt_id;
    int flag;
    int note_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);

        edtName = findViewById(R.id.edtName);
        edtPage = findViewById(R.id.edtPage);
        edtPrice = findViewById(R.id.edtPrice);
        edtDesc = findViewById(R.id.edtDesc);
        txt_id = findViewById(R.id.txt_id);

        flag = getIntent().getIntExtra("flag", 0);

        if(flag == 1) {
            btnAdd.setEnabled(false);
            Book b = (Book)getIntent().getSerializableExtra("book");
            txt_id.setText(b.getId() + "");
            edtName.setText(b.getBookName() + "");
            edtPage.setText(b.getPage() + "");
            edtPrice.setText(b.getPrice() + "");
            edtDesc.setText(b.getDescription() + "");
            note_id = b.getId();
        } else {
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }

        btnAdd.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Book b = new Book(note_id, edtName.getText() + "", Integer.parseInt(edtPage.getText() + ""),
                Float.parseFloat(edtPrice.getText() + ""), edtDesc.getText() + "");
        Intent i = new Intent(this, Main4Activity.class);
        i.putExtra("book", b);
        if(flag == 1) {
            if(v == btnDelete) {
                setResult(Main4Activity.RESULT_DELETE, i);
            } else {
                setResult(Main4Activity.RESULT_EDIT, i);
            }
        } else {
            setResult(Main4Activity.RESULT_ADD, i);
        }
        finish();
    }
}
