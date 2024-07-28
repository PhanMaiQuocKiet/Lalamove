package com.example.lalamove.View.Home.KhachHang;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalamove.View.Home.KhachHang.DonHang.DonHangActivity;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;
import com.example.lalamove.View.model.TableTaiXeYeuThich.QuerySql;


public class BuocTiepTheoActivity extends AppCompatActivity {
    RelativeLayout rlt_ThemChiTietHangHoa , rlt_GhiChu;
    ImageButton  btnBack;
    Button btn_buoctieptheo;
    TextView tv_BuocTiepTheo_tongtien,tv_ChiTietHangHoa,tv_GhiChuChoTaiXe;
    EditText edt_SoDienThoaiNguoiGui,edt_SoDienThoaiNguoiNhan;
    Switch favorite_switch;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dat_hang_buoc_tiep_theo);
        //Anh Xa
        AnhXa();
        //lấy dữ liệu trong luong đặt hàng
        SharedPreferences sharedPreferences2 = this.getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        String soDienThoaiKhachHang = sharedPreferences2.getString("sodienthoai","");
        SharedPreferences sharedPreferences = this.getSharedPreferences("LuongDatHang",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor  editor= sharedPreferences.edit();

        int tongTien = sharedPreferences.getInt("tongtien",0);
        String tenLoaiHangVanChuyen = sharedPreferences.getString("tenloaihangvanchuyen","");
        String ghiChu = sharedPreferences.getString("ghichuchotaixe","");
        String trangThai = "Chờ nhận hàng";
        String maLoaiHang = sharedPreferences.getString("maloaihangvanchuyen","");
        String noiNhan = sharedPreferences.getString("noinhan","");
        String noiGiao  = sharedPreferences.getString("noigiao","");
        String maPhuongTien = sharedPreferences.getString("maphuongtien","");
        int soLuongThungHang = sharedPreferences.getInt("soluongthunghang",0);
        java.util.Date thoiGianDatHang = new java.util.Date();

        tv_ChiTietHangHoa.setText(tenLoaiHangVanChuyen);
        tv_ChiTietHangHoa.setTextSize(19);
        tv_BuocTiepTheo_tongtien.setText(String.valueOf(tongTien) +" đ");
        tv_GhiChuChoTaiXe.setText(ghiChu);
        tv_GhiChuChoTaiXe.setTextSize(15);
        // Su kien Click
        rlt_ThemChiTietHangHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuocTiepTheoActivity.this,BoSungChiTietActvity.class);
                startActivity(i);
            }
        });
        rlt_GhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuocTiepTheoActivity.this, GhiChuChoTaiXeActivity.class);
                startActivity(i);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        favorite_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuerySql querySql = new QuerySql();
                if(!querySql.isTaiXeYeuThich(soDienThoaiKhachHang,BuocTiepTheoActivity.this)){
                    Toast.makeText(BuocTiepTheoActivity.this,"Bạn chưa có tài xế yêu thích nào ",Toast.LENGTH_SHORT).show();
                    favorite_switch.setChecked(false);
                }
            }
        });
        btn_buoctieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdtNguoiGui = edt_SoDienThoaiNguoiGui.getText().toString();
                String sdtNguoiNhan = edt_SoDienThoaiNguoiNhan.getText().toString();
                if(sdtNguoiGui.isEmpty()){
                    edt_SoDienThoaiNguoiGui.setError("Vui Lòng nhập số điện thoại Người gửi");
                }else if(sdtNguoiNhan.isEmpty()){
                    edt_SoDienThoaiNguoiNhan.setError("Vui Lòng nhập số điện thoại người gửi");
                }else{
                    if(!isDinhDangSoDienThoai(sdtNguoiNhan)){
                        edt_SoDienThoaiNguoiNhan.setError("Định dạng số điện thoại không đúng");
                    } else if (!isDinhDangSoDienThoai(sdtNguoiGui)) {
                        edt_SoDienThoaiNguoiNhan.setError("Định dạng số điện thoại không đúng");
                    } else if (sdtNguoiNhan.equals(sdtNguoiGui)) {
                        edt_SoDienThoaiNguoiNhan.setError("Số điện thoại không được trùng");
                        edt_SoDienThoaiNguoiNhan.setError("Số điện thoại không được trùng");
                    } else{
                        //Thêm dữ liệu đơn hàng vào database
                        QuerySql_DonDatGiaoHang querySqlDonDatGiaoHang = new QuerySql_DonDatGiaoHang();
                        querySqlDonDatGiaoHang.sp_insert_DonDatGiaoHang_ChiTietDon(soDienThoaiKhachHang,sdtNguoiGui,noiNhan,sdtNguoiNhan,noiGiao,thoiGianDatHang,maPhuongTien,ghiChu,soLuongThungHang,maLoaiHang,trangThai,tongTien,BuocTiepTheoActivity.this);

                        //Xóa dữ liệu lưu trữ trong Share Prefer
                        editor.clear();
                        editor.apply();

                        //Di chuyển tới trang đơn hàng
                        Intent i = new Intent(BuocTiepTheoActivity.this, Home_KhachHang.class);
                        startActivity(i);
                    }
                }

            }
        });
    }

    void AnhXa(){
        rlt_ThemChiTietHangHoa = findViewById(R.id.rlt_ThemChiTietHangHoa);
        rlt_GhiChu = findViewById(R.id.rlt_GhiChu);
        btnBack = findViewById(R.id.btn_back_bsct);
        btn_buoctieptheo = findViewById(R.id.btn_buoctieptheo);
        edt_SoDienThoaiNguoiGui = findViewById(R.id.edt_SoDienThoaiNguoiGui);
        edt_SoDienThoaiNguoiNhan = findViewById(R.id.edt_SoDienThoaiNguoiNhan);
        tv_BuocTiepTheo_tongtien = findViewById(R.id.tv_BuocTiepTheo_tongtien);
        tv_ChiTietHangHoa= findViewById(R.id.tv_ChiTietHangHoa);
        tv_GhiChuChoTaiXe =findViewById(R.id.tv_GhiChuChoTaiXe);
        favorite_switch = findViewById(R.id.favorite_switch);
    }
    public boolean isDinhDangSoDienThoai(String phoneNumber) {
        // Số điện thoại phải có 10 chữ số
        String regex = "^[0-9]{10}$";
        return phoneNumber.matches(regex);
    }
}
