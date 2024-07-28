package com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi;

import android.util.Log;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackOrderQuery {
    public List<FeedbackOrder> getFeedbackOrders() {
        List<FeedbackOrder> feedbackOrders = new ArrayList<>();
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connection = connectionHelper.connectionClass();

        if (connection != null) {
            try {
                String query = "SELECT madonphanhoi, thoidiemguidon, trangthaihotro FROM DonPhanHoi";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    FeedbackOrder order = new FeedbackOrder();
                    order.setMadonphanhoi(rs.getInt("madonphanhoi"));
                    order.setThoidiemguidon(rs.getString("thoidiemguidon"));
                    order.setTrangthaihotro(rs.getString("trangthaihotro"));
                    feedbackOrders.add(order);
                }
                connection.close();
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
        return feedbackOrders;
    }
}