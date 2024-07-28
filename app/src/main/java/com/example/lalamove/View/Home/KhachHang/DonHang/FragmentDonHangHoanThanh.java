package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.util.List;

public class FragmentDonHangHoanThanh extends Fragment implements DonHangDangTaiAdapter.onXemChiTietClickListener{
    private ListView lv_DonHang_HoanThanh;
    private LinearLayout ln_hienthi_HoanThanh;
    int size = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v =inflater.inflate(R.layout.fragment_da_hoan_thanh, container, false);
        //Anh Xa
        // Lấy dữ liệu từ SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String soDienThoai = sharedPreferences.getString("sodienthoai", "");
        AnhXa(v,soDienThoai);
       return  v;
    }

    void AnhXa(View v ,String soDienThoai){
        ln_hienthi_HoanThanh = v.findViewById(R.id.ln_hienthi_DaHoanThanh);
        lv_DonHang_HoanThanh = v.findViewById(R.id.lv_DonHang_DaHoanThanh);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataAllHoanThanh(requireContext(), lv_DonHang_HoanThanh, this::setAdapter, soDienThoai,"Hoàn thành");
    }


    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity() != null) {
            DonHangDangTaiAdapter adapter = new DonHangDangTaiAdapter(getActivity(), R.layout.layout_list_donhang, listDonHang);
            adapter.setOnNhanDonClickListener(this);
            lv_DonHang_HoanThanh.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged(); // Thêm dòng này
            if(size <= 0){
                lv_DonHang_HoanThanh.setVisibility(View.GONE);
                ln_hienthi_HoanThanh.setVisibility(View.VISIBLE);
            }else{
                lv_DonHang_HoanThanh.setVisibility(View.VISIBLE);
                ln_hienthi_HoanThanh.setVisibility(View.GONE);
            }
        }

    }
    @Override
    public void onXemChiTietClick(DonHang donHang) {

    }

}
