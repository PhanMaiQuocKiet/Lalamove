package com.example.lalamove.View.Home.TaiXe.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.TaiXe.TrangChuTaiXeActivity;
import com.example.lalamove.View.model.TableChiTietDonGiao.ChiTiet_QuerySql;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.TableTaiKhoanTaiXe.TaiXe_QuerySql;
import com.example.lalamove.View.model.TableTaiXeYeuThich.QuerySql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChiTietDonHangActivity extends AppCompatActivity {

    // TextView variables
    TextView tv_ChiTietDonHang_SoSaoDanhGia, tv_ChiTietDonHang_ThoiGian_Thu, tv_ChiTietDonHang_ThoiGian_Gio,
            tv_ChiTietDonHang_ThoiGian_Thang, tv_ChiTietDonHang_SoDienThoaiNguoiGui, tv_ChiTietDonHang_ChiTietDonHang,
            tv_ChiTietDonHang_MaDonHang, tv_ChiTietDonHang_SoDienThoaiTaiXe, tv_ChiTietDonHang_tongtien,
            tv_ChiTietDonHang_TenTaiXe, tv_ChiTietDonHang_TenPhuongTien, tv_ChiTietDonHang_DiemGiao, tv_ChiTietDonHang_DiemNhan,
            tv_ChiTietDonHang_TrangThai,tv_ChiTietDonHang_SoDienThoaiNguoiNhan,tv_ChiTietDonHang_DanhGiaTaiXe;

    AppCompatButton btn_huy_chitietdonhang_taixe,btn_hoanthanh_chitietdonhang_taixe,btn_DatLai_chitietdonhang_taixe;
    ImageView imgv_ChiTietDonHang_Back;


    SharedPreferences sharedPreferences,sharedPreferences2, sharedPreferences3;
    SharedPreferences.Editor editor2,editor3;
    String trangThaiHuy = "Hủy";
    String trangThaiHoanThanh = "Hoàn thành";
    Context context = ChiTietDonHangActivity.this;
    int maDonHang = 0;
    String sodienthoaitaixe ="";
    String soDienThoaiKhachHang = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietdonhang);

        // AnhXa
        AnhXa();

        // Lay Share
        LayDuLieuTuShare();

        // Set Dữ liệu
        SetDuLieu();

        //phân role
        soDienThoaiKhachHang = sharedPreferences.getString("sodienthoai","");
        TaiKhoanSQL taiKhoanSQL = new TaiKhoanSQL();
        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(sharedPreferences.getString("sodienthoai",""), ChiTietDonHangActivity.this);
        if(loaiTaiKhoan.equals("KhachHang")){
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.GONE);
        }


        //Xóa khi bấm back
        imgv_ChiTietDonHang_Back.setOnClickListener(v -> XoaDuLieu());
        //Nhận và xử lý button
        XuLyHienThi();

        //Xử lý btn đặt lại
        btn_DatLai_chitietdonhang_taixe.setOnClickListener(v -> VeTrangChu(context));

        //Cập nhật trạng thái khi bấm Hủy đơn
        btn_huy_chitietdonhang_taixe.setOnClickListener(v -> CapNhatTrangThai(trangThaiHuy,context));

        //Cập nhật trạng thái khi bấm hoàn thành đơn
        btn_hoanthanh_chitietdonhang_taixe.setOnClickListener(v -> CapNhatTrangThai(trangThaiHoanThanh,context));

        //Xử lý nhấn vào tv đánh giá tài xế
        tv_ChiTietDonHang_DanhGiaTaiXe.setOnClickListener(v -> XuLyDanhGiaTaiXe(context));
    }
    private void XuLyDanhGiaTaiXe(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_danh_gia, null);
        builder.setView(dialogView);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);

        builder.setTitle("Đánh giá tài xế");
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            int rating = (int) ratingBar.getRating();
            // Xử lý đánh giá ở đây
            QuerySql querySql = new QuerySql();
            querySql.sp_insert_DanhGiaTaiXe(soDienThoaiKhachHang,sodienthoaitaixe,rating,context);
        });
        builder.setNegativeButton("Quay lại", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void XuLyHienThi(){
        String trangthai = getIntent().getStringExtra("trangthai");
        if(trangthai.equals("Hoàn thànhkh") || trangthai.equals("Hủykh")){
            btn_DatLai_chitietdonhang_taixe.setVisibility(View.VISIBLE);
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.GONE);
            btn_huy_chitietdonhang_taixe.setVisibility(View.GONE);
        } else if (trangthai.equals("Đang giaokh") || trangthai.equals("Chờ nhận hàngkh")) {
            btn_DatLai_chitietdonhang_taixe.setVisibility(View.GONE);
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.GONE);
            btn_huy_chitietdonhang_taixe.setVisibility(View.VISIBLE);
        } else if(trangthai.equals("Hoàn thànhtx") || trangthai.equals("Hủytx")){
            btn_DatLai_chitietdonhang_taixe.setVisibility(View.VISIBLE);
            btn_DatLai_chitietdonhang_taixe.setText("Về trang chủ");
            tv_ChiTietDonHang_DanhGiaTaiXe.setVisibility(View.GONE);
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.GONE);
            btn_huy_chitietdonhang_taixe.setVisibility(View.GONE);
        }else{
            tv_ChiTietDonHang_DanhGiaTaiXe.setVisibility(View.GONE);
            btn_DatLai_chitietdonhang_taixe.setVisibility(View.GONE);
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.VISIBLE);
            btn_huy_chitietdonhang_taixe.setVisibility(View.VISIBLE);
        }
    }
    private void VeTrangChu(Context context){
        Intent i = new Intent(context, TrangChuTaiXeActivity.class);
        startActivity(i);
    }
    private void CapNhatTrangThai(String trangThai,Context context){
        QuerySql_DonDatGiaoHang querySqlDonDatGiaoHang = new QuerySql_DonDatGiaoHang();
        Toast.makeText(context, String.valueOf(maDonHang), Toast.LENGTH_SHORT).show();
        if(querySqlDonDatGiaoHang.updateTrangThaiDonHang(context,trangThai,maDonHang)){
            Intent i = new Intent(ChiTietDonHangActivity.this,DonHangActivity.class);
            startActivity(i);
        }


    }
    private void LayDuLieuTuShare() {
        sharedPreferences = getSharedPreferences("ThongTinDangNhap",Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("ChiTietDonHang", Context.MODE_PRIVATE);
        sharedPreferences3 = getSharedPreferences("ChiTietTaiXe", Context.MODE_PRIVATE);

    }
    private void SetDuLieu(){
        String tenPhuongtien = sharedPreferences2.getString("tenphuongtien", "");
        String noiNhan = sharedPreferences2.getString("diachinhandon", "");
        int giaTien = sharedPreferences2.getInt("tongtien", 0);
        maDonHang = sharedPreferences2.getInt("madonhang", 0);
        sodienthoaitaixe = sharedPreferences2.getString("sodienthoaitaixe", "");
        String noiGiao = sharedPreferences2.getString("diachigiao", "");
        String trangThai = sharedPreferences2.getString("trangthai", "");
        String sdtNguoiNhan = sharedPreferences2.getString("sodienthoainguoinhan","");
        String sdtNguoiGui = sharedPreferences2.getString("sodienthoainguoigui","");
        Toast.makeText(context,sodienthoaitaixe,Toast.LENGTH_SHORT).show();
        ChiTiet_QuerySql chiTietQuerySql = new ChiTiet_QuerySql(this);
        Timestamp thoiGianNhanHang = chiTietQuerySql.getThoiGianNhanDon(sodienthoaitaixe, maDonHang);

        DinhDangThoiGian(thoiGianNhanHang);

        TaiXe_QuerySql taiXeQuerySql = new TaiXe_QuerySql();
        taiXeQuerySql.getTenTaiXe_SoSao(sodienthoaitaixe, this);

        String tenTaiXe = sharedPreferences3.getString("tentaixe", "");
        int diemDanhGia = sharedPreferences3.getInt("diemdanhgia", 0);

        tv_ChiTietDonHang_SoDienThoaiNguoiGui.setText(sdtNguoiGui);
        tv_ChiTietDonHang_SoDienThoaiNguoiNhan.setText(sdtNguoiNhan);
        tv_ChiTietDonHang_SoDienThoaiTaiXe.setText(sodienthoaitaixe);
        tv_ChiTietDonHang_MaDonHang.setText(String.valueOf(maDonHang));
        tv_ChiTietDonHang_TrangThai.setText(trangThai);
        tv_ChiTietDonHang_TenTaiXe.setText(tenTaiXe);
        tv_ChiTietDonHang_SoSaoDanhGia.setText(String.format(Locale.getDefault(), "%d.0", diemDanhGia));
        tv_ChiTietDonHang_TenPhuongTien.setText(tenPhuongtien);
        tv_ChiTietDonHang_DiemNhan.setText(noiNhan);

        tv_ChiTietDonHang_DiemGiao.setText(noiGiao);
        tv_ChiTietDonHang_tongtien.setText(String.format(Locale.getDefault(), "%,d đ", giaTien));
    }
    private void DinhDangThoiGian(Timestamp thoiGianNhanHang){
        if (thoiGianNhanHang != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
            String formattedDayOfWeek = dateFormat.format(thoiGianNhanHang);

            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(thoiGianNhanHang);

            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String formattedTime = dateFormat.format(thoiGianNhanHang);

            tv_ChiTietDonHang_ThoiGian_Thu.setText(formattedDayOfWeek);
            tv_ChiTietDonHang_ThoiGian_Thang.setText(formattedDate);
            tv_ChiTietDonHang_ThoiGian_Gio.setText(formattedTime);
        } else {
            tv_ChiTietDonHang_ThoiGian_Thu.setText("N/A");
            tv_ChiTietDonHang_ThoiGian_Thang.setText("N/A");
            tv_ChiTietDonHang_ThoiGian_Gio.setText("N/A");
        }
    }
    private void XoaDuLieu(){
        editor2 = sharedPreferences2.edit();
        editor3 = sharedPreferences3.edit();

        editor2.clear();
        editor3.clear();

        editor2.apply();
        editor3.apply();

        finish();
    }


    void AnhXa() {
        tv_ChiTietDonHang_DanhGiaTaiXe= findViewById(R.id.tv_ChiTietDonHang_DanhGiaTaiXe);
        btn_DatLai_chitietdonhang_taixe = findViewById(R.id.btn_DatLai_chitietdonhang_taixe);
        tv_ChiTietDonHang_SoSaoDanhGia = findViewById(R.id.tv_ChiTietDonHang_SoSaoDanhGia);
        tv_ChiTietDonHang_ThoiGian_Thu = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Thu);
        tv_ChiTietDonHang_TrangThai = findViewById(R.id.tv_ChiTietDonHang_TrangThai);
        tv_ChiTietDonHang_DiemGiao = findViewById(R.id.tv_ChiTietDonHang_DiemGiao);
        tv_ChiTietDonHang_DiemNhan = findViewById(R.id.tv_ChiTietDonHang_DiemNhan);
        tv_ChiTietDonHang_TenPhuongTien = findViewById(R.id.tv_ChiTietDonHang_TenPhuongTien);
        tv_ChiTietDonHang_TenTaiXe = findViewById(R.id.tv_ChiTietDonHang_TenTaiXe);
        tv_ChiTietDonHang_SoSaoDanhGia = findViewById(R.id.tv_ChiTietDonHang_SoSaoDanhGia);
        tv_ChiTietDonHang_ThoiGian_Thu = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Thu);
        tv_ChiTietDonHang_ThoiGian_Thang = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Thang);
        tv_ChiTietDonHang_ThoiGian_Gio = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Gio);
        tv_ChiTietDonHang_SoDienThoaiNguoiGui = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiNguoiGui);
        tv_ChiTietDonHang_SoDienThoaiNguoiNhan = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiNguoiNhan);
        tv_ChiTietDonHang_MaDonHang = findViewById(R.id.tv_ChiTietDonHang_MaDonHang);
        tv_ChiTietDonHang_SoDienThoaiTaiXe = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiTaiXe);
        tv_ChiTietDonHang_tongtien = findViewById(R.id.tv_ChiTietDonHang_tongtien);
        btn_huy_chitietdonhang_taixe = findViewById(R.id.btn_huy_chitietdonhang_taixe);
        btn_hoanthanh_chitietdonhang_taixe = findViewById(R.id.btn_hoanthanh_chitietdonhang_taixe);
        imgv_ChiTietDonHang_Back = findViewById(R.id.imgv_ChiTietDonHang_Back);
    }


}
