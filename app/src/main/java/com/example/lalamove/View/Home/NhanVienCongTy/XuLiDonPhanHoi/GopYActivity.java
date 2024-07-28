package com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lalamove.R;
import com.example.lalamove.database.data.ConnectionHelper;

import java.util.List;

public class GopYActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xu_li_don_gop_y, container, false);

        // Kiểm tra kết nối cơ sở dữ liệu
        ConnectionHelper connectionHelper = new ConnectionHelper();


        // Kiểm tra truy vấn cơ sở dữ liệu
        FeedbackOrderQuery query = new FeedbackOrderQuery();


        // Lấy các đơn phản hồi từ cơ sở dữ liệu
        List<FeedbackOrder> feedbackOrders = query.getFeedbackOrders();

        if (feedbackOrders.isEmpty()) {
            // Không có đơn phản hồi, hiển thị trạng thái trống
            view.findViewById(R.id.imageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.tv_empty).setVisibility(View.VISIBLE);
        } else {
            // Có đơn phản hồi, hiển thị danh sách
            view.findViewById(R.id.imageView).setVisibility(View.GONE);
            view.findViewById(R.id.tv_empty).setVisibility(View.GONE);

            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FeedbackOrderAdapter adapter = new FeedbackOrderAdapter(feedbackOrders);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}

