package lab4;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CancellationException;

public class bai6 extends JFrame {

    private JButton btnStart;
    private JButton btnCancel;

    private JProgressBar progressBar;

    private JLabel lblStatus;

    // Lưu SwingWorker để nút Hủy có thể truy cập
    private SwingWorker<Void, Void> worker;

    public bai6() {

        // ==========================
        // THIẾT LẬP CỬA SỔ
        // ==========================
        setTitle("Bài 6 - Hủy tác vụ SwingWorker");
        setSize(500, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "MÔ PHỎNG TẢI DỮ LIỆU CÓ THỂ HỦY",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        // ==========================
        // BUTTON
        // ==========================
        btnStart = new JButton("Bắt đầu");

        btnCancel = new JButton("Hủy");

        // Ban đầu chưa có tác vụ nên không cho Hủy
        btnCancel.setEnabled(false);

        btnStart.setPreferredSize(
                new Dimension(120, 35)
        );

        btnCancel.setPreferredSize(
                new Dimension(120, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnStart);
        buttonPanel.add(btnCancel);

        // ==========================
        // PROGRESS BAR
        // ==========================
        progressBar = new JProgressBar(0, 100);

        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        progressBar.setPreferredSize(
                new Dimension(400, 30)
        );

        JPanel progressPanel = new JPanel();

        progressPanel.add(progressBar);

        // ==========================
        // LABEL TRẠNG THÁI
        // ==========================
        lblStatus = new JLabel(
                "Chưa bắt đầu",
                SwingConstants.CENTER
        );

        lblStatus.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        // ==========================
        // PANEL CHÍNH
        // ==========================
        JPanel mainPanel = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        mainPanel.add(lblTitle);
        mainPanel.add(buttonPanel);
        mainPanel.add(progressPanel);
        mainPanel.add(lblStatus);

        add(mainPanel);

        // ==========================
        // SỰ KIỆN
        // ==========================
        btnStart.addActionListener(
                e -> startTask()
        );

        btnCancel.addActionListener(
                e -> cancelTask()
        );
    }

    // =================================
    // BẮT ĐẦU TÁC VỤ
    // =================================
    private void startTask() {

        // Khóa nút bắt đầu
        btnStart.setEnabled(false);

        // Cho phép hủy
        btnCancel.setEnabled(true);

        // Reset progress
        progressBar.setValue(0);

        lblStatus.setText(
                "Đang xử lý..."
        );

        // =================================
        // TẠO SWINGWORKER
        // =================================
        worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground()
                    throws Exception {

                // Mô phỏng tải dữ liệu
                for (int i = 0; i <= 100; i += 10) {

                    // Kiểm tra người dùng đã hủy chưa
                    if (isCancelled()) {

                        return null;
                    }

                    // Cập nhật tiến độ
                    setProgress(i);

                    // Mô phỏng xử lý trong 1 giây
                    if (i < 100) {

                        try {

                            Thread.sleep(1000);

                        } catch (InterruptedException ex) {

                            // cancel(true) có thể làm
                            // Thread.sleep bị ngắt

                            if (isCancelled()) {
                                return null;
                            }

                            throw ex;
                        }
                    }
                }

                return null;
            }

            // ==========================
            // KHI TÁC VỤ KẾT THÚC
            // ==========================
            @Override
            protected void done() {

                // Nếu đã bị hủy
                if (isCancelled()) {

                    lblStatus.setText(
                            "Đã hủy tác vụ"
                    );

                } else {

                    try {

                        // Kiểm tra tác vụ hoàn tất bình thường
                        get();

                        progressBar.setValue(100);

                        lblStatus.setText(
                                "Tải dữ liệu hoàn tất!"
                        );

                    } catch (CancellationException ex) {

                        lblStatus.setText(
                                "Đã hủy tác vụ"
                        );

                    } catch (Exception ex) {

                        lblStatus.setText(
                                "Có lỗi xảy ra!"
                        );
                    }
                }

                // Cho phép chạy lại
                btnStart.setEnabled(true);

                // Không còn tác vụ để hủy
                btnCancel.setEnabled(false);
            }
        };

        // =================================
        // THEO DÕI PROGRESS
        // =================================
        worker.addPropertyChangeListener(
                evt -> {

                    if ("progress".equals(
                            evt.getPropertyName())) {

                        int value =
                                (int) evt.getNewValue();

                        progressBar.setValue(value);

                        lblStatus.setText(
                                "Đang xử lý... "
                                        + value
                                        + "%"
                        );
                    }
                }
        );

        // Chạy tác vụ
        worker.execute();
    }

    // =================================
    // HỦY TÁC VỤ
    // =================================
    private void cancelTask() {

        // Kiểm tra có worker đang chạy không
        if (worker != null
                && !worker.isDone()) {

            // true = cho phép ngắt thread đang chạy
            worker.cancel(true);

            lblStatus.setText(
                    "Đã hủy tác vụ"
            );

            btnCancel.setEnabled(false);
        }
    }

    // =================================
    // MAIN
    // =================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai6 frame = new bai6();

            frame.setVisible(true);
        });
    }
}