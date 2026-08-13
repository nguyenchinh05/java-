import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class bai7 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Resizable Window");

        // Kích thước ban đầu
        frame.setSize(400, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cho phép thay đổi kích thước
        frame.setResizable(true);

        // Kích thước nhỏ nhất
        frame.setMinimumSize(new Dimension(200, 150));

        // Kích thước lớn nhất
        frame.setMaximumSize(new Dimension(800, 600));

        // Đảm bảo không vượt quá 800x600
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int width = frame.getWidth();
                int height = frame.getHeight();

                if (width > 800) {
                    width = 800;
                }

                if (height > 600) {
                    height = 600;
                }

                if (width != frame.getWidth()
                        || height != frame.getHeight()) {

                    frame.setSize(width, height);
                }
            }
        });

        JLabel label =
                new JLabel("Resizable Window", SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(label);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}