import java.util.Scanner;

public class Main {

    // Hàm nhập điểm và kiểm tra từ 0 đến 10
    public static double nhapDiem(Scanner sc, String tenDiem) {
        double diem;

        while (true) {
            System.out.print("Nhap " + tenDiem + ": ");

            if (sc.hasNextDouble()) {
                diem = sc.nextDouble();

                if (diem >= 0 && diem <= 10) {
                    return diem;
                } else {
                    System.out.println("Loi! Diem phai nam trong khoang 0 - 10.");
                }
            } else {
                System.out.println("Loi! Vui long nhap mot so.");
                sc.next();
            }
        }
    }

    // Hàm tính điểm tổng kết
    public static double tinhDiemTongKet(
            double chuyenCan,
            double giuaKy,
            double cuoiKy) {

        return chuyenCan * 0.1
                + giuaKy * 0.3
                + cuoiKy * 0.6;
    }

    // Hàm xếp loại
    public static String xepLoai(double diemTongKet) {

        if (diemTongKet >= 8.5) {
            return "A";
        } else if (diemTongKet >= 7.0) {
            return "B";
        } else if (diemTongKet >= 5.5) {
            return "C";
        } else if (diemTongKet >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ===============================
        // NHAP THONG TIN
        // ===============================

        System.out.print("Nhap ma sinh vien: ");
        String maSV = sc.nextLine();

        System.out.print("Nhap ho ten sinh vien: ");
        String hoTen = sc.nextLine();

        double chuyenCan = nhapDiem(sc, "diem chuyen can");
        double giuaKy = nhapDiem(sc, "diem giua ky");
        double cuoiKy = nhapDiem(sc, "diem cuoi ky");

        // ===============================
        // TINH DIEM
        // ===============================

        double diemTongKet =
                tinhDiemTongKet(chuyenCan, giuaKy, cuoiKy);

        String loai = xepLoai(diemTongKet);

        // ===============================
        // HIEN THI KET QUA
        // ===============================

        System.out.println("\n==============================");
        System.out.println("       KET QUA SINH VIEN");
        System.out.println("==============================");

        System.out.println("Ma sinh vien : " + maSV);
        System.out.println("Ho ten       : " + hoTen);
        System.out.println("Chuyen can   : " + chuyenCan);
        System.out.println("Giua ky      : " + giuaKy);
        System.out.println("Cuoi ky      : " + cuoiKy);

        System.out.printf("Diem tong ket: %.2f%n", diemTongKet);
        System.out.println("Xep loai     : " + loai);

        System.out.println("==============================");

        sc.close();
    }
}