package com.example.lalamove.View.model.TableTaiKhoanTaiXe;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaiXe_QuerySql {
    ConnectionHelper connectionHelper;
    Connection con;
    public String getMaPhuongTien(String sodienthoai, Context context) {
        String maphuongtien = "";
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT maphuongtien FROM TaiKhoanTaiXe WHERE sodienthoaitaixe = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, sodienthoai);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    maphuongtien = rs.getString(1);
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu ", Toast.LENGTH_SHORT).show();
                }

                // Đóng kết nối và các resource
                rs.close();
                preparedStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return maphuongtien;
    }
    public void getTenTaiXe_SoSao(String sodienthoai, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ChiTietTaiXe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT ten, diemdanhgia " +
                        "FROM TaiKhoanTaiXe tktx " +
                        "INNER JOIN TaiKhoan tk ON tktx.sodienthoaitaixe = tk.sodienthoai " +
                        "WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, sodienthoai);

                rs = preparedStatement.executeQuery(); // Không cần truyền tham số vào phương thức

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    editor.putString("tentaixe", rs.getString(1));
                    editor.putInt("diemdanhgia", rs.getInt(2));
                    editor.apply();
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        } finally {
            // Đóng kết nối và các resource trong khối finally
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (Exception e) {
                Log.e("TAG", "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }


}
