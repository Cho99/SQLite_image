package com.example.sqliteluuhinhanh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ThemDoVatActivity extends AppCompatActivity {

    Button btnThem, btnHuy;
    ImageView imgHinh;
    ImageButton ibtnCamera, ibtnFolder;
    EditText edtTen, edtMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        AnhXa();
    }

    private void AnhXa() {
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnHuy = (Button) findViewById(R.id.buttonHuy);
        imgHinh = (ImageView) findViewById(R.id.imageView);
        ibtnCamera  = (ImageButton) findViewById(R.id.imageButtonCamera);
        ibtnFolder = (ImageButton) findViewById(R.id.imageButtonFolder);
        edtTen = (EditText) findViewById(R.id.editTextName);
        edtMoTa = (EditText) findViewById(R.id.editTextDescription);
    }
}
