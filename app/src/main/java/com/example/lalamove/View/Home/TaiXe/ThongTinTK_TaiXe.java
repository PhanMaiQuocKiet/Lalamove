package com.example.lalamove.View.Home.TaiXe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;

import com.example.lalamove.View.Home.KhachHang.Home_KhachHang;
import com.example.lalamove.View.Home.KhachHang.ThongTinTK_KhachHang;

import com.example.lalamove.View.model.TableTaiXe.TaiXeSQL;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;

import java.util.ArrayList;

public class ThongTinTK_TaiXe extends AppCompatActivity {

    private static final String TAG = "ThongTinTK_TaiXe";

    private ImageButton btnBack;
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtBienSoPhuongTien,edt_mkmoi,edt_mkcu;
    private ImageView imgEditName;
    private ImageView imgEditPhone;
    private Button btn_edit;
    private Button btn_doimk;
    private TaiXeSQL taiXeSQL;
    private TaiKhoanSQL taiKhoanSQL;
    private SharedPreferences pref;
    private String soDienThoaiDN = "";
    private String soDienThoai, mkcu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitiettaixe);

        // Khởi tạo các thành phần UI
        btnBack = findViewById(R.id.btn_back_hosotx);
        edtName = findViewById(R.id.edt_Ten_HoSoChiTiet_TaiXe);
        edtPhone = findViewById(R.id.edt_HoSoChiTiet_SDT_TaiXe);
        edtBienSoPhuongTien = findViewById(R.id.edt_HoSoChiTiet_BienSoXe_TaiXe);
        imgEditName = findViewById(R.id.insert_name);
        imgEditPhone = findViewById(R.id.insert_sdt);
        btn_edit = findViewById(R.id.btn_edit_tttkTX);
        btn_doimk = findViewById(R.id.btn_doimk_tttkTX);
        edt_mkcu = findViewById(R.id.mkcu_hscttx);
        edt_mkmoi = findViewById(R.id.mkmoi_hscttx);

        // Lấy dữ liệu điện thoại từ SharedPreferences
        SharedPreferences sharedPreferences = this.getSharedPreferences("ThongTinDangNhap", MODE_PRIVATE);
        soDienThoaiDN = sharedPreferences.getString("sodienthoai", "");

        taiXeSQL = new TaiXeSQL();
        taiKhoanSQL = new TaiKhoanSQL();


        // Xử lý sự kiện nhấn nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Tải thông tin tài khoản
        loadThongTinTaiKhoan();


        // Xử lý sự kiện nhấn nút chỉnh sửa thông tin tài khoản
        btn_edit.setOnClickListener(c -> updatetttk());

        // Xử lý sự kiện nhấn nút đổi mật khẩu
        btn_doimk.setOnClickListener(c -> doimk());
    }

    private void loadThongTinTaiKhoan() {
        pref = this.getSharedPreferences("ThongTinDangNhap", MODE_PRIVATE);
        soDienThoai = pref.getString("sodienthoai", ""); // Hoặc lấy từ Intent hoặc SharedPreferences

        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(soDienThoai, this);

        // Nếu tài khoản tồn tại, lấy thông tin và hiển thị lên giao diện
        if (!loaiTaiKhoan.isEmpty()) {
            ArrayList<String> kq = taiKhoanSQL.sp_select_taikhoan(soDienThoai, this);
            // Ví dụ: Lấy thông tin từ cơ sở dữ liệu và hiển thị lên EditText
            edtName.setText(kq.get(0));
            edtPhone.setText(kq.get(1));
            mkcu = kq.get(2);
            ArrayList<String> kq2 = taiXeSQL.sp_select_taikhoan_taixe(soDienThoai, this);
            edtBienSoPhuongTien.setText(kq2.get(0));
            // Giả sử edt_diemdg và edt_loaipt cũng đã được khai báo
            // edt_diemdg.setText(kq2.get(1));
            // edt_loaipt.setText(kq2.get(2));

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
            Toast.makeText(this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }


    private void updatetttk() {
        String ten = edtName.getText().toString();
        String sdt = edtPhone.getText().toString();
        taiKhoanSQL.updatetttk(sdt, ten, this);
        finish();
    }

    private void doimk() {
        if (!DinhDang.isDinhDangMatKhau(edt_mkmoi.getText().toString())) {
            edt_mkmoi.setError("Sai định dạng mật khẩu");

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
            if (mkcu.compareTo(edt_mkcu.getText().toString()) == 0) {
                taiKhoanSQL.sp_update_mkTaiKhoan(soDienThoai, edt_mkcu.getText().toString(), this);
                finish();
            } else {
                Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
