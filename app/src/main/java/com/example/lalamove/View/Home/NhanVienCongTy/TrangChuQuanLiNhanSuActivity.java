package com.example.lalamove.View.Home.NhanVienCongTy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan.QuanLiTaiKhoanActivity;

public class TrangChuQuanLiNhanSuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu_quan_li_nhan_su);
        LinearLayout lnThongTinTaiKhoan = findViewById(R.id.ln_ThongTinTaiKhoan);
        lnThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuQuanLiNhanSuActivity.this, QuanLiTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout lg_out = findViewById(R.id.ln_log_out);
        lg_out.setOnClickListener(c->{
            finish();
        });

    }
}
