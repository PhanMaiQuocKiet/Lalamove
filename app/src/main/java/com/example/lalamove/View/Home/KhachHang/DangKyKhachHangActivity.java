package com.example.lalamove.View.Home.KhachHang;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Login.LoginActivity;
import com.example.lalamove.View.model.QuerySql;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;

public class DangKyKhachHangActivity extends AppCompatActivity {

    private EditText edt_matkhau_khachhang, edt_sodienthoai_khachhang,edt_email_khachhang,edt_ten_khachhang;
    private ImageView showPasswordImageView;
    private Button btn_DangKy ;
    private TextView tv_DaCoTaiKhoan;
    private boolean isPasswordVisible = false;
    ConnectionHelper connectionHelper = new ConnectionHelper();
    Connection connection;
    QuerySql querySql = new QuerySql();
    String role = "KhachHang";
    CheckBox cb_DieuKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky_khachhang);
        //Anh Xa
        AnhXa();
        showPasswordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    edt_matkhau_khachhang.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_show); // Change to eye icon
                } else {
                    // Show password
                    edt_matkhau_khachhang.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordImageView.setImageResource(R.drawable.ic_hide); // Change to eye closed icon
                }
                isPasswordVisible = !isPasswordVisible;
                // Move the cursor to the end of the text
                edt_matkhau_khachhang.setSelection(edt_matkhau_khachhang.getText().length());
            }
        });

            btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = edt_sodienthoai_khachhang.getText().toString();
                String email = edt_email_khachhang.getText().toString();
                String matKhau = edt_matkhau_khachhang.getText().toString();
                String ten = edt_ten_khachhang.getText().toString();
                if(!isDinhDangSoDienThoai(soDienThoai)){
                    edt_sodienthoai_khachhang.setError("Số điện thoại phải đủ 10 số");
                }
                else if(!isDinhDangEmail(email)){
                    edt_email_khachhang.setError("email phải đúng đinh dạng abc@...com");
                }
                else if(!isDinhDangMatKhau(matKhau)){
                    edt_matkhau_khachhang.setError("Số điện thoại phải đủ 10 số");
                }
                else if(!isDinhDangTen(ten)){
                    edt_ten_khachhang.setError("Tên không đúng định dạng");
                }
                else
                {
                    try{
                        querySql.sp_insert_TaiKhoan(soDienThoai,ten,matKhau,role,null, DangKyKhachHangActivity.this);
                        Intent intent = new Intent(DangKyKhachHangActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Log.e(TAG, e.getMessage(), null);
                    }

                }
            }
        });

        tv_DaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyKhachHangActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    //Anh Xa
    void AnhXa()
    {
        edt_sodienthoai_khachhang = findViewById(R.id.edt_sodienthoai_khachhang);
        edt_email_khachhang = findViewById(R.id.edt_email_khachang);
        edt_matkhau_khachhang = findViewById(R.id.edt_matkhau_khachhang);
        edt_ten_khachhang= findViewById(R.id.edt_ten_khachhang);
        showPasswordImageView = findViewById(R.id.show_password);
        btn_DangKy = findViewById(R.id.btn_DangKy);
        tv_DaCoTaiKhoan= findViewById(R.id.tv_DaCoTaiKhoan);
        cb_DieuKhoan = findViewById(R.id.cb_dieukhoan);
    }

    //Kiem Tra Dinh Dang
    public boolean isDinhDangSoDienThoai(String phoneNumber) {
        // Số điện thoại phải có 10 chữ số
        String regex = "^[0-9]{10}$";
        return phoneNumber.matches(regex);
    }
    public boolean isDinhDangEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
    public boolean isDinhDangMatKhau(String password) {
        // Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(regex);
    }
    public boolean isDinhDangTen(String name) {
        // Biểu thức chính quy để kiểm tra tên chỉ chứa chữ cái, khoảng trắng và dấu gạch ngang
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }


}