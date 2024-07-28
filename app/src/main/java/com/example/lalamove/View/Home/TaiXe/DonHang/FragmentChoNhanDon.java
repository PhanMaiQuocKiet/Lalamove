package com.example.lalamove.View.Home.TaiXe.DonHang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableChiTietDonGiao.ChiTiet_QuerySql;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;
import com.example.lalamove.View.model.TableTaiKhoanTaiXe.TaiXe_QuerySql;

import java.util.Date;
import java.util.List;

public class FragmentChoNhanDon extends Fragment implements DonHangAdapter.OnNhanDonClickListener {
    LinearLayout ln_hienthi_ChoNhanDon;
    ListView lv_DonHang_ChoNhanDon;
    private String trangthai = "Chờ nhận hàng";
    int size = 0;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chonhandon_taixe, container, false);

        //Lấy dữ liệu từ share
        sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String sodienthoai = sharedPreferences.getString("sodienthoai","");
        TaiXe_QuerySql taiXeQuerySql = new TaiXe_QuerySql();
        String maPhuongTien = taiXeQuerySql.getMaPhuongTien(sodienthoai, requireContext());

        //Ánh Xạ
        AnhXa(v, maPhuongTien, sodienthoai);


        return v;
    }

    void AnhXa(View v, String maPhuongTien, String sodienthoaitaixe) {
        ln_hienthi_ChoNhanDon = v.findViewById(R.id.ln_hienthi_ChoNhanDon);
        lv_DonHang_ChoNhanDon = v.findViewById(R.id.lv_DonHang_ChoNhanDon);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDonHangChuaNhanDon(getActivity(), lv_DonHang_ChoNhanDon, this::setAdapter, maPhuongTien, sodienthoaitaixe, trangthai, "Đang giao");
    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity() != null) {
            DonHangAdapter adapter = new DonHangAdapter(getActivity(), R.layout.layout_list_donhang_taixe, listDonHang);
            adapter.setOnNhanDonClickListener(this); // Thiết lập listener cho adapter
            lv_DonHang_ChoNhanDon.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged();
            if (size <= 0) {
                lv_DonHang_ChoNhanDon.setVisibility(View.GONE);
                ln_hienthi_ChoNhanDon.setVisibility(View.VISIBLE);
            } else {
                lv_DonHang_ChoNhanDon.setVisibility(View.VISIBLE);
                ln_hienthi_ChoNhanDon.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNhanDonClick(DonHang donHang) {
        // Thực hiện cập nhật lên database
        CapNhatChiTietDonHang(donHang);

    }
    @Override
    public void onXemChiTietClick(DonHang donHang){

    }

    private void CapNhatChiTietDonHang(DonHang donHang) {
        sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String sodienthoaitaixe = sharedPreferences.getString("sodienthoai", "");
        // Lấy thời gian hiện tại
        Date thoigiannhanhang = new Date();

        ChiTiet_QuerySql chiTietQuerySql = new ChiTiet_QuerySql(requireContext());
        chiTietQuerySql.CapNhatChiTietDonGiaoHang(sodienthoaitaixe, thoigiannhanhang, donHang.getMaDonHang());
        reloadData();
    }
    private void reloadData() {
        String sodienthoai = sharedPreferences.getString("sodienthoai", "");
        TaiXe_QuerySql taiXeQuerySql = new TaiXe_QuerySql();
        String maPhuongTien = taiXeQuerySql.getMaPhuongTien(sodienthoai, requireContext());

        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDonHangChuaNhanDon(getActivity(), lv_DonHang_ChoNhanDon, this::setAdapter, maPhuongTien, sodienthoai, trangthai, "Đang giao");
    }
    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }
}
