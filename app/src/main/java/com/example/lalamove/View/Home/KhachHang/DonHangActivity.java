package com.example.lalamove.View.Home.KhachHang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class DonHangActivity extends AppCompatActivity {
    ImageView img_QuayLai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang);
        //Anh Xa
        AnhXa();
        img_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    void AnhXa(){
        img_QuayLai = findViewById(R.id.img_QuayLai);
    }
}
