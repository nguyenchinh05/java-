package lab4;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class bai4 extends JFrame {

    private JTextField txtN;
    private JButton btnFind;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public bai4() {

        // ==========================
        // THIẾT LẬP CỬA SỔ
        // ==========================
        setTitle("Bài 4 - Tìm số Fibonacci");
        setSize(650, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "TÌM SỐ FIBONACCI",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // ==========================
        // NHẬP N
        // ==========================
        JPanel inputPanel = new JPanel();

        JLabel lblN = new JLabel("Nhập N:");

        txtN = new JTextField(15);

        inputPanel.add(lblN);
        inputPanel.add(txtN);

        // ==========================
        // NÚT TÌM
        // ==========================
        btnFind = new JButton("Tìm");

        btnFind.setPreferredSize(
                new Dimension(100, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnFind);

        // ==========================
        // PROGRESS BAR
        // ==========================
        progressBar = new JProgressBar(0, 100);

        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        progressBar.setPreferredSize(
                new Dimension(450, 25)
        );

        JPanel progressPanel = new JPanel();

        progressPanel.add(progressBar);

        // ==========================
        // LABEL KẾT QUẢ
        // ==========================
        lblResult = new JLabel(
                "Kết quả: ",
                SwingConstants.CENTER
        );

        lblResult.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        // ==========================
        // PANEL CHÍNH
        // ==========================
        JPanel mainPanel = new JPanel(
                new GridLayout(5, 1, 5, 5)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        mainPanel.add(lblTitle);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(progressPanel);
        mainPanel.add(lblResult);

        add(mainPanel);

        // ==========================
        // SỰ KIỆN NÚT TÌM
        // ==========================
        btnFind.addActionListener(
                e -> findFibonacci()
        );
    }

    // =================================
    // HÀM TÍNH FIBONACCI
    // CÓ MEMOIZATION
    // =================================
    private BigInteger fibonacci(
            int n,
            Map<Integer, BigInteger> memo) {

        // Fibonacci(0) = 0
        // Fibonacci(1) = 1
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // Nếu đã tính rồi thì lấy lại kết quả
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Tính Fibonacci
        BigInteger value =
                fibonacci(n - 1, memo)
                        .add(
                                fibonacci(n - 2, memo)
                        );

        // Lưu kết quả vào Map
        memo.put(n, value);

        return value;
    }

    // =================================
    // HÀM XỬ LÝ TÌM FIBONACCI
    // =================================
    private void findFibonacci() {

        int n;

        // ==========================
        // KIỂM TRA DỮ LIỆU
        // ==========================
        try {

            n = Integer.parseInt(
                    txtN.getText().trim()
            );

            if (n < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "N phải >= 0!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập số nguyên hợp lệ!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Lưu N để dùng trong SwingWorker
        final int number = n;

        // Không cho bấm nhiều lần
        btnFind.setEnabled(false);

        txtN.setEnabled(false);

        // ProgressBar chạy không xác định
        progressBar.setValue(0);
        progressBar.setIndeterminate(true);
        progressBar.setString("Đang xử lý...");

        lblResult.setText(
                "Đang tính Fibonacci..."
        );

        // =================================
        // SWINGWORKER
        // =================================
        SwingWorker<BigInteger, Void> worker =
                new SwingWorker<>() {

                    // Chạy trong luồng nền
                    @Override
                    protected BigInteger doInBackground() {

                        // Tạo Map lưu kết quả đã tính
                        Map<Integer, BigInteger> memo =
                                new HashMap<>();

                        return fibonacci(
                                number,
                                memo
                        );
                    }

                    // ==========================
                    // KHI TÍNH XONG
                    // ==========================
                    @Override
                    protected void done() {

                        try {

                            // Lấy kết quả
                            BigInteger result = get();

                            lblResult.setText(
                                    "Fibonacci("
                                            + number
                                            + ") = "
                                            + result
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Có lỗi khi tính Fibonacci!"
                            );
                        }

                        // Dừng progress chạy
                        progressBar.setIndeterminate(false);

                        progressBar.setValue(100);

                        progressBar.setString("100%");

                        // Cho phép nhập lại
                        btnFind.setEnabled(true);

                        txtN.setEnabled(true);

                        txtN.requestFocus();
                    }
                };

        // Bắt đầu SwingWorker
        worker.execute();
    }

    // =================================
    // MAIN
    // =================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai4 frame = new bai4();

            frame.setVisible(true);
        });
    }
}