package com.example.lalamove.View.Home.TaiXe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.KhachHang.Home_KhachHang;
import com.example.lalamove.View.Home.KhachHang.ThongTinTK_KhachHang;
import com.example.lalamove.View.model.TableTaiXe.TaiXeSQL;

public class ThongTinTK_TaiXe extends AppCompatActivity {

    private static final String TAG = "ThongTinTK_TaiXe";

    private ImageButton btnBack;
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtChucVu;
    private EditText edtDiemDanhGia;
    private EditText edtMaPhuongTien;
    private EditText edtBienSoPhuongTien;
    private ImageView imgEditName;
    private ImageView imgEditPhone;
    private ImageView imgEditChucVu;
    private ImageView imgEditMaPhuongTien;
    private ImageView imgEditBienSoPhuongTien;

    private TaiXeSQL taiXeSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitiettaixe); // Tên file layout tương ứng

        // Khởi tạo các thành phần UI
        btnBack = findViewById(R.id.btn_back_hosotx);
        edtName = findViewById(R.id.Ten);
        edtPhone = findViewById(R.id.sdt);
        edtChucVu = findViewById(R.id.chucvu_1);
        edtDiemDanhGia = findViewById(R.id.diemdanhgia);
        edtMaPhuongTien = findViewById(R.id.maphuongtien);
        edtBienSoPhuongTien = findViewById(R.id.biensophuongtien);
        imgEditName = findViewById(R.id.insert_name);
        imgEditPhone = findViewById(R.id.insert_sdt);
        imgEditChucVu = findViewById(R.id.insert_gmail);
        imgEditMaPhuongTien = findViewById(R.id.insert_maphuongtien);
        imgEditBienSoPhuongTien = findViewById(R.id.insert_biensophuongtien);

        taiXeSQL = new TaiXeSQL();

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ThongTinTK_TaiXe.this, TrangChuTaiXeActivity.class);
            startActivity(intent);
        });
        imgEditName.setOnClickListener(v -> updateName());

        imgEditPhone.setOnClickListener(v -> updatePhone());

        imgEditChucVu.setOnClickListener(v -> updateChucVu());

        imgEditMaPhuongTien.setOnClickListener(v -> updateMaPhuongTien());

        imgEditBienSoPhuongTien.setOnClickListener(v -> updateBienSoPhuongTien());
    }

    private void updateName() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        if (!name.isEmpty() && !phone.isEmpty()) {
            taiXeSQL.updateName(phone, name, this);
        } else {
            Toast.makeText(this, "Vui lòng nhập tên và số điện thoại", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePhone() {
        String newPhone = edtPhone.getText().toString().trim();
        String oldPhone = "";

        if (!newPhone.isEmpty()) {
            if (!oldPhone.isEmpty()) {
                taiXeSQL.updatePhone(oldPhone, newPhone, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại cũ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập số điện thoại mới", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateChucVu() {
        String newChucVu = edtChucVu.getText().toString().trim();
        String phone = "";

        if (!newChucVu.isEmpty()) {
            if (!phone.isEmpty()) {
                taiXeSQL.updateChucVu(phone, newChucVu, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập chức vụ", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateMaPhuongTien() {
        String newMaPhuongTien = edtMaPhuongTien.getText().toString().trim();
        String phone = "";

        if (!newMaPhuongTien.isEmpty()) {
            if (!phone.isEmpty()) {
                taiXeSQL.updateMaPhuongTien(phone, newMaPhuongTien, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập mã phương tiện", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBienSoPhuongTien() {
        String newBienSoPhuongTien = edtBienSoPhuongTien.getText().toString().trim();
        String phone = "";

        if (!newBienSoPhuongTien.isEmpty()) {
            if (!phone.isEmpty()) {
                taiXeSQL.updateBienSoPhuongTien(phone, newBienSoPhuongTien, this);
            } else {
                Toast.makeText(this, "Không tìm thấy số điện thoại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập biển số phương tiện", Toast.LENGTH_SHORT).show();
        }
    }
}
