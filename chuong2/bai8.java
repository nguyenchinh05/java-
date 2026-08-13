import javax.swing.*;
import java.awt.*;

public class bai8 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Colored Background");

        frame.setSize(400, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Đặt màu nền xanh lá
        frame.getContentPane().setBackground(Color.GREEN);

        // Sử dụng BorderLayout
        frame.setLayout(new BorderLayout());

        // Tạo JLabel
        JLabel label =
                new JLabel("Colored Background", SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(label, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}