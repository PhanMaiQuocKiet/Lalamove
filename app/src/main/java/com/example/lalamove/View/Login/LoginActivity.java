package com.example.lalamove.View.Login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.KhachHang.DangKyKhachHangActivity;
import com.example.lalamove.View.model.QuerySql;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_matkhau,edt_sodienthoai_dangnhap;
    private ImageView showPasswordImageView;
    TextView tv_DangKy , tv_QuenMatKhau;
    private Button btn_DangNhap;
    private boolean isPasswordVisible = false;
    QuerySql  querySql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        //Ánh Xạ
        AnhXa();

        //Xử lý hành động đăng nhập
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = edt_sodienthoai_dangnhap.getText().toString();
                String matkhau = edt_matkhau.getText().toString();
                querySql = new QuerySql();
                querySql.sp_search_taikhoan(soDienThoai,matkhau,LoginActivity.this);
            }
        });

        showPasswordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    edt_matkhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_hide); // Change to eye icon
                } else {
                    // Show password
                    edt_matkhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_show); // Change to eye closed icon
                }
                isPasswordVisible = !isPasswordVisible;
                // Move the cursor to the end of the text
                edt_matkhau.setSelection(edt_matkhau.getText().length());
            }
        });
        tv_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DangKyKhachHangActivity.class);
                startActivity(intent);
            }
        });
        tv_QuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    void AnhXa(){
         tv_DangKy = findViewById(R.id.sign_up);
        edt_matkhau = findViewById(R.id.edt_matkhau_dangnhap);
        edt_sodienthoai_dangnhap = findViewById(R.id.edt_sodienthoai_dangNhap);
        showPasswordImageView = findViewById(R.id.show_password);
        tv_QuenMatKhau= findViewById(R.id.forgot_password);
        btn_DangNhap = findViewById(R.id.btn_dangnhap);
    }

}