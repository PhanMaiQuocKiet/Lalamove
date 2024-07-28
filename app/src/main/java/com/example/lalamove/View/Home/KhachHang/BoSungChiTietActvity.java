package com.example.lalamove.View.Home.KhachHang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableLoaiHangVanChuyen.QuerySql;

public class BoSungChiTietActvity extends AppCompatActivity {
    RadioGroup radioGroup;
    EditText edt_SoLuongGoiHang;
    Button btn_luu;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton btn_TruSoLuong, btn_CongSoLuong;
    int soLuongGoiHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bosungchitiet);
        // AnhXa
        AnhXa();

        // Khai báo sharedPreferences
        sharedPreferences = getSharedPreferences("LuongDatHang", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Sự kiện click
        btn_TruSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 soLuongGoiHang = Integer.parseInt(edt_SoLuongGoiHang.getText().toString());
                if (soLuongGoiHang > 0) {
                    soLuongGoiHang--;
                    edt_SoLuongGoiHang.setText(String.valueOf(soLuongGoiHang));
                }
            }
        });

        btn_CongSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 soLuongGoiHang = Integer.parseInt(edt_SoLuongGoiHang.getText().toString());
                soLuongGoiHang++;
                edt_SoLuongGoiHang.setText(String.valueOf(soLuongGoiHang));
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Lấy ID của RadioButton được chọn
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) { // Kiểm tra nếu có RadioButton được chọn
                    // Tìm RadioButton từ ID
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    // Lấy giá trị từ RadioButton
                    String selectedValue = selectedRadioButton.getText().toString().trim();
                    selectedValue = selectedValue.replaceAll("\\s+", " ");

                    QuerySql querySql = new QuerySql();
                    String maLoaiHangVanChuyen = querySql.getMaLoaiHangVanChuyen(selectedValue, BoSungChiTietActvity.this);

                    // Lưu dữ liệu vào SharedPreferences
                    editor.putString("tenloaihangvanchuyen", selectedValue);
                    editor.putString("maloaihangvanchuyen", maLoaiHangVanChuyen);
                    editor.putInt("soluongthunghang", soLuongGoiHang);
                    editor.apply(); // Lưu thay đổi
                    Intent i = new Intent(BoSungChiTietActvity.this, BuocTiepTheoActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(BoSungChiTietActvity.this, "Vui lòng chọn một tùy chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void AnhXa() {
        radioGroup = findViewById(R.id.radioGroup);
        edt_SoLuongGoiHang = findViewById(R.id.edt_SoLuongGoiHang);
        btn_luu = findViewById(R.id.btn_luu);
        btn_TruSoLuong = findViewById(R.id.btn_TruSoLuong);
        btn_CongSoLuong = findViewById(R.id.btn_CongSoLuong);
    }
}
