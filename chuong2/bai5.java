import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class bai5 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Digital Clock");

        frame.setSize(400, 200);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Định dạng thời gian
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        String time = now.format(formatter);

        // Tạo JLabel hiển thị thời gian
        JLabel label = new JLabel(time, SwingConstants.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 30));

        frame.add(label);

        // Căn giữa màn hình
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}