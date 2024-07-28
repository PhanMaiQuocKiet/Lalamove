package com.example.lalamove.View.Home.KhachHang;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableKhachHang.QuerySql_KhachHang;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;
import com.example.lalamove.View.model.XacThucvaDinhDang.XacThuc;
import com.example.lalamove.database.data.ConnectionHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;

public class DangKyKhachHangActivity extends AppCompatActivity {

    private EditText edt_matkhau_khachhang, edt_sodienthoai_khachhang,edt_email_khachhang,edt_ten_khachhang;
    private ImageView showPasswordImageView;
    private Button btn_DangKy ;
    private FirebaseAuth mAuth;
    private TextView tv_DaCoTaiKhoan;
    private boolean isPasswordVisible = false;
    private ActivityResultLauncher<Intent> otpIntent;
    String soDienThoai,email,matKhau,ten;

    ConnectionHelper connectionHelper = new ConnectionHelper();
    Connection connection;
    QuerySql_KhachHang querySql = new QuerySql_KhachHang();
    String role = "KhachHang";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky_khachhang);
        taoIntent();
        XacThuc xacthuc = XacThuc.getInstance(DangKyKhachHangActivity.this, otpIntent);
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
                 soDienThoai = edt_sodienthoai_khachhang.getText().toString();
                 matKhau = edt_matkhau_khachhang.getText().toString();
                 ten = edt_ten_khachhang.getText().toString();
                if(!DinhDang.isDinhDangSoDienThoai(soDienThoai)){
                    edt_sodienthoai_khachhang.setError("Số điện thoại phải đủ 10 số");
                }
                else if(!DinhDang.isDinhDangMatKhau(matKhau)) {
                    edt_matkhau_khachhang.setError("Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
                }
                else if(!DinhDang.isDinhDangMatKhau(matKhau)){
                    edt_matkhau_khachhang.setError("Mật khẩu phải có ít nhất 1 kí tự đặc biệt và in hoa");

                }
                else if(!DinhDang.isDinhDangTen(ten)){
                    edt_ten_khachhang.setError("Tên không đúng định dạng");
                }
                else
                {
                    String sdt = DinhDang.dinhDangSDT(soDienThoai);
                    xacthuc.guiYeuCauOTP(sdt);
                }
            }
            });

        tv_DaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    void taoIntent()
    {
        otpIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),rs->{
            if (rs.getResultCode() == RESULT_OK)
            {
                Intent data = rs.getData();
                if(data!=null&&data.getBooleanExtra("kq",false)==true)
                {
                    try {
                        querySql.sp_insert_TaiKhoan(soDienThoai, ten, matKhau,role, null,DangKyKhachHangActivity.this);
                        Toast.makeText(DangKyKhachHangActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT);
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(DangKyKhachHangActivity.this,"LOI KHI DANG KY TAI KHOAN",Toast.LENGTH_SHORT);
                        Log.e(TAG, "Lỗi khi đăng ký tài khoản: " + e.getMessage());
                    }
                }
                else
                {
                    Toast.makeText(DangKyKhachHangActivity.this,"Xac thuc OTP khong thanh cong",Toast.LENGTH_SHORT);
                }
            }
        });//

    }

    //Anh Xa
    void AnhXa()
    {
        edt_sodienthoai_khachhang = findViewById(R.id.edt_sodienthoai_khachhang);
        edt_matkhau_khachhang = findViewById(R.id.edt_matkhau_khachhang);
        edt_ten_khachhang= findViewById(R.id.edt_ten_khachhang);
        showPasswordImageView = findViewById(R.id.show_password);
        btn_DangKy = findViewById(R.id.btn_DangKy);
        tv_DaCoTaiKhoan= findViewById(R.id.tv_DaCoTaiKhoan);

    }
}