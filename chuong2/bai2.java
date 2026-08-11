import javax.swing.*;

public class bai2 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Welcome");

        frame.setSize(400, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Căn giữa màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị JFrame
        frame.setVisible(true);

        // Hiển thị hộp thoại thông báo
        JOptionPane.showMessageDialog(
                frame,
                "Welcome to Java Swing",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Sau khi nhấn OK thì thoát chương trình
        System.exit(0);
    }
}