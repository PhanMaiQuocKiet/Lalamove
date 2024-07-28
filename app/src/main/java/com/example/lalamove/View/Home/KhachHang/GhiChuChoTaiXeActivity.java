package com.example.lalamove.View.Home.KhachHang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class GhiChuChoTaiXeActivity extends AppCompatActivity {
    EditText edt_GhiChuChoTaiXe ;
    Button btn_Luu_GhiChuChoTaiXe;
    ImageButton btn_Dong_GhiChuChoTaiXe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bosungchitiet_ghichutaixe);
        //Anh Xa
        AnhXa();
        sharedPreferences = this.getSharedPreferences("LuongDatHang",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("ghichuchotaixe","");
        btn_Luu_GhiChuChoTaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ghiChu = edt_GhiChuChoTaiXe.getText().toString();
                editor.putString("ghichuchotaixe",ghiChu);
                editor.apply();

                Intent i = new Intent(GhiChuChoTaiXeActivity.this,BuocTiepTheoActivity.class);
                startActivity(i);
            }
        });
        btn_Dong_GhiChuChoTaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("ghichuchotaixe");
                editor.apply();
                Intent i = new Intent(GhiChuChoTaiXeActivity.this,BuocTiepTheoActivity.class);
                startActivity(i);
            }
        });

    }
    void AnhXa(){
        edt_GhiChuChoTaiXe = findViewById(R.id.edt_GhiChuChoTaiXe);
        btn_Luu_GhiChuChoTaiXe = findViewById(R.id.btn_Luu_GhiChuChoTaiXe);
        btn_Dong_GhiChuChoTaiXe = findViewById(R.id.btn_Dong_GhiChuChoTaiXe);
    }
}
