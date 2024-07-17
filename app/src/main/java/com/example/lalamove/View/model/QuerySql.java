package com.example.lalamove.View.model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.lalamove.View.Home.KhachHang.Home_khachhang_Activity;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class QuerySql {
    Connection con;
    ConnectionHelper connectionHelper ;

    //Them Thong Tin Tai Khoan
    public void sp_insert_TaiKhoan(String sodienthoai, String ten, String matkhau, String loaitaikhoan, String hinhdaidien,Context context) {
        try {
             connectionHelper = new ConnectionHelper();
             con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "{call sp_insert_TaiKhoan(?, ?, ?, ?, ?)}";
                CallableStatement callableStatement = con.prepareCall(sql);
                callableStatement.setString(1, sodienthoai);
                callableStatement.setString(2, ten);
                callableStatement.setString(3, matkhau);
                callableStatement.setString(4, loaitaikhoan);
                callableStatement.setString(5, hinhdaidien);

                callableStatement.execute();
                Toast.makeText(context, "Dữ liệu đã được chèn thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra , thử lại sau", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug

        }
    }
    // Tìm kiếm thông tin tài khoản theo số điện thoại
    public void sp_search_taikhoan(String sodienthoaiDn,String matkhauDn,Context context) {
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "{call sp_search_taikhoan(?)}";
                CallableStatement callableStatement = con.prepareCall(sql);

                // Thiết lập giá trị cho tham số của stored procedure
                callableStatement.setString(1, sodienthoaiDn);

                // Thực thi truy vấn
                ResultSet rs = callableStatement.executeQuery();

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    // Đọc dữ liệu từ ResultSet và xử lý
                    String matkhau = rs.getString("matkhau");
                    String loaitaikhoan = rs.getString("loaitaikhoan");
                    // so sánh mật khẩu đăng nhập với mật khẩu trong database
                    if(!matkhauDn.equals(matkhau)){
                        Toast.makeText(context, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        // Đóng kết nối và các resource
                        rs.close();
                        callableStatement.close();
                        con.close();
                        return;
                    }

                    //Home khachhang
                    if (loaitaikhoan.equals("KhachHang")) {
                        Intent intent = new Intent(context, Home_khachhang_Activity.class);
                        context.startActivity(intent);
                    }


                } else {
                    Toast.makeText(context, "Số điện thoại không tồn tại", Toast.LENGTH_SHORT).show();
                }

                // Đóng kết nối và các resource
                rs.close();
                callableStatement.close();
                con.close();

            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
