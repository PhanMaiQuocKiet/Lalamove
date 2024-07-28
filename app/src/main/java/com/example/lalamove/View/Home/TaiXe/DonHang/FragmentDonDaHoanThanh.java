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
import com.example.lalamove.View.Home.KhachHang.DonHang.DonHangDangTaiAdapter;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.util.List;

public class FragmentDonDaHoanThanh extends Fragment implements DonHangAdapter.OnNhanDonClickListener{
    LinearLayout ln_hienthi_HoanThanh_TaiXe;
    ListView lv_DonHang_HoanThanh_TaiXe;
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_donhoanthanh_taixe, container, false);
        // Lấy dữ liệu từ share
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String sodienthoai = sharedPreferences.getString("sodienthoai", "");
        AnhXa(v, sodienthoai);
        return v;
    }

    void AnhXa(View v, String soDienThoai) {
        ln_hienthi_HoanThanh_TaiXe = v.findViewById(R.id.ln_hienthi_HoanThanh_TaiXe);
        lv_DonHang_HoanThanh_TaiXe = v.findViewById(R.id.lv_DonHang_HoanThanh_TaiXe);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataHoanThanhTaiXe(requireContext(), lv_DonHang_HoanThanh_TaiXe, this::setAdapter, soDienThoai, "Hoàn thành");
    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity() != null) {
            DonHangAdapter adapter = new DonHangAdapter(requireContext(), R.layout.layout_list_donhang_taixe, listDonHang);
            adapter.setOnNhanDonClickListener(this);
            lv_DonHang_HoanThanh_TaiXe.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged(); // Thêm dòng này
            if (size <= 0) {
                lv_DonHang_HoanThanh_TaiXe.setVisibility(View.GONE);
                ln_hienthi_HoanThanh_TaiXe.setVisibility(View.VISIBLE);
            } else {
                lv_DonHang_HoanThanh_TaiXe.setVisibility(View.VISIBLE);
                ln_hienthi_HoanThanh_TaiXe.setVisibility(View.GONE);
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

