import javax.swing.*;
import java.awt.*;

public class bai6 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Custom Icon");

        // Kích thước cửa sổ
        frame.setSize(500, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Đọc hình ảnh logo
        ImageIcon icon = new ImageIcon("logo.png");

        // Đặt icon cho JFrame
        frame.setIconImage(icon.getImage());

        // Tạo JLabel
        JLabel label =
                new JLabel("Custom Icon Window", SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(label);

        // Căn giữa
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}