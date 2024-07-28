package com.example.lalamove.View.Home.NhanVienCongTy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class TrangChuQuanLiNhanSuActivity extends AppCompatActivity {
    TextView tv_TrangChuQuanLyNhanSu;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu_quan_li_nhan_su);

        //Anh Xa
        AnhXa();
        tv_TrangChuQuanLyNhanSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void AnhXa(){
        tv_TrangChuQuanLyNhanSu = findViewById(R.id.tv_TrangChuQuanLyNhanSu);
    }
}
