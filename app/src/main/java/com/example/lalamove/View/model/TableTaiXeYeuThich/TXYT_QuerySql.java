package com.example.lalamove.View.model.TableTaiXeYeuThich;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.View.Home.KhachHang.TXYT.TXyeuthich;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class  TXYT_QuerySql {
    ConnectionHelper connectionHelper;
    Connection connection ;

    public boolean isTaiXeYeuThich(String sodienthoai , Context context){
        boolean flag = false;
        try{
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection != null){
                String sql = "SELECT * FROM TaiXeYeuThich WHERE = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                if(rs != null){
                    flag = true;
                }else{
                    Toast.makeText(context,"Chưa có tài xế yêu thích ",Toast.LENGTH_SHORT).show();
                }


                rs.close();
                connection.close();
                preparedStatement.close();



            }else{
                Toast.makeText(context,"lỗi không truy xuất được dữ liệu",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage(),null );
        }
        return flag;
    }
    public void sp_insert_DanhGiaTaiXe(String sodienthoaikhachhang, String sodienthoaitaixe , int diemdanhgia , Context context){

        try{
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection != null){
                String sql = "{Call sp_insert_DanhGiaTaiXe(?,?,?)}";
                CallableStatement CallableStatement = connection.prepareCall(sql);

                CallableStatement.setString(1, sodienthoaikhachhang);
                CallableStatement.setString(2, sodienthoaitaixe);
                CallableStatement.setInt(3, diemdanhgia);

                CallableStatement.execute();
                Toast.makeText(context,"Bạn đã đánh giá tài xế " + diemdanhgia + " sao ",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"lỗi không truy xuất được dữ liệu",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage(),null );
            Toast.makeText(context,"Tài xế đơn hàng này đã được bạn đánh giá",Toast.LENGTH_SHORT).show();
        }

    }
    public List<TXyeuthich> getFavoriteDrivers(String sodienthoaiKhachHang, Context context) {
        List<TXyeuthich> favoriteDrivers = new ArrayList<>();
        try {
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String sql = "SELECT sodienthoaitaixe , sodienthoaikhachhang, sodondavanchuyen " +
                        "FROM TaiXeYeuThich  " +

                        "WHERE sodienthoaiKhachHang = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, sodienthoaiKhachHang);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String sodienthoaiTaiXe = rs.getString("sodienthoaiTaiXe");
                    String sodienthoaiKH = rs.getString("sodienthoaiKhachHang");
                    int sodondavanchuyen = rs.getInt("sodondavanchuyen");

                    TXyeuthich driver = new TXyeuthich(sodienthoaiTaiXe, sodienthoaiKH, sodondavanchuyen);
                    favoriteDrivers.add(driver);
                }

                rs.close();
                preparedStatement.close();
                connection.close();

            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi lấy danh sách tài xế yêu thích: " + e.getMessage());
        }
        return favoriteDrivers;
    }
    public boolean ThemTXyeuthich(String customerPhone, String driverPhone) {
        boolean success = false;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            Connection connection = connectionHelper.connectionClass();
            if (connection != null) {
                String sql = "INSERT INTO TaiXeYeuThich (sodienthoaiKhachHang, sodienthoaiTaiXe) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerPhone);
                preparedStatement.setString(2, driverPhone);

                int rowsAffected = preparedStatement.executeUpdate();
                success = rowsAffected > 0;

                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi thêm tài xế yêu thích: " + e.getMessage());
        }
        return success;
    }
}
