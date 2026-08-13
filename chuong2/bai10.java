import javax.swing.*;

public class bai10 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Menu Demo");

        frame.setSize(500, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Tạo menu File
        JMenu fileMenu = new JMenu("File");

        // Tạo menu item Exit
        JMenuItem exitItem = new JMenuItem("Exit");

        // Sự kiện khi chọn Exit
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        // Thêm Exit vào File
        fileMenu.add(exitItem);

        // Thêm File vào JMenuBar
        menuBar.add(fileMenu);

        // Đặt JMenuBar cho JFrame
        frame.setJMenuBar(menuBar);

        // Căn giữa
        frame.setLocationRelativeTo(null);

        // Hiển thị
        frame.setVisible(true);
    }
}