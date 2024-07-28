package com.example.lalamove.View.Home.KhachHang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class TrungTamTroGiupActivity extends AppCompatActivity {
    ImageButton btn_back_TrungTamTroGiup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trungtamtrogiup);
        //Anh Xa
        AnhXa();
        btn_back_TrungTamTroGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void AnhXa(){
        btn_back_TrungTamTroGiup = findViewById(R.id.btn_back_TrungTamTroGiup);
    }
}
