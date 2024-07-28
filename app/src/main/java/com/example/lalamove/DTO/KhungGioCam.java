package com.example.lalamove.DTO;

import java.util.List;

public class KhungGioCam {
    public String getMaKhungGio() {
        return maKhungGio;
    }

    public void setMaKhungGio(String maKhungGio) {
        this.maKhungGio = maKhungGio;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public List<String> getMaPhuongTienBiCam() {
        return maPhuongTienBiCam;
    }

    public void setMaPhuongTienBiCam(List<String> maPhuongTienBiCam) {
        this.maPhuongTienBiCam = maPhuongTienBiCam;
    }

    private String maKhungGio;
    public KhungGioCam(){}

    public KhungGioCam(String maKhungGio, String gioBatDau, String gioKetThuc, List<String> maPhuongTienBiCam) {
        this.maKhungGio = maKhungGio;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.maPhuongTienBiCam = maPhuongTienBiCam;
    }

    private String gioBatDau;
    private String gioKetThuc;
    private List<String> maPhuongTienBiCam;

    // Constructor, getters, setters
}