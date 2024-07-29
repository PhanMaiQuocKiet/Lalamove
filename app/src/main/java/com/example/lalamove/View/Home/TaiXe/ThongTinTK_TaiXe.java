package com.example.lalamove.View.Home.TaiXe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.TableTaiXe.TaiXeSQL;

public class ThongTinTK_TaiXe extends AppCompatActivity {

    private static final String TAG = "ThongTinTK_TaiXe";

    private ImageButton btnBack;
     EditText edtName;
     EditText edtPhone;
     EditText edtBienSoPhuongTien;
    private ImageView imgEditName;
    private ImageView imgEditPhone;
    private TaiKhoanSQL taiXeSQL;
    String soDienThoaiDN = "";
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


        //Lấy dữ liệu điện thoại từ share
        SharedPreferences sharedPreferences = this.getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        soDienThoaiDN = sharedPreferences.getString("sodienthoai","");

        // Khởi tạo đối tượng TaiXeSQL
        taiXeSQL = new TaiKhoanSQL();

        // Xử lý sự kiện nhấn nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Xử lý sự kiện nhấn nút sửa tên
        imgEditName.setOnClickListener(v -> updateName(ThongTinTK_TaiXe.this));

        // Xử lý sự kiện nhấn nút sửa số điện thoại
        imgEditPhone.setOnClickListener(v -> updatePhone(ThongTinTK_TaiXe.this));

    }

    private void updateName(Context context) {
        String name = edtName.getText().toString().trim();
        if (!name.isEmpty()) {
            // Thực hiện cập nhật tên
            // Đây có thể là một phương thức cập nhật dữ liệu vào cơ sở dữ liệu
            // Ví dụ: taiXeSQL.updateName(name, this);
            taiXeSQL.updateTenTaiKhoan(soDienThoaiDN,name,context);
            Toast.makeText(this, "Tên đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePhone(Context context) {
        String phone = edtPhone.getText().toString().trim();
        if (!phone.isEmpty()) {
            // Thực hiện cập nhật số điện thoại

            taiXeSQL.updateSdtTaiKhoan(soDienThoaiDN,phone,context);
            Toast.makeText(this, "Số điện thoại đã được cập nhật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
    }




}
