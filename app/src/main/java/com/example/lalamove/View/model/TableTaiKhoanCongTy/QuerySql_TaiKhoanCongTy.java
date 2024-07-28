package com.example.lalamove.View.model.TableTaiKhoanCongTy;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.temporal.ValueRange;

public class QuerySql_TaiKhoanCongTy {
    Connection con;
    ConnectionHelper connectionHelper ;

    public String getVaiTroTaiKhoanCongTy(String soDienThoai , Context context){
        String vaiTro = "";
        try{
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if(con != null){
                String query = "SELECT vaitro FROM TaiKhoanCongTy WhERE sodienthoai = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, soDienThoai);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                if(rs.next()){
                    vaiTro = rs.getString(1);
                }else{
                    return null;
                }
                // Đóng kết nối và các resource
                rs.close();
                preparedStatement.close();
                con.close();
            }else{
                Toast.makeText(context, "Lấy dữ liệu thất bại",Toast.LENGTH_SHORT ).show();
            }

        }catch (Exception e){
            Log.e(TAG, e.getMessage(),null );
        }
        return vaiTro;
    }
}
