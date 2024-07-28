package com.example.lalamove.ListLoaiXe;

public class PhuongTien {
    String tenPhuongTien;
    int trongLuong;
    private String maPhuongTien;
    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public int getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(int trongLuong) {
        this.trongLuong = trongLuong;
    }


    public String getMaPhuongTien() {
        return maPhuongTien;
    }
    public void setMaPhuongTien(String maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public PhuongTien(String tenPhuongTien, int trongLuong,String maPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
        this.trongLuong = trongLuong;
        this.maPhuongTien = maPhuongTien;
    }

    @Override
    public String toString() {
        return "PhuongTien{" +
                "tenPhuongTien='" + tenPhuongTien + '\'' +
                ", trongLuong=" + trongLuong +
                '}';
    }
}