package lab4;

import javax.swing.*;
import java.awt.*;

public class bai3 extends JFrame {

    private JTextField txtN;
    private JButton btnCalculate;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public bai3() {

        // ==========================
        // THIẾT LẬP CỬA SỔ
        // ==========================
        setTitle("Bài 3 - Tính tổng số nguyên tố");
        setSize(500, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "TÍNH TỔNG CÁC SỐ NGUYÊN TỐ NHỎ HƠN N",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        // ==========================
        // NHẬP N
        // ==========================
        JPanel inputPanel = new JPanel();

        JLabel lblN = new JLabel("Nhập N:");

        txtN = new JTextField(12);

        inputPanel.add(lblN);
        inputPanel.add(txtN);

        // ==========================
        // NÚT TÍNH
        // ==========================
        btnCalculate = new JButton("Tính");

        btnCalculate.setPreferredSize(
                new Dimension(100, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnCalculate);

        // ==========================
        // PROGRESS BAR
        // ==========================
        progressBar = new JProgressBar(0, 100);

        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        progressBar.setPreferredSize(
                new Dimension(380, 25)
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
        // SỰ KIỆN NÚT TÍNH
        // ==========================
        btnCalculate.addActionListener(
                e -> calculatePrimeSum()
        );
    }

    // =================================
    // HÀM KIỂM TRA SỐ NGUYÊN TỐ
    // =================================
    private boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // =================================
    // HÀM TÍNH TỔNG SỐ NGUYÊN TỐ
    // =================================
    private void calculatePrimeSum() {

        int n;

        try {

            n = Integer.parseInt(
                    txtN.getText().trim()
            );

            if (n <= 2) {

                JOptionPane.showMessageDialog(
                        this,
                        "N phải lớn hơn 2!",
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

        // Không cho nhấn nhiều lần
        btnCalculate.setEnabled(false);

        txtN.setEnabled(false);

        progressBar.setValue(0);

        lblResult.setText("Đang tính...");

        // Lưu N để sử dụng trong SwingWorker
        final int number = n;

        // =================================
        // SWINGWORKER
        // =================================
        SwingWorker<Long, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Long doInBackground() {

                        long sum = 0;

                        for (int i = 2; i < number; i++) {

                            // Kiểm tra số nguyên tố
                            if (isPrime(i)) {
                                sum += i;
                            }

                            // Tính phần trăm tiến độ
                            int progress =
                                    (int) (
                                            (i * 100.0)
                                                    / number
                                    );

                            setProgress(progress);
                        }

                        return sum;
                    }

                    // ==========================
                    // KHI TÍNH XONG
                    // ==========================
                    @Override
                    protected void done() {

                        try {

                            // Lấy kết quả từ doInBackground()
                            long result = get();

                            lblResult.setText(
                                    "Tổng các số nguyên tố nhỏ hơn "
                                            + number
                                            + " = "
                                            + result
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Có lỗi khi tính toán!"
                            );
                        }

                        progressBar.setValue(100);

                        btnCalculate.setEnabled(true);

                        txtN.setEnabled(true);
                    }
                };

        // =================================
        // CẬP NHẬT PROGRESS BAR
        // =================================
        worker.addPropertyChangeListener(evt -> {

            if ("progress".equals(
                    evt.getPropertyName())) {

                int value =
                        (int) evt.getNewValue();

                progressBar.setValue(value);
            }
        });

        // Bắt đầu SwingWorker
        worker.execute();
    }

    // =================================
    // MAIN
    // =================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai3 frame = new bai3();

            frame.setVisible(true);
        });
    }
}