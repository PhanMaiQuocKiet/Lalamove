package com.example.lalamove.View.Home.TaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.TaiXe.DonHang.DonHangActivity;
import com.example.lalamove.View.Login.DangNhapActivity;

public class TrangChuTaiXeActivity extends AppCompatActivity {
    private TextView tv_TaiXe_DangXuat;
    ImageView img_TrangChuTaiXe_DonHang,tttk_tx;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu_tai_xe);
        //Ánh xạ
        AnhXa();
        //Đăng xuất
        tv_TaiXe_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuTaiXeActivity.this, DangNhapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Kết thúc Activity2 để không giữ lại trên stack
            }
        });
        img_TrangChuTaiXe_DonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrangChuTaiXeActivity.this, DonHangActivity.class);
                startActivity(i);
            }
        });
        tttk_tx.setOnClickListener(c->{
            Intent intent = new Intent(TrangChuTaiXeActivity.this, ThongTinTK_TaiXe.class);
            startActivity(intent);
        });



    }

    //Ánh xạ
    void AnhXa(){
        img_TrangChuTaiXe_DonHang = findViewById(R.id.img_TrangChuTaiXe_DonHang);
        tv_TaiXe_DangXuat= findViewById(R.id.tv_TaiXe_DangXuat);
        tttk_tx=findViewById(R.id.tttk_taixe);
    }




}
