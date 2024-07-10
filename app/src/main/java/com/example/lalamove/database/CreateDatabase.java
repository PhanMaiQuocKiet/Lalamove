package com.example.lalamove.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class CreateDatabase extends SQLiteOpenHelper {

    // Tên database
    private final static String DATABASE_NAME = "Lalamove";

    // Version
    private final static int VERSION = 1;

    // Các khóa
    private final static String INT_AUTO_PRIMARYKEY = " INTEGER PRIMARY KEY AUTOINCREMENT,";
    private final static String CREATE_TABLE = "CREATE TABLE ";
    private final static String TEXT = " TEXT,";
    private final static String TEXT_END = " TEXT";
    private final static String DATE = " DATE,";
    private final static String DATE_END = " DATE";
    private final static String INT = " INTEGER,";
    private final static String INT_END = " INTEGER";
    private final static String TEXT_PRIMARYKEY = " TEXT PRIMARY KEY,";


    // Bảng TAIKHOAN
    public final static String TB_TAIKHOAN = "TAIKHOAN";
    public final static String SoDienThoai = "sodienthoai";
    public final static String Gmail = "gmail";
    public final static String MatKhau = "matkhau";
    public final static String VaiTro = "vaitro";

    // Bảng PHUONGTIEN
    public final static String TB_PHUONGTIEN = "PHUONGTIEN";
    public final static String MaPhuongTien = "maphuongtien";
    public final static String TenPhuongTien = "tenphuongtien";
    public final static String TrongLuongHangHoa = "trongluonghanghoa";
    public final static String AnhPhuongTien = "anhphuongtien";
    public final static String MoTa = "mota";

    // Bảng TAIXE
    public final static String TB_TAIXE = "TAIXE";
    public final static String MaTaiXe = "mataixe";
    public final static String MaPhuongTien_TX = "maphuongtien";
    public final static String SoDienThoai_tx = "sodienthoai";
    public final static String Ten_TX = "ten";
    public final static String HinhDaiDien = "hinhdaidien";

    // Bảng NGANHANG
    public final static String TB_NGANHANG = "NGANHANG";
    public final static String STK = "stk";
    public final static String SoDienThoai_NH = "sodienthoai";
    public final static String TenNganHang = "tennganhang";

    // Bảng NHANVIEN
    public final static String TB_NHANVIEN = "NHANVIEN";
    public final static String MaNhanVien = "manhanvien";
    public final static String SoDienThoai_nv = "sodienthoai";
    public final static String Ten_NV = "ten";
    public final static String HinhDaiDien_NV = "hinhdaidien";

    // Bảng Đơn Hàng
    public final static String TB_DONHANG = "DONHANG";
    public final static String MaDonHang = "madonhang";
    public final static String MaPhuongTien_dh = "maphuongtien";
    public final static String SoDienThoai_dh = "sodienthoai";
    public final static String SoDienThoaiNguoiGui = "sodienthoainguoigui";
    public final static String NoiNhan = "noinhan";
    public final static String SoDienThoaiNguoiNhan = "sodienthoainguoinhan";
    public final static String NoiGiao = "noigiao";
    public final static String ChiTietHangHoa = "chitiethanghoa";
    public final static String GiaTienHang = "giatienhang";
    public final static String PhuongThucThanhToan = "phuongthucthanhtoan";
    public final static String LoaiHangVanChuyen = "loaihangvanchuyen";
    public final static String SoLuongGiaoHang = "soluonggiaohang";
    public final static String TrongLuongHangGiao = "trongluonghanggiao";
    public final static String NgayDat = "ngaydat";

    // Bảng CHITIETDONHANG
    public final static String TB_CHITIETDONHANG = "CHITIETDONHANG";
    public final static String MaDonHang_CT = "madonhang";
    public final static String MaTaiXe_CT = "mataixe";
    public final static String TrangThaiDonHang = "trangthaidonhang";
    public final static String TienVanChuyen = "tienvanchuyen";
    public final static String TongTien = "tongtien";


    // Bảng BCDT
    public final static String TB_BCDT = "BCDT";
    public final static String Thang = "thang";
    public final static String Ngay = "ngay";
    public final static String SoHoaDonVanChuyen = "sohoadonvanchuyen";
    public final static String SoDonBiHuy = "sodonbihuy";
    public final static String DoanhThu = "doanhthu";
    public final static String TongDoanhThu = "tongdoanhtu";
    public final static String MaDonHang_BCDT = "madonhang";

    // Bảng YEUTHICH
    public final static String TB_YEUTHICH = "YEUTHICH";
    public final static String SoDienThoai_YT = "sodienthoai";
    public final static String MaTaiXe_YT = "mataixe";
    public final static String SoDonHangDaThucHien = "sodonhangdathuchien";

    // Bảng DANHGIA
    public final static String TB_DANHGIA = "DANHGIA";
    public final static String SoDienThoai_DG = "sodienthoai";
    public final static String MaTaiXe_DG = "mataixe";
    public final static String DiemDanhGia = "diemdanhgia";

    // Bảng KHIEUNAI
    public final static String TB_KHIEUNAI = "KHIEUNAI";
    public final static String MaKhieuNai = "makhieunai";
    public final static String SoDienThoai_KN = "sodienthoai";
    public final static String ChiTietKhieuNai = "chitietkhieunai";

    // Bảng HOTRO
    public final static String TB_HOTRO = "HOTRO";
    public final static String MaHoTro = "mahotro";
    public final static String MaChuDe_HT = "machude";
    public final static String SoDienThoai_HT = "sodienthoai";

    // Bảng CHUDEHOTRO
    public final static String TB_CHUDEHOTRO = "CHUDEHOTRO";
    public final static String MaChuDe = "machude";
    public final static String TenChuDe = "tenchude";
    public final static String MoTaChiTiet = "motachitiet";

    // Bảng CHITIETNHANVIENHOTRO
    public final static String TB_CHITIETNHANVIENHOTRO = "CHITIETNHANVIENHOTRO";
    public final static String MaHoTro_CTNV = "mahotro";
    public final static String MaNhanVien_CTNV = "manhanvien";
    public final static String SoDienThoai_CTNV = "sodienthoai";
    public final static String TrangThaiHoTro = "trangthaihotro";

    public CreateDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        setWriteAheadLoggingEnabled(true); // Đảm bảo hỗ trợ UTF-8

        // Kiểm tra sự tồn tại của database
        if (!checkDatabaseExists(context, DATABASE_NAME)) {
            // Tạo database nếu chưa tồn tại
            SQLiteDatabase db = this.getWritableDatabase();
            onCreate(db);
        }
    }
    private boolean checkDatabaseExists(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng TAIKHOAN
        String createTaiKhoanTable = CREATE_TABLE + TB_TAIKHOAN + "("
                + SoDienThoai + TEXT_PRIMARYKEY
                + Gmail + TEXT
                + MatKhau + TEXT
                + VaiTro + TEXT_END + ")";
        db.execSQL(createTaiKhoanTable);

        // Tạo bảng PHUONGTIEN
        String createPhuongTienTable = CREATE_TABLE + TB_PHUONGTIEN + "("
                + MaPhuongTien + TEXT_PRIMARYKEY
                + TenPhuongTien + TEXT
                + TrongLuongHangHoa + INT
                + AnhPhuongTien + TEXT
                + MoTa + TEXT_END + ")";
        db.execSQL(createPhuongTienTable);

        // Tạo bảng TAIXE
        String createTaiXeTable = CREATE_TABLE + TB_TAIXE + "("
                + MaTaiXe + TEXT_PRIMARYKEY
                + MaPhuongTien_TX + TEXT
                + SoDienThoai_tx + TEXT
                + Ten_TX + TEXT
                + HinhDaiDien + TEXT_END + ")";
        db.execSQL(createTaiXeTable);

        // Tạo bảng NGANHANG
        String createNganHangTable = CREATE_TABLE + TB_NGANHANG + "("
                + STK + TEXT_PRIMARYKEY
                + SoDienThoai_NH + TEXT
                + TenNganHang + TEXT_END + ")";
        db.execSQL(createNganHangTable);

        // Tạo bảng NHANVIEN
        String createNhanVienTable = CREATE_TABLE + TB_NHANVIEN + "("
                + MaNhanVien + TEXT_PRIMARYKEY
                + SoDienThoai_nv + TEXT
                + Ten_NV + TEXT
                + HinhDaiDien_NV + TEXT_END + ")";
        db.execSQL(createNhanVienTable);

        // Tạo bảng ĐONHANG
        String createDonHangTable = CREATE_TABLE + TB_DONHANG + "("
                + MaDonHang + INT_AUTO_PRIMARYKEY
                + MaPhuongTien_dh + TEXT
                + SoDienThoai_dh + TEXT
                + SoDienThoaiNguoiGui + TEXT
                + NoiNhan + TEXT
                + SoDienThoaiNguoiNhan + TEXT
                + NoiGiao + TEXT
                + ChiTietHangHoa + TEXT
                + GiaTienHang + INT
                + PhuongThucThanhToan + TEXT
                + LoaiHangVanChuyen + TEXT
                + SoLuongGiaoHang + INT
                + TrongLuongHangGiao + INT
                + NgayDat + DATE_END + ")";
        db.execSQL(createDonHangTable);

        // Tạo bảng CHITIETDONHANG
        String createChiTietDonHangTable = CREATE_TABLE + TB_CHITIETDONHANG + "("
                + MaDonHang_CT + TEXT
                + MaTaiXe_CT + TEXT
                + TrangThaiDonHang + TEXT
                + TienVanChuyen + INT
                + TongTien + INT_END + ")";
        db.execSQL(createChiTietDonHangTable);

        // Tạo bảng BCDT
        String createBCDTTable = CREATE_TABLE + TB_BCDT + "("
                + Thang + INT
                + Ngay + DATE
                + SoHoaDonVanChuyen + INT
                + SoDonBiHuy + INT
                + DoanhThu + INT
                + TongDoanhThu + INT
                + MaDonHang_BCDT + INT_END + ")";
        db.execSQL(createBCDTTable);

        // Tạo bảng YEUTHICH
        String createYeuThichTable = CREATE_TABLE + TB_YEUTHICH + "("
                + SoDienThoai_YT + TEXT
                + MaTaiXe_YT + TEXT
                + SoDonHangDaThucHien + INT_END + ")";
        db.execSQL(createYeuThichTable);

        // Tạo bảng DANHGIA
        String createDanhGiaTable = CREATE_TABLE + TB_DANHGIA + "("
                + SoDienThoai_DG + TEXT
                + MaTaiXe_DG + TEXT
                + DiemDanhGia + INT_END + ")";
        db.execSQL(createDanhGiaTable);

        // Tạo bảng KHIEUNAI
        String createKhieuNaiTable = CREATE_TABLE + TB_KHIEUNAI + "("
                + MaKhieuNai + INT_AUTO_PRIMARYKEY
                + SoDienThoai_KN + TEXT
                + ChiTietKhieuNai + TEXT_END + ")";
        db.execSQL(createKhieuNaiTable);

        // Tạo bảng HOTRO
        String createHoTroTable = CREATE_TABLE + TB_HOTRO + "("
                + MaHoTro + INT_AUTO_PRIMARYKEY
                + MaChuDe_HT + TEXT
                + SoDienThoai_HT + TEXT_END + ")";
        db.execSQL(createHoTroTable);

        // Tạo bảng CHUDEHOTRO
        String createChuDeHoTroTable = CREATE_TABLE + TB_CHUDEHOTRO + "("
                + MaChuDe + INT_AUTO_PRIMARYKEY
                + TenChuDe + TEXT
                + MoTaChiTiet + TEXT_END + ")";
        db.execSQL(createChuDeHoTroTable);

        // Tạo bảng CHITIETNHANVIENHOTRO
        String createChiTietNhanVienHoTroTable = CREATE_TABLE + TB_CHITIETNHANVIENHOTRO + "("
                + MaHoTro_CTNV + INT
                + MaNhanVien_CTNV + TEXT
                + SoDienThoai_CTNV + TEXT
                + TrangThaiHoTro + TEXT_END + ")";
        db.execSQL(createChiTietNhanVienHoTroTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa và tạo lại bảng nếu có bất kỳ thay đổi cấu trúc nào
        db.execSQL("DROP TABLE IF EXISTS " + TB_TAIKHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_PHUONGTIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_TAIXE);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NGANHANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DONHANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETDONHANG);
        db.execSQL("DROP TABLE IF EXISTS " + TB_BCDT);
        db.execSQL("DROP TABLE IF EXISTS " + TB_YEUTHICH);
        db.execSQL("DROP TABLE IF EXISTS " + TB_DANHGIA);
        db.execSQL("DROP TABLE IF EXISTS " + TB_KHIEUNAI);
        db.execSQL("DROP TABLE IF EXISTS " + TB_HOTRO);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CHUDEHOTRO);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETNHANVIENHOTRO);

        // Tạo lại bảng mới
        onCreate(db);
    }
}
