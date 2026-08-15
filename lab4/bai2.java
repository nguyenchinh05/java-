package lab4;

import javax.swing.*;
import java.awt.*;

public class bai2 extends JFrame {

    private JButton btnLoad;
    private JProgressBar progressBar;
    private JLabel lblStatus;

    public bai2() {

        // ==========================
        // THIẾT LẬP CỬA SỔ
        // ==========================
        setTitle("Bài 2 - Mô phỏng tải dữ liệu");
        setSize(450, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "MÔ PHỎNG TẢI DỮ LIỆU",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // ==========================
        // NÚT TẢI DỮ LIỆU
        // ==========================
        btnLoad = new JButton("Tải dữ liệu");

        btnLoad.setPreferredSize(
                new Dimension(130, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnLoad);

        // ==========================
        // PROGRESS BAR
        // ==========================
        progressBar = new JProgressBar(0, 100);

        progressBar.setValue(0);

        // Hiển thị %
        progressBar.setStringPainted(true);

        progressBar.setPreferredSize(
                new Dimension(350, 30)
        );

        JPanel progressPanel = new JPanel();

        progressPanel.add(progressBar);

        // ==========================
        // LABEL TRẠNG THÁI
        // ==========================
        lblStatus = new JLabel(
                "Chưa tải dữ liệu",
                SwingConstants.CENTER
        );

        lblStatus.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        // ==========================
        // PANEL CHÍNH
        // ==========================
        JPanel panel = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        panel.add(lblTitle);
        panel.add(buttonPanel);
        panel.add(progressPanel);
        panel.add(lblStatus);

        add(panel);

        // ==========================
        // SỰ KIỆN BUTTON
        // ==========================
        btnLoad.addActionListener(
                e -> loadData()
        );
    }

    // =================================
    // HÀM TẢI DỮ LIỆU
    // =================================
    private void loadData() {

        // Không cho bấm nhiều lần
        btnLoad.setEnabled(false);

        // Đưa progress về 0
        progressBar.setValue(0);

        // Trạng thái
        lblStatus.setText(
                "Đang tải dữ liệu..."
        );

        // =================================
        // SWINGWORKER
        // =================================
        SwingWorker<Void, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Void doInBackground()
                            throws Exception {

                        // Tăng từ 0 -> 100
                        for (int i = 0; i <= 100; i += 10) {

                            // Cập nhật progress
                            setProgress(i);

                            // Mô phỏng tải dữ liệu
                            // mỗi lần chờ 1 giây
                            if (i < 100) {
                                Thread.sleep(1000);
                            }
                        }

                        return null;
                    }

                    // Khi hoàn thành
                    @Override
                    protected void done() {

                        progressBar.setValue(100);

                        lblStatus.setText(
                                "Tải dữ liệu hoàn tất!"
                        );

                        btnLoad.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                bai2.this,
                                "Tải dữ liệu thành công!",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                };

        // =================================
        // THEO DÕI PROGRESS
        // =================================
        worker.addPropertyChangeListener(evt -> {

            if ("progress".equals(
                    evt.getPropertyName())) {

                int value =
                        (int) evt.getNewValue();

                progressBar.setValue(value);

                lblStatus.setText(
                        "Đang tải dữ liệu... "
                                + value
                                + "%"
                );
            }
        });

        // Chạy SwingWorker
        worker.execute();
    }

    // =================================
    // MAIN
    // =================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai2 frame = new bai2();

            frame.setVisible(true);
        });
    }
}