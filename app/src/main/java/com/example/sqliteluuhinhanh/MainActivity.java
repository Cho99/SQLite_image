package com.example.sqliteluuhinhanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        database = new Database(this, "QuanLy.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(255), MoTa VARCHAR(255), HinhANH BLOB)");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));
            }
        });
    }

    private void AnhXa() {
        btnThem = (Button) findViewById(R.id.buttonAdd);
    }
}
