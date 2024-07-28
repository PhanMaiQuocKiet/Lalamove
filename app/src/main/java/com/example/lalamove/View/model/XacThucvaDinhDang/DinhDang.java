package com.example.lalamove.View.model.XacThucvaDinhDang;

public class DinhDang {
    public static boolean isDinhDangSoDienThoai(String phoneNumber) {
        String regex = "^[0-9]{10}$";
        return phoneNumber.matches(regex);
    }
    public static boolean isDinhDangBienSoXe(String biensoxe) {
        String regex = "^[0-9]{5}$";
        return biensoxe.matches(regex);
    }
    public static boolean isDinhDangEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
    public static boolean isDinhDangMatKhau(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        return password.matches(regex);
    }
    public static boolean isDinhDangTen(String name) {
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }
    public static String dinhDangSDT(String number) {
        // Remove all non-digit characters
        number = number.replaceAll("[^\\d]", "");

        // Add country code if not already present
        if (!number.startsWith("84")) {
            number = "84" + number.substring(1); // Assumes the number starts with a 0
        }

        return "+" + number;
    }
}
