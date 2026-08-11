import javax.swing.*;
import java.awt.*;

public class bai3 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Exit Application");

        // Kích thước JFrame
        frame.setSize(300, 200);

        // Đóng chương trình khi bấm X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sử dụng GridBagLayout để đặt nút giữa
        frame.setLayout(new GridBagLayout());

        // Tạo nút Exit
        JButton btnExit = new JButton("Exit");

        // Chỉnh kích thước nút
        btnExit.setPreferredSize(new Dimension(100, 40));

        // Thêm sự kiện cho nút
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        // Thêm nút vào JFrame
        frame.add(btnExit);

        // Căn JFrame giữa màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}