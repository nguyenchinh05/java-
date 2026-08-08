import java.util.Scanner;

public class Main {

    // Ham nhap diem va kiem tra tu 0 den 10
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
                System.out.println("Loi! Vui long nhap bang so.");
                sc.next();
            }
        }
    }

    // Ham tinh diem tong ket
    public static double tinhDiemTongKet(
            double chuyenCan,
            double giuaKy,
            double cuoiKy) {

        return chuyenCan * 0.1
                + giuaKy * 0.3
                + cuoiKy * 0.6;
    }

    // Ham xep loai
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
        // NHAP THONG TIN SINH VIEN
        // ===============================

        System.out.println("==============================================");
        System.out.println("          NHAP THONG TIN SINH VIEN");
        System.out.println("==============================================");

        System.out.print("Nhap ma sinh vien   : ");
        String maSV = sc.nextLine();

        System.out.print("Nhap ho ten         : ");
        String hoTen = sc.nextLine();

        double chuyenCan = nhapDiem(sc, "diem chuyen can");
        double giuaKy = nhapDiem(sc, "diem giua ky");
        double cuoiKy = nhapDiem(sc, "diem cuoi ky");

        // ===============================
        // TINH DIEM TONG KET
        // ===============================

        double diemTongKet =
                tinhDiemTongKet(chuyenCan, giuaKy, cuoiKy);

        String loai = xepLoai(diemTongKet);

        // ===============================
        // HIEN THI KET QUA
        // ===============================

        System.out.println();
        System.out.println("+----------+----------------------+------------+----------+----------+----------+----------+");
        System.out.println("| Ma SV    | Ho ten               | Chuyen can | Giua ky  | Cuoi ky  | Tong ket | Xep loai |");
        System.out.println("+----------+----------------------+------------+----------+----------+----------+----------+");

        System.out.printf(
                "| %-8s | %-20s | %10.2f | %8.2f | %8.2f | %8.2f | %-8s |%n",
                maSV,
                hoTen,
                chuyenCan,
                giuaKy,
                cuoiKy,
                diemTongKet,
                loai
        );

        System.out.println("+----------+----------------------+------------+----------+----------+----------+----------+");

        sc.close();
    }
}