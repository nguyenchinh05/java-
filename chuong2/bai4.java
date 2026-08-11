import javax.swing.*;

public class bai4 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Image Viewer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Đọc hình ảnh từ file
        ImageIcon image = new ImageIcon("image.png");
        
        // Tạo JLabel chứa hình ảnh
        JLabel label = new JLabel(image);

        // Thêm JLabel vào JFrame
        frame.add(label);

        // Tự động điều chỉnh kích thước JFrame theo hình ảnh
        frame.pack();

        // Căn giữa màn hình
        frame.setLocationRelativeTo(null);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}