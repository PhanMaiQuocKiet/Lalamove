package com.example.lalamove.View.Home.KhachHang;

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
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;

import java.util.ArrayList;


public class ThongTinTK_KhachHang extends AppCompatActivity {

    private EditText editTextTen, editTextSdt, editTextGmail,edtmkcu,edtmkmoi;
    private TaiKhoanSQL taiKhoanSQL;

    private SharedPreferences pref;
    private Button btn_edit_tk,btn_doimk;
    private String soDienThoai , mkcu , mkmoi; // Biến để lưu số điện thoại hoặc ID tài khoản, cần lấy từ Intent hoặc SharedPreferences

    private String soDienThoai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitietkhachhang);

        editTextTen = findViewById(R.id.Ten);
        editTextSdt = findViewById(R.id.sdt);
        ImageButton btnBack = findViewById(R.id.btn_back_hosokh);

        edtmkcu=findViewById(R.id.mkcu_hsctkh);
        edtmkmoi=findViewById(R.id.mkmoi_hsctkh);
        btn_edit_tk=findViewById(R.id.btn_edit_tttkKH);
        btn_doimk=findViewById(R.id.btn_doimk_tttkKH);


        taiKhoanSQL = new TaiKhoanSQL();

        loadThongTinTaiKhoan();

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(ThongTinTK_KhachHang.this, Home_KhachHang.class);
            startActivity(intent);
        });




        btn_doimk.setOnClickListener(c->{
            doimk();
        });
        btn_edit_tk.setOnClickListener(c->{
            updatetttk();
        });

    }

    private void loadThongTinTaiKhoan() {
        // Đây là ví dụ, bạn cần thay thế giá trị của soDienThoai bằng giá trị thực tế
        pref = this.getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        soDienThoai = pref.getString("sodienthoai",""); // Hoặc lấy từ Intent hoặc SharedPreferences


        insertName.setOnClickListener(view -> updateTenTaiKhoan());

        insertSdt.setOnClickListener(view -> updateSdtTaiKhoan());

        insertGmail.setOnClickListener(view -> updateGmailTaiKhoan());
    }

    private void loadThongTinTaiKhoan() {
        soDienThoai = "";

        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(soDienThoai, this);
        if (!loaiTaiKhoan.isEmpty()) {

            ArrayList<String> kq = taiKhoanSQL.sp_select_taikhoan(soDienThoai,this);
            // Ví dụ: Lấy thông tin từ cơ sở dữ liệu và hiển thị lên EditText
            // Bạn cần thêm các phương thức để lấy tên, số điện thoại và Gmail từ cơ sở dữ liệu
            editTextTen.setText(kq.get(0));
            editTextSdt.setText(kq.get(1));
            mkcu = kq.get(2);

            editTextTen.setText("Tên khách hàng");
            editTextSdt.setText(soDienThoai);
            editTextGmail.setText("example@gmail.com");

        } else {
            Toast.makeText(this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }


    private void updatetttk() {
        String ten = editTextTen.getText().toString();
        String sdt = editTextSdt.getText().toString();
        taiKhoanSQL.updatetttk(sdt,ten,this);
        finish();
    }
    private void doimk()
    {
        if(!DinhDang.isDinhDangMatKhau(edtmkmoi.getText().toString()))
        {
            edtmkmoi.setError("Sai dinh dang mat khau");
        }
        else{
            if(mkcu.compareTo(edtmkcu.getText().toString())==0)
            {
                    taiKhoanSQL.sp_update_mkTaiKhoan(soDienThoai,edtmkmoi.getText().toString(),this);
                    finish();
            }
            else {
                Toast.makeText(this,"Mat khau cu khong dung",Toast.LENGTH_SHORT).show();
            }

            }
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

