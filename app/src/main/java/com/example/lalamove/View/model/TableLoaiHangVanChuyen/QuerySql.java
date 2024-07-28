package com.example.lalamove.View.model.TableLoaiHangVanChuyen;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuerySql {
    ConnectionHelper connectionHelper;
    Connection connection;

    public String getMaLoaiHangVanChuyen(String tenloaihangvanchuen , Context context){
        String maLoaiHang = "";
        try{
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection != null){
                String sql = "SELECT maloaihang From LoaiHangVanChuyen Where tenloaihang = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, tenloaihangvanchuen);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();
                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    maLoaiHang = rs.getString(1);
                } else {
                    Toast.makeText(context, "không có mã phương tiện phù hợp ", Toast.LENGTH_SHORT).show();
                }
                // Đóng kết nối và các resource
                rs.close();
                preparedStatement.close();
                connection.close();
            }else{
                Toast.makeText(context, "Lấy dữ liệu thất bại",Toast.LENGTH_SHORT ).show();
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage(),null );
        }
        return  maLoaiHang;
    }
}
