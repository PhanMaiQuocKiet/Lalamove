package com.example.lalamove.View.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.KhachHang.DangKyKhachHangActivity;
import com.example.lalamove.View.Home.TaiXe.DangKyTaiXeActivity;
import com.example.lalamove.View.model.TableKhachHang.QuerySql_KhachHang;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;

public class DangNhapActivity extends AppCompatActivity {

    private EditText edt_matkhau, edt_sodienthoai_dangnhap;
    private ImageView showPasswordImageView;
    private TextView tv_DangKy, tv_QuenMatKhau, tv_dangkylamtaixe;
    private Button btn_DangNhap;
    private boolean isPasswordVisible = false;
    private QuerySql_KhachHang querySql;
    private SharedPreferences sharedPreferences,sharedPreferences2;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("ThongTinDangNhap", MODE_PRIVATE);
        editor = sharedPreferences2.edit();
        // Ánh xạ
        AnhXa();
        // Xử lý hành động đăng nhập
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = edt_sodienthoai_dangnhap.getText().toString();
                String matkhau = edt_matkhau.getText().toString();
                if (!DinhDang.isDinhDangSoDienThoai(soDienThoai)) {
                    edt_sodienthoai_dangnhap.setError("Số điện thoại phải 10 số");
                } else {
                    long lockTime = sharedPreferences.getLong("lockTime", 0);
                    if (System.currentTimeMillis() < lockTime) {
                       Toast.makeText(DangNhapActivity.this, "Tài khoản bị khóa. Vui lòng thử lại sau." , Toast.LENGTH_SHORT).show();
                        return;
                   }
                    querySql = new QuerySql_KhachHang();
                    querySql.sp_search_taikhoan(soDienThoai, matkhau, DangNhapActivity.this);
                    editor.putString("sodienthoai",soDienThoai);
                    editor.apply();
                }
            }
        });

        tv_dangkylamtaixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyTaiXeActivity.class);
                startActivity(intent);
            }
        });

        showPasswordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    edt_matkhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_hide);
                } else {
                    // Show password
                    edt_matkhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_show);
                }
                isPasswordVisible = !isPasswordVisible;
                edt_matkhau.setSelection(edt_matkhau.getText().length());
            }
        });

        tv_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyKhachHangActivity.class);
                startActivity(intent);
            }
        });

        tv_QuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, QuenmkActivity.class);
                startActivity(intent);
            }
        });
    }

    void AnhXa() {
        tv_DangKy = findViewById(R.id.tv_chuacoTK);
        edt_matkhau = findViewById(R.id.edt_matkhau_dangnhap);
        edt_sodienthoai_dangnhap = findViewById(R.id.edt_sodienthoai_dangNhap);
        showPasswordImageView = findViewById(R.id.show_password);
        tv_QuenMatKhau = findViewById(R.id.tv_forgot_password);
        btn_DangNhap = findViewById(R.id.btn_dangnhap);
        tv_dangkylamtaixe = findViewById(R.id.tv_DangNhap_DangKy_TaiXe);
    }


}
