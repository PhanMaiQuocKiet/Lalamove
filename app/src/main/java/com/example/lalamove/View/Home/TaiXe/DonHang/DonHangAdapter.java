package com.example.lalamove.View.Home.TaiXe.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DonHangAdapter extends ArrayAdapter<DonHang> {
    private Context context;
    public OnNhanDonClickListener nhanDonClickListener;
    public DonHangAdapter(@NonNull Context context, int resource, List<DonHang> objects) {
        super(context, resource,objects);
        this.context = context;
    }
    public void setOnNhanDonClickListener(OnNhanDonClickListener listener) {
        this.nhanDonClickListener = listener;
    }
    public interface OnNhanDonClickListener {
        void onNhanDonClick(DonHang donHang);
        void onXemChiTietClick(DonHang donHang);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DonHang donHang = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_donhang_taixe, parent, false);
        }

        // Anh xa
        TextView tv_thu_ListDonHang = convertView.findViewById(R.id.tv_thu_ListDonHang);
        TextView tv_thang_ngay_ListDonHang = convertView.findViewById(R.id.tv_thang_ngay_ListDonHang);
        TextView tv_ThoiGianDatHang_ListDonHang = convertView.findViewById(R.id.tv_ThoiGianDatHang_ListDonHang);
        TextView tv_DiemDen_ListDonHang = convertView.findViewById(R.id.tv_DiemDen_ListDonHang);
        TextView tv_DiemGiao_ListDonHang = convertView.findViewById(R.id.tv_DiemGiao_ListDonHang);
        TextView tv_TenLoaiPhuongTien_ListDonHang = convertView.findViewById(R.id.tv_TenLoaiPhuongTien_ListDonHang);
        TextView tv_GiaTien_ListDonHang = convertView.findViewById(R.id.tv_GiaTien_ListDonHang);
        AppCompatButton btn_NhanDon = convertView.findViewById(R.id.btn_nhandon);
        AppCompatButton btn_XemChiTiet = convertView.findViewById(R.id.btn_xemchitiet);

        if (donHang != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
            tv_thu_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tv_thang_ngay_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            tv_ThoiGianDatHang_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            tv_DiemDen_ListDonHang.setText(donHang.getNoiNhan());
            tv_DiemGiao_ListDonHang.setText(donHang.getNoiGiao());
            tv_TenLoaiPhuongTien_ListDonHang.setText(donHang.getTenphuongtien());
            tv_GiaTien_ListDonHang.setText(String.format(Locale.getDefault(), "%,d đ", donHang.getGiaTien()));

            // Get the status of the order
            QuerySql_DonDatGiaoHang querySqlDonDatGiaoHang = new QuerySql_DonDatGiaoHang();
            String trangThai = querySqlDonDatGiaoHang.getTrangThai(context,donHang.getMaDonHang());
            // Show/Hide buttons based on order status
            if ("Chờ nhận hàng".equals(trangThai)){
                btn_NhanDon.setVisibility(View.VISIBLE);
                btn_XemChiTiet.setVisibility(View.GONE);
            } else {
                btn_NhanDon.setVisibility(View.GONE);
                btn_XemChiTiet.setVisibility(View.VISIBLE);
            }

            btn_NhanDon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nhanDonClickListener != null && donHang != null) {
                        nhanDonClickListener.onNhanDonClick(donHang);
                    }
                }
            });

            btn_XemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nhanDonClickListener != null) {
                        nhanDonClickListener.onXemChiTietClick(donHang);
                        LuuChiTietDonhang(donHang);
                        Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                        intent.putExtra("trangthai",donHang.getTrangthaidonhang()+"tx");
                        context.startActivity(intent);
                    }
                }
            });

            convertView.setOnClickListener(v -> {
                SharedPreferences sharedPreferences = context.getSharedPreferences("DonHangChiTiet", Context.MODE_PRIVATE);
            });
        }

        return convertView;
    }
    private void LuuChiTietDonhang(DonHang donHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ChiTietDonHang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sodienthoaitaixe",donHang.getSoDienThoaiTaiXe());
        editor.putString("trangthai", donHang.getTrangthaidonhang());
        editor.putString("tenphuongtien", donHang.getTenphuongtien());
        editor.putString("diachinhandon", donHang.getNoiNhan());
        editor.putString("sodienthoainguoigui", donHang.getSoDienThoaiNguoiGui());
        editor.putString("diachigiao", donHang.getNoiGiao());
        editor.putString("sodienthoainguoinhan", donHang.getSoDienThoaiNguoiNhan());
        editor.putInt("madonhang", donHang.getMaDonHang());
        editor.putInt("tongtien", donHang.getGiaTien());

        editor.apply();
    }


}
