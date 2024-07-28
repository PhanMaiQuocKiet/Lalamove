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

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
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

        // Khởi tạo đối tượng TaiXeSQL
        taiXeSQL = new TaiXeSQL();

        // Xử lý sự kiện nhấn nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Xử lý sự kiện nhấn nút sửa tên
        imgEditName.setOnClickListener(v -> updateName());

        // Xử lý sự kiện nhấn nút sửa số điện thoại
        imgEditPhone.setOnClickListener(v -> updatePhone());

        // Xử lý sự kiện nhấn nút sửa chức vụ
        imgEditChucVu.setOnClickListener(v -> updateChucVu());

        // Xử lý sự kiện nhấn nút sửa mã phương tiện
        imgEditMaPhuongTien.setOnClickListener(v -> updateMaPhuongTien());

        // Xử lý sự kiện nhấn nút sửa biển số phương tiện
        imgEditBienSoPhuongTien.setOnClickListener(v -> updateBienSoPhuongTien());
    }

    private void updateName() {
        String name = edtName.getText().toString().trim();
        if (!name.isEmpty()) {
            // Thực hiện cập nhật tên
            // Đây có thể là một phương thức cập nhật dữ liệu vào cơ sở dữ liệu
            // Ví dụ: taiXeSQL.updateName(name, this);
            Toast.makeText(this, "Tên đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePhone() {
        String phone = edtPhone.getText().toString().trim();
        if (!phone.isEmpty()) {
            // Thực hiện cập nhật số điện thoại
            // Ví dụ: taiXeSQL.updatePhone(phone, this);
            Toast.makeText(this, "Số điện thoại đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateChucVu() {
        String chucVu = edtChucVu.getText().toString().trim();
        if (!chucVu.isEmpty()) {
            // Thực hiện cập nhật chức vụ
            // Ví dụ: taiXeSQL.updateChucVu(chucVu, this);
            Toast.makeText(this, "Chức vụ đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập chức vụ", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateMaPhuongTien() {
        String maPhuongTien = edtMaPhuongTien.getText().toString().trim();
        if (!maPhuongTien.isEmpty()) {
            // Thực hiện cập nhật mã phương tiện
            // Ví dụ: taiXeSQL.updateMaPhuongTien(maPhuongTien, this);
            Toast.makeText(this, "Mã phương tiện đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập mã phương tiện", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBienSoPhuongTien() {
        String bienSoPhuongTien = edtBienSoPhuongTien.getText().toString().trim();
        if (!bienSoPhuongTien.isEmpty()) {
            // Thực hiện cập nhật biển số phương tiện
            // Ví dụ: taiXeSQL.updateBienSoPhuongTien(bienSoPhuongTien, this);
            Toast.makeText(this, "Biển số phương tiện đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập biển số phương tiện", Toast.LENGTH_SHORT).show();
        }
    }
}
