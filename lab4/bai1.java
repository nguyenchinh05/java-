package lab4;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class bai1 extends JFrame {

    private JTextField txtSeconds;
    private JButton btnStart;
    private JLabel lblTime;

    public bai1() {

        // ==========================
        // THIẾT LẬP JFRAME
        // ==========================
        setTitle("Bài 1 - Đồng hồ đếm ngược");
        setSize(420, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "ĐỒNG HỒ ĐẾM NGƯỢC",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // ==========================
        // NHẬP SỐ GIÂY
        // ==========================
        JLabel lblSeconds = new JLabel("Nhập số giây:");

        txtSeconds = new JTextField(10);

        JPanel inputPanel = new JPanel();

        inputPanel.add(lblSeconds);
        inputPanel.add(txtSeconds);

        // ==========================
        // NÚT BẮT ĐẦU
        // ==========================
        btnStart = new JButton("Bắt đầu");

        btnStart.setPreferredSize(
                new Dimension(120, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnStart);

        // ==========================
        // LABEL THỜI GIAN
        // ==========================
        lblTime = new JLabel(
                "Thời gian còn lại: 0 giây",
                SwingConstants.CENTER
        );

        lblTime.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        // ==========================
        // PANEL CHÍNH
        // ==========================
        JPanel mainPanel = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        mainPanel.add(lblTitle);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(lblTime);

        add(mainPanel);

        // ==========================
        // SỰ KIỆN NÚT BẮT ĐẦU
        // ==========================
        btnStart.addActionListener(e -> startCountdown());
    }

    // ==============================
    // HÀM ĐẾM NGƯỢC
    // ==============================
    private void startCountdown() {

        int seconds;

        // Kiểm tra dữ liệu nhập
        try {

            seconds = Integer.parseInt(
                    txtSeconds.getText().trim()
            );

            if (seconds <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Số giây phải lớn hơn 0!",
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

        // Không cho nhấn nhiều lần trong lúc đang chạy
        btnStart.setEnabled(false);
        txtSeconds.setEnabled(false);

        // Lưu giá trị để dùng trong SwingWorker
        final int totalSeconds = seconds;

        // ==============================
        // SWINGWORKER
        // ==============================
        SwingWorker<Void, Integer> worker =
                new SwingWorker<>() {

                    // Chạy ở luồng nền
                    @Override
                    protected Void doInBackground()
                            throws Exception {

                        for (int i = totalSeconds; i >= 0; i--) {

                            // Gửi giá trị sang process()
                            publish(i);

                            // Không cần chờ thêm sau khi về 0
                            if (i > 0) {
                                Thread.sleep(1000);
                            }
                        }

                        return null;
                    }

                    // Cập nhật giao diện
                    @Override
                    protected void process(
                            List<Integer> chunks) {

                        int value =
                                chunks.get(
                                        chunks.size() - 1
                                );

                        lblTime.setText(
                                "Thời gian còn lại: "
                                        + value
                                        + " giây"
                        );
                    }

                    // Chạy khi đếm xong
                    @Override
                    protected void done() {

                        btnStart.setEnabled(true);
                        txtSeconds.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                bai1.this,
                                "Đếm ngược hoàn thành!",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        txtSeconds.requestFocus();
                    }
                };

        // Bắt đầu SwingWorker
        worker.execute();
    }

    // ==============================
    // MAIN
    // ==============================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai1 frame =
                    new bai1();

            frame.setVisible(true);
        });
    }
}