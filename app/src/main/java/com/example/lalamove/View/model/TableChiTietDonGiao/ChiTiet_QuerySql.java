package com.example.lalamove.View.model.TableChiTietDonGiao;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class ChiTiet_QuerySql {
    private Context context;

    public ChiTiet_QuerySql(Context context) {
        this.context = context;
    }
    ConnectionHelper connectionHelper;
    Connection con;
    public void CapNhatChiTietDonGiaoHang(String sodienthoaitaixe, java.util.Date thoigiannhanhang, int madonhang) {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        String sql = "UPDATE ChiTietDonGiao SET sodienthoaitaixe = ?, thoigiannhanhang = ? WHERE madonhang = ?";

        try (Connection con = connectionHelper.connectionClass();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, sodienthoaitaixe);
            preparedStatement.setTimestamp(2, new Timestamp(thoigiannhanhang.getTime()));
            preparedStatement.setInt(3, madonhang);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Toast.makeText(context, "Nhận đơn", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Không có dữ liệu nào được cập nhật", Toast.LENGTH_SHORT).show();
            }

        } catch (SQLException e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
    public Timestamp  getThoiGianNhanDon(String sodienthoai, int madonhang) {
        Timestamp  date = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT thoigiannhanhang FROM ChiTietDonGiao WHERE sodienthoaitaixe = ? AND madonhang = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, sodienthoai);
                preparedStatement.setInt(2, madonhang);

                rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    date = rs.getTimestamp("thoigiannhanhang");
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
            Toast.makeText(context, "Lỗi khi truy xuất dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
        return date;
    }

}
