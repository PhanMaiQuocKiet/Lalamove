package com.example.lalamove.View.Home.TaiXe;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableLoaiPhuongTien.LoaiPhuongTien;
import com.example.lalamove.View.model.TableTaiXe.TaiXeSQL;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;
import com.example.lalamove.View.model.XacThucvaDinhDang.XacThuc;

public class DangKyTaiXeActivity extends AppCompatActivity {
    private EditText edt_sodienthoai_TaiXe, edt_matkhau_TaiXe, edt_ten_TaiXe, edt_Bienso_vanchuyen;
    private Spinner spinner_phuongtien;
    private Button btn_DangKy_TaiKhoanTaiXe;
    private ImageView showPasswordButton;
    private String loaiTaiKhoan = "TaiXe";
    private boolean isPasswordVisible = false;
    private TextView tv_DaCoTaiKhoan_TaiXe ;
    private ActivityResultLauncher<Intent> otpIntent;
    String soDienThoai,matKhau,ten,bienso,tenphuongtien,loaiphuongtien,maphuongtien;
    TaiXeSQL taiXeSQL;
    LoaiPhuongTien loaiPhuongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky_tai_xe);
        taoIntent();
        XacThuc xacthuc = XacThuc.getInstance(DangKyTaiXeActivity.this,otpIntent);
        taiXeSQL = new TaiXeSQL();
        loaiPhuongTien = new LoaiPhuongTien();
        //AnhXa
        AnhXa();
        //tải dữ liệu phương tiện
        loadPhuongTienData();
        tv_DaCoTaiKhoan_TaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    edt_matkhau_TaiXe.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_show);
                } else {
                    edt_matkhau_TaiXe.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_hide);
                }
                isPasswordVisible = !isPasswordVisible;
                edt_matkhau_TaiXe.setSelection(edt_matkhau_TaiXe.getText().length());
            }
        });

        btn_DangKy_TaiKhoanTaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               soDienThoai = edt_sodienthoai_TaiXe.getText().toString();
                 matKhau = edt_matkhau_TaiXe.getText().toString();
                 ten =(edt_ten_TaiXe.getText().toString());
                ten = ten.trim().replaceAll("\\s+", " ");
                 bienso = edt_Bienso_vanchuyen.getText().toString();
                 tenphuongtien = spinner_phuongtien.getSelectedItem().toString();
                 maphuongtien = loaiPhuongTien.getMaPhuongTien(tenphuongtien, DangKyTaiXeActivity.this);

                // Validate input
                if (!DinhDang.isDinhDangSoDienThoai(soDienThoai)) {
                    edt_sodienthoai_TaiXe.setError("Số điện thoại phải đủ 10 số");
                } else if (taiXeSQL.isTaiKhoanTonTai(soDienThoai, DangKyTaiXeActivity.this)) {
                    edt_sodienthoai_TaiXe.setError("Số điện thoại đã được đăng ký");
                } else if (!DinhDang.isDinhDangMatKhau(matKhau)) {
                    edt_matkhau_TaiXe.setError("1 ký tự in hoa, và 1 số");
                } else if (!DinhDang.isDinhDangTen(ten)) {
                    edt_ten_TaiXe.setError("Tên không đúng định dạng");
                } else if (!DinhDang.isDinhDangBienSoXe(bienso)) {
                    edt_Bienso_vanchuyen.setError("Biển số xe có 5 số");
                } else {
                    xacthuc.guiYeuCauOTP(DinhDang.dinhDangSDT(soDienThoai));
                }
            }
        });
    }
    void taoIntent()
    {
        otpIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Intent data = result.getData();
                        if(data!=null&&data.getBooleanExtra("kq",false)==true)
                        {
                            try {
                                taiXeSQL.sp_insert_TaiKhoanTaiXe(soDienThoai, ten, matKhau, loaiTaiKhoan, null, soDienThoai, bienso, maphuongtien, null, DangKyTaiXeActivity.this);
                                Toast.makeText(DangKyTaiXeActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT);
                                finish();
                            } catch (Exception e) {
                                Log.e(TAG, "Lỗi khi đăng ký tài khoản: " + e.getMessage());
                            }
                        }
                        else
                        {
                            Toast.makeText(DangKyTaiXeActivity.this,"Xac thuc OTP khong thanh cong",Toast.LENGTH_SHORT);
                        }
                    }
                });//
    }
    void AnhXa() {
        edt_sodienthoai_TaiXe = findViewById(R.id.edt_sodienthoai_TaiXe);
        edt_matkhau_TaiXe = findViewById(R.id.edt_matkhau_TaiXe);
        edt_ten_TaiXe = findViewById(R.id.edt_ten_TaiXe);
        spinner_phuongtien = findViewById(R.id.spinner_phuongtien);
        btn_DangKy_TaiKhoanTaiXe = findViewById(R.id.btn_DangKy_TaiKhoanTaiXe);
        showPasswordButton = findViewById(R.id.show_password);
        edt_Bienso_vanchuyen = findViewById(R.id.edt_Bienso_vanchuyen);
        tv_DaCoTaiKhoan_TaiXe= findViewById(R.id.tv_DaCoTaiKhoan_TaiXe);
    }

    void loadPhuongTienData() {
        try {
            LoaiPhuongTien loaiPhuongTien = new LoaiPhuongTien(this, spinner_phuongtien);
            loaiPhuongTien.loadDataTenPhuongTien();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tải dữ liệu phương tiện: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
