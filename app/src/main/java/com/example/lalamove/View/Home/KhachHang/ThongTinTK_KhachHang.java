package com.example.lalamove.View.Home.KhachHang;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;

public class ThongTinTK_KhachHang extends AppCompatActivity {

    private EditText editTextTen, editTextSdt, editTextGmail;
    private TaiKhoanSQL taiKhoanSQL;
    private String soDienThoai; // Biến để lưu số điện thoại hoặc ID tài khoản, cần lấy từ Intent hoặc SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitietkhachhang);

        // Khởi tạo các view
        editTextTen = findViewById(R.id.Ten);
        editTextSdt = findViewById(R.id.sdt);
        editTextGmail = findViewById(R.id.Gmail_1);
        ImageButton btnBack = findViewById(R.id.btn_back_hosokh);
        ImageView insertName = findViewById(R.id.insert_name);
        ImageView insertSdt = findViewById(R.id.insert_sdt);
        ImageView insertGmail = findViewById(R.id.insert_gmail);

        taiKhoanSQL = new TaiKhoanSQL();

        // Lấy thông tin tài khoản từ cơ sở dữ liệu
        loadThongTinTaiKhoan();

        // Xử lý sự kiện nút back
        btnBack.setOnClickListener(view -> finish());

        // Xử lý sự kiện cập nhật tên
        insertName.setOnClickListener(view -> updateTenTaiKhoan());

        // Xử lý sự kiện cập nhật số điện thoại
        insertSdt.setOnClickListener(view -> updateSdtTaiKhoan());

        // Xử lý sự kiện cập nhật Gmail
        insertGmail.setOnClickListener(view -> updateGmailTaiKhoan());
    }

    private void loadThongTinTaiKhoan() {
        // Đây là ví dụ, bạn cần thay thế giá trị của soDienThoai bằng giá trị thực tế
        soDienThoai = ""; // Hoặc lấy từ Intent hoặc SharedPreferences

        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(soDienThoai, this);

        // Nếu tài khoản tồn tại, lấy thông tin và hiển thị lên giao diện
        if (!loaiTaiKhoan.isEmpty()) {
            // Ví dụ: Lấy thông tin từ cơ sở dữ liệu và hiển thị lên EditText
            // Bạn cần thêm các phương thức để lấy tên, số điện thoại và Gmail từ cơ sở dữ liệu
            editTextTen.setText("Tên khách hàng");
            editTextSdt.setText(soDienThoai);
            editTextGmail.setText("example@gmail.com");
        } else {
            Toast.makeText(this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTenTaiKhoan() {
        // Thực hiện cập nhật tên tài khoản
        String tenMoi = editTextTen.getText().toString();
        // Gọi phương thức cập nhật trong TaiKhoanSQL
        // Ví dụ: taiKhoanSQL.updateTenTaiKhoan(soDienThoai, tenMoi, this);
    }

    private void updateSdtTaiKhoan() {
        // Thực hiện cập nhật số điện thoại
        String sdtMoi = editTextSdt.getText().toString();
        // Gọi phương thức cập nhật trong TaiKhoanSQL
        // Ví dụ: taiKhoanSQL.updateSdtTaiKhoan(soDienThoai, sdtMoi, this);
    }

    private void updateGmailTaiKhoan() {
        // Thực hiện cập nhật Gmail
        String gmailMoi = editTextGmail.getText().toString();
        // Gọi phương thức cập nhật trong TaiKhoanSQL
        // Ví dụ: taiKhoanSQL.updateGmailTaiKhoan(soDienThoai, gmailMoi, this);
    }
}
