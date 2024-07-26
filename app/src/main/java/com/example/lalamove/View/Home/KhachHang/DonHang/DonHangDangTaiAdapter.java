package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.Home.TaiXe.DonHang.ChiTietDonHangActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DonHangDangTaiAdapter extends ArrayAdapter<DonHang> {
    private int selectedPosition = -1;
    private Context context;
    public onXemChiTietClickListener onXemChiTietClick;

    public DonHangDangTaiAdapter(@NonNull Context context, int resource, List<DonHang> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    public void setOnNhanDonClickListener(onXemChiTietClickListener listener) {
        this.onXemChiTietClick = listener;
    }
    public interface onXemChiTietClickListener {

        void onXemChiTietClick(DonHang donHang);
    }
    public int getSelectedPosition() {
        return selectedPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DonHang donHang = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_donhang, parent, false);
        }

        // Anh xa
        TextView tv_thu_ListDonHang = convertView.findViewById(R.id.tv_thu_ListDonHang);
        TextView tv_thang_ngay_ListDonHang = convertView.findViewById(R.id.tv_thang_ngay_ListDonHang);
        TextView tv_ThoiGianDatHang_ListDonHang = convertView.findViewById(R.id.tv_ThoiGianDatHang_ListDonHang);
        TextView tv_DiemDen_ListDonHang = convertView.findViewById(R.id.tv_DiemDen_ListDonHang);
        TextView tv_DiemGiao_ListDonHang = convertView.findViewById(R.id.tv_DiemGiao_ListDonHang);
        TextView tv_TenLoaiPhuongTien_ListDonHang = convertView.findViewById(R.id.tv_TenLoaiPhuongTien_ListDonHang);
        TextView tv_GiaTien_ListDonHang = convertView.findViewById(R.id.tv_GiaTien_ListDonHang);
        TextView tv_trangthai_Donhang_KhachHang = convertView.findViewById(R.id.tv_trangthai_Donhang_KhachHang);
        LinearLayout ln_item = convertView.findViewById(R.id.ln_item);


        if (donHang != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
            tv_thu_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tv_thang_ngay_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            tv_ThoiGianDatHang_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));
            tv_trangthai_Donhang_KhachHang.setText(donHang.getTrangthaidonhang());
            tv_DiemDen_ListDonHang.setText(donHang.getNoiNhan());
            tv_DiemGiao_ListDonHang.setText(donHang.getNoiGiao());
            tv_TenLoaiPhuongTien_ListDonHang.setText(donHang.getTenphuongtien());
            tv_GiaTien_ListDonHang.setText(String.format(Locale.getDefault(), "%,d đ", donHang.getGiaTien()));
        }
        ln_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onXemChiTietClick != null && donHang != null) {
                    onXemChiTietClick.onXemChiTietClick(donHang);
                    if (donHang.getTrangthaidonhang().equals("Chờ nhận hàng")) {

                    }else{
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

                        // Start new activity
                        Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                        context.startActivity(intent);
                    }

                }
            }
        });



        return convertView;
    }
}