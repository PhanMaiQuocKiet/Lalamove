package com.example.lalamove.View.model.TableLoaiPhuongTien;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuerySql {
    ConnectionHelper connectionHelper;
    Connection connection;

    public List<PhuongTien> getDataPhuongTien() {
        List<PhuongTien> listPhuongTien = new ArrayList<>();

        try {
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if (connection != null) {
                String sql = "SELECT * FROM LoaiPhuongTien ORDER BY trongtaitoida , tenphuongtien"; // Sửa câu lệnh SQL
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    // Đọc dữ liệu từ ResultSet
                    String tenPhuongTien = rs.getString("tenphuongtien");
                    int trongLuong = rs.getInt("trongtaitoida");
                    String maphuongtien = rs.getString("maphuongtien");

                    // Tạo đối tượng PhuongTien và thêm vào danh sách
                    PhuongTien phuongTien = new PhuongTien(tenPhuongTien, trongLuong,maphuongtien);
                    listPhuongTien.add(phuongTien);
                }

                if (listPhuongTien.isEmpty()) {
                    Log.e(TAG, "Chưa có phương tiện", null);
                }
            } else {
                Log.e(TAG, "Lỗi kết nối database", null);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), null);
        }

        return listPhuongTien;
    }
}
