package com.example.admin.lab04;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnShow, btnSave;
    EditText edtUserName, edtPass;
    CheckBox cbShow;
    public static final String MY_PREFS = "com.example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPass = findViewById(R.id.edtPass);
        edtUserName = findViewById(R.id.edtUserName);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        cbShow = findViewById(R.id.cbShow);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mypref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = mypref.edit();
                editor.putString("username",edtUserName.getText().toString());
                editor.putString("password",edtPass.getText().toString());
                editor.commit();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mypref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
                edtUserName.setText(mypref.getString("username",""));
                edtPass.setText(mypref.getString("password",""));
            }
        });

        cbShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
