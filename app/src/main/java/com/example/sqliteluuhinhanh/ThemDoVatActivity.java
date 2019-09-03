package com.example.sqliteluuhinhanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class ThemDoVatActivity extends AppCompatActivity {

    Button btnThem, btnHuy;
    ImageView imgHinh;
    ImageButton ibtnCamera, ibtnFolder;
    EditText edtTen, edtMoTa;

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        AnhXa();

        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chuyen data image  -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] HinhAnh = byteArray.toByteArray();

                MainActivity.database.INSERT_DOVAT(
                        edtTen.getText().toString().trim(),
                        edtMoTa.getText().toString().trim(),
                        HinhAnh
                );
                Toast.makeText(ThemDoVatActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
