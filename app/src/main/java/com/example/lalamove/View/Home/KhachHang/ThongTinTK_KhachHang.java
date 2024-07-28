package com.example.lalamove.View.Home.KhachHang;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;

public class ThongTinTK_KhachHang extends AppCompatActivity {

    private EditText editTextTen, editTextSdt, editTextGmail;
    private TaiKhoanSQL taiKhoanSQL;
    private String soDienThoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitietkhachhang);

        editTextTen = findViewById(R.id.Ten);
        editTextSdt = findViewById(R.id.sdt);
        editTextGmail = findViewById(R.id.Gmail_1);
        ImageButton btnBack = findViewById(R.id.btn_back_hosokh);
        ImageView insertName = findViewById(R.id.insert_name);
        ImageView insertSdt = findViewById(R.id.insert_sdt);
        ImageView insertGmail = findViewById(R.id.insert_gmail);

        taiKhoanSQL = new TaiKhoanSQL();

        loadThongTinTaiKhoan();

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(ThongTinTK_KhachHang.this, Home_KhachHang.class);
            startActivity(intent);
        });

        insertName.setOnClickListener(view -> updateTenTaiKhoan());

        insertSdt.setOnClickListener(view -> updateSdtTaiKhoan());

        insertGmail.setOnClickListener(view -> updateGmailTaiKhoan());
    }

    private void loadThongTinTaiKhoan() {
        soDienThoai = "";
        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(soDienThoai, this);
        if (!loaiTaiKhoan.isEmpty()) {
            editTextTen.setText("Tên khách hàng");
            editTextSdt.setText(soDienThoai);
            editTextGmail.setText("example@gmail.com");
        } else {
            Toast.makeText(this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTenTaiKhoan() {
        String tenMoi = editTextTen.getText().toString().trim();
        if (!tenMoi.isEmpty()) {
            if (!soDienThoai.isEmpty()) {
                taiKhoanSQL.updateTenTaiKhoan(soDienThoai, tenMoi, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSdtTaiKhoan() {
        String sdtMoi = editTextSdt.getText().toString().trim();
        if (!sdtMoi.isEmpty()) {
            if (!soDienThoai.isEmpty()) {
                taiKhoanSQL.updateSdtTaiKhoan(soDienThoai, sdtMoi, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại hiện tại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập số điện thoại mới", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateGmailTaiKhoan() {
        String gmailMoi = editTextGmail.getText().toString().trim();
        if (!gmailMoi.isEmpty()) {
            if (!soDienThoai.isEmpty()) {
                taiKhoanSQL.updateGmailTaiKhoan(soDienThoai, gmailMoi, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập Gmail mới", Toast.LENGTH_SHORT).show();
        }
    }

}
