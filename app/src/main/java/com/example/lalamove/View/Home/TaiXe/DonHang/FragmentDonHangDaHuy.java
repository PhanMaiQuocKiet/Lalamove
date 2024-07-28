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
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.util.List;

public class FragmentDonHangDaHuy extends Fragment implements DonHangAdapter.OnNhanDonClickListener{
    ListView lv_DonHang_DaHuy_TaiXe;
    LinearLayout ln_hienthi_DaHuy_TaiXe;
    int size = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dondahuy_taixe, container, false);
        // Lấy dữ liệu từ share
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String sodienthoai = sharedPreferences.getString("sodienthoai", "");

        //Anh Xa
        AnhXa(v,sodienthoai);

        return v;
    }
    void AnhXa(View v, String soDienThoai) {
        ln_hienthi_DaHuy_TaiXe = v.findViewById(R.id.ln_hienthi_DaHuy_TaiXe);
        lv_DonHang_DaHuy_TaiXe = v.findViewById(R.id.lv_DonHang_DaHuy_TaiXe);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataHoanThanhTaiXe(requireContext(), lv_DonHang_DaHuy_TaiXe, this::setAdapter, soDienThoai, "Hủy");
    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity() != null) {
            DonHangAdapter adapter = new DonHangAdapter(requireContext(), R.layout.layout_list_donhang_taixe, listDonHang);
            adapter.setOnNhanDonClickListener(this);
            lv_DonHang_DaHuy_TaiXe.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged(); // Thêm dòng này
            if (size <= 0) {
                lv_DonHang_DaHuy_TaiXe.setVisibility(View.GONE);
                ln_hienthi_DaHuy_TaiXe.setVisibility(View.VISIBLE);
            } else {
                lv_DonHang_DaHuy_TaiXe.setVisibility(View.VISIBLE);
                ln_hienthi_DaHuy_TaiXe.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void onXemChiTietClick(DonHang donHang){

    }
    @Override
    public void onNhanDonClick(DonHang donHang) {
    }

}
