import javax.swing.*;
import java.awt.*;

public class bai9 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("JDialog Demo");

        frame.setSize(500, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dùng GridBagLayout để nút nằm giữa
        frame.setLayout(new GridBagLayout());

        // Tạo nút
        JButton button = new JButton("Open Dialog");

        button.setPreferredSize(new Dimension(120, 40));

        // Sự kiện khi nhấn nút
        button.addActionListener(e -> {

            // Tạo JDialog
            JDialog dialog = new JDialog(
                    frame,
                    "Dialog",
                    true
            );

            dialog.setSize(200, 150);

            // Tạo JLabel
            JLabel label = new JLabel(
                    "This is a dialog",
                    SwingConstants.CENTER
            );

            dialog.add(label);

            // Dialog xuất hiện giữa JFrame chính
            dialog.setLocationRelativeTo(frame);

            dialog.setVisible(true);
        });

        frame.add(button);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}