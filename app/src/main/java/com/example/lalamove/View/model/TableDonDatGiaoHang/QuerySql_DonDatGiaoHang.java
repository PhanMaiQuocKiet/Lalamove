package com.example.lalamove.View.model.TableDonDatGiaoHang;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class QuerySql_DonDatGiaoHang {
    ConnectionHelper connectionHelper ;

    public void sp_insert_DonDatGiaoHang_ChiTietDon(
            String sodienthoaikhachhhang,
            String sodienthoainguoigui,
            String noinhan,
            String sodienthoaiguoigiao,
            String noigiao,
            java.util.Date thoigiandathang,
            String maphuongtien,
            String ghichuchotaixe,
            int soluongthunghang,
            String maloaihang,
            String trangthai,
            int giatien,
            Context context) {
        try {
             connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "{call sp_insert_DonDatGiaoHang_ChiTietDon(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement callableStatement = con.prepareCall(sql);

                // Thiết lập giá trị cho các tham số
                callableStatement.setString(1, sodienthoaikhachhhang);
                callableStatement.setString(2, sodienthoainguoigui);
                callableStatement.setString(3, noinhan);
                callableStatement.setString(4, sodienthoaiguoigiao);
                callableStatement.setString(5, noigiao);
                callableStatement.setTimestamp(6, new java.sql.Timestamp(thoigiandathang.getTime()));
                callableStatement.setString(7, maphuongtien);
                callableStatement.setString(8, ghichuchotaixe);
                callableStatement.setInt(9, soluongthunghang);
                callableStatement.setString(10, maloaihang);
                callableStatement.setString(11, trangthai);
                callableStatement.setInt(12, giatien);

                // Thực thi stored procedure
                callableStatement.execute();
                Toast.makeText(context, "Thêm đơn hàng thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), null);
        }
    }

    public void getDataAll2(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String sodienthoai,String trangthai1 , String trangthai2) {
        List<DonHang> listDonHang = new ArrayList<>();
        Log.d(TAG, "getDataAll2: "+trangthai1 );
        Log.d(TAG, "getDataAll2: "+trangthai2 );
        try {
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT ddgh.MaDonHang, ddgh.SoDienThoaiKhachHang, ddgh.SoDienThoaiNguoiGui, " +
                        "ddgh.NoiNhan, ddgh.SoDienThoaiguoiGiao, ddgh.NoiGiao, " +
                        "ddgh.ThoiGianDatHang, ddgh.MaPhuongTien, ctdg.GiaTien, ddgh.TrangThai, " +
                        "lpt.tenphuongtien, ctdg.sodienthoaitaixe " +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "INNER JOIN LoaiPhuongTien lpt ON ddgh.maphuongtien = lpt.maphuongtien " +
                        "WHERE (ddgh.TrangThai = ? OR ddgh.TrangThai = ?) AND ddgh.SoDienThoaiKhachHang = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai1);
                preparedStatement.setString(2,trangthai2);
                preparedStatement.setString(3, sodienthoai);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    String tenPhuongTien = rs.getString("TenPhuongTien");
                    int giaTien = rs.getInt("GiaTien");
                    String trangThai = rs.getString("TrangThai");
                    String soDienThoaiTaiXe = rs.getString("sodienthoaitaixe");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien,tenPhuongTien,trangThai,soDienThoaiTaiXe);
                    listDonHang.add(donHang);
                }
                preparedStatement.close();
                con.close();
                rs.close();
                Log.d("QuerySql_DonDatGiaoHang", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getDataAllHoanThanh(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String sodienthoai, String trangthai) {
        List<DonHang> listDonHang = new ArrayList<>();
        try {
            Log.d(TAG, "getDataAllHoanThanh : Số điện thoại từ SharedPreferences: " + sodienthoai);
            Log.d(TAG, "getDataAllHoanThanh : Trạng thái đơn hàng: " + trangthai);
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT ddgh.MaDonHang, ddgh.SoDienThoaiKhachHang, ddgh.SoDienThoaiNguoiGui, " +
                        "ddgh.NoiNhan, ddgh.SoDienThoaiguoiGiao, ddgh.NoiGiao, " +
                        "ddgh.ThoiGianDatHang, ddgh.MaPhuongTien, ctdg.GiaTien, ddgh.TrangThai, " +
                        "lpt.tenphuongtien, ctdg.sodienthoaitaixe " +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "INNER JOIN LoaiPhuongTien lpt ON ddgh.maphuongtien = lpt.maphuongtien " +
                        "WHERE ddgh.trangthai = ? AND ddgh.SoDienThoaiKhachHang = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai);
                preparedStatement.setString(2, sodienthoai);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    String tenPhuongTien = rs.getString("TenPhuongTien");
                    int giaTien = rs.getInt("GiaTien");
                    String trangThai = rs.getString("TrangThai");
                    String soDienThoaiTaiXe = rs.getString("sodienthoaitaixe");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien,tenPhuongTien,trangThai,soDienThoaiTaiXe);
                    listDonHang.add(donHang);
                }
                Log.d("QuerySql_DonDatGiaoHang_huy", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);
                preparedStatement.close();
                con.close();
                rs.close();

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void getDataAllHuy(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String sodienthoai, String trangthai){
        List<DonHang> listDonHang = new ArrayList<>();
        Log.d(TAG, "getDataAllHuy: " + trangthai);
        try {
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT ddgh.MaDonHang, ddgh.SoDienThoaiKhachHang, ddgh.SoDienThoaiNguoiGui, " +
                        "ddgh.NoiNhan, ddgh.SoDienThoaiguoiGiao, ddgh.NoiGiao, " +
                        "ddgh.ThoiGianDatHang, ddgh.MaPhuongTien, ctdg.GiaTien, ddgh.TrangThai " +
                        ",lpt.tenphuongtien, ctdg.sodienthoaitaixe " +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "INNER JOIN LoaiPhuongTien lpt ON ddgh.maphuongtien = lpt.maphuongtien " +
                        "WHERE ddgh.trangthai = ? AND ddgh.SoDienThoaiKhachHang = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai);
                preparedStatement.setString(2, sodienthoai);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    String tenPhuongTien = rs.getString("TenPhuongTien");
                    int giaTien = rs.getInt("GiaTien");
                    String trangThai = rs.getString("TrangThai");
                    String soDienThoaiTaiXe = rs.getString("sodienthoaitaixe");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien,tenPhuongTien,trangThai,soDienThoaiTaiXe);
                    listDonHang.add(donHang);
                }
                Log.d("QuerySql_DonDatGiaoHang_huy", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);
                preparedStatement.close();
                con.close();
                rs.close();

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void getDataHoanThanhTaiXe(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String sodienthoai, String trangthai) {
        List<DonHang> listDonHang = new ArrayList<>();
        try {
            Log.d(TAG, "Số điện thoại từ SharedPreferences: " + sodienthoai);
            Log.d(TAG, "Trạng thái đơn hàng: " + trangthai);
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT ddgh.MaDonHang, ddgh.SoDienThoaiKhachHang, ddgh.SoDienThoaiNguoiGui, " +
                        "ddgh.NoiNhan, ddgh.SoDienThoaiguoiGiao, ddgh.NoiGiao, " +
                        "ddgh.ThoiGianDatHang, ddgh.MaPhuongTien, ctdg.GiaTien, ddgh.TrangThai, " +
                        "lpt.tenphuongtien, ctdg.sodienthoaitaixe " +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "INNER JOIN LoaiPhuongTien lpt ON ddgh.maphuongtien = lpt.maphuongtien " +
                        "WHERE ddgh.trangthai = ? AND ctdg.sodienthoaitaixe = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai);
                preparedStatement.setString(2, sodienthoai);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    String tenPhuongTien = rs.getString("TenPhuongTien");
                    int giaTien = rs.getInt("GiaTien");
                    String trangThai = rs.getString("TrangThai");
                    String soDienThoaiTaiXe = rs.getString("sodienthoaitaixe");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien,tenPhuongTien,trangThai,soDienThoaiTaiXe);
                    listDonHang.add(donHang);
                }
                Log.d("QuerySql_DonDatGiaoHang_huy", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);
                preparedStatement.close();
                con.close();
                rs.close();

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    public void getDonHangChuaNhanDon(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String maxe,String sodienthoaitaixe,String trangthai , String trangthai2) {
        List<DonHang> listDonHang = new ArrayList<>();
        try {
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT *" +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "Inner Join LoaiPhuongTien lpt ON  ddgh.maphuongtien = lpt.maphuongtien " +
                        "Inner Join TaiKhoanTaiXe tktx ON lpt.maphuongtien = tktx.maphuongtien " +
                        "WHERE (ddgh.trangthai = ? or ddgh.TrangThai = ?) AND tktx.maphuongtien  = ? AND tktx.sodienthoaitaixe = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai);
                preparedStatement.setString(2,trangthai2);
                preparedStatement.setString(3, maxe);
                preparedStatement.setString(4, sodienthoaitaixe);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    String tenPhuongTien = rs.getString("TenPhuongTien");
                    String trangThai = rs.getString("TrangThai");
                    int giaTien = rs.getInt("GiaTien");
                    String soDienThoaiTaiXe = rs.getString("sodienthoaitaixe");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien,tenPhuongTien,trangThai,soDienThoaiTaiXe);
                    listDonHang.add(donHang);
                }
                Log.d("QuerySql_DonDatGiaoHang", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public String getTrangThai(Context context, int maDonHang) {
        String trangThai = "";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT trangthai FROM DonDatGiaoHang WHERE madonhang = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, maDonHang);
                rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    trangThai = rs.getString("trangthai");
                }
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi lại lỗi để dễ dàng debug
        } finally {
            // Đảm bảo tài nguyên được đóng
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return trangThai;
    }

    public boolean updateTrangThaiDonHang(Context context, String trangThai, int maDonHang) {
        boolean flags = false;
        try {
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "UPDATE DonDatGiaoHang set trangthai = ? where madonhang = ? ";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangThai);
                preparedStatement.setInt(2,maDonHang);

                int rs = preparedStatement.executeUpdate();

                if (rs > 0) {
                    flags = true;
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
                preparedStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(),null );
        }
        return flags;
    }



}
