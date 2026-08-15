package lab4;

import javax.swing.*;
import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class bai5 extends JFrame {

    private JButton btnChoose;
    private JButton btnCount;

    private JLabel lblFile;
    private JLabel lblResult;

    private JProgressBar progressBar;

    private File selectedFile;

    public bai5() {

        // ==========================
        // THIẾT LẬP CỬA SỔ
        // ==========================
        setTitle("Bài 5 - Đếm số dòng trong file");
        setSize(650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ==========================
        // TIÊU ĐỀ
        // ==========================
        JLabel lblTitle = new JLabel(
                "ĐỌC FILE VÀ ĐẾM SỐ DÒNG",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // ==========================
        // NÚT CHỌN FILE
        // ==========================
        btnChoose = new JButton("Chọn file");

        btnChoose.setPreferredSize(
                new Dimension(120, 35)
        );

        // ==========================
        // NÚT ĐẾM DÒNG
        // ==========================
        btnCount = new JButton("Đếm dòng");

        btnCount.setPreferredSize(
                new Dimension(120, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnChoose);
        buttonPanel.add(btnCount);

        // ==========================
        // LABEL HIỂN THỊ FILE
        // ==========================
        lblFile = new JLabel(
                "File: Chưa chọn file",
                SwingConstants.CENTER
        );

        // ==========================
        // PROGRESS BAR
        // ==========================
        progressBar = new JProgressBar(0, 100);

        progressBar.setValue(0);

        progressBar.setStringPainted(true);

        progressBar.setPreferredSize(
                new Dimension(500, 25)
        );

        JPanel progressPanel = new JPanel();

        progressPanel.add(progressBar);

        // ==========================
        // LABEL KẾT QUẢ
        // ==========================
        lblResult = new JLabel(
                "Số dòng: 0",
                SwingConstants.CENTER
        );

        lblResult.setFont(
                new Font("Arial", Font.BOLD, 18)
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
        mainPanel.add(buttonPanel);
        mainPanel.add(lblFile);
        mainPanel.add(progressPanel);
        mainPanel.add(lblResult);

        add(mainPanel);

        // ==========================
        // SỰ KIỆN
        // ==========================
        btnChoose.addActionListener(
                e -> chooseFile()
        );

        btnCount.addActionListener(
                e -> countLines()
        );
    }

    // =================================
    // CHỌN FILE
    // =================================
    private void chooseFile() {

        JFileChooser chooser =
                new JFileChooser();

        int result =
                chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            selectedFile =
                    chooser.getSelectedFile();

            lblFile.setText(
                    "File: "
                            + selectedFile.getAbsolutePath()
            );

            lblResult.setText(
                    "Số dòng: Chưa đếm"
            );

            progressBar.setValue(0);
        }
    }

    // =================================
    // ĐẾM SỐ DÒNG
    // =================================
    private void countLines() {

        // Kiểm tra đã chọn file chưa
        if (selectedFile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn file trước!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Không cho bấm trong lúc đang chạy
        btnCount.setEnabled(false);
        btnChoose.setEnabled(false);

        progressBar.setValue(0);

        lblResult.setText(
                "Đang đọc file..."
        );

        // =================================
        // SWINGWORKER
        // =================================
        SwingWorker<Long, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Long doInBackground()
                            throws Exception {

                        // Tổng kích thước file
                        long totalBytes =
                                Files.size(
                                        selectedFile.toPath()
                                );

                        long readBytes = 0;
                        long lines = 0;

                        // Mở file UTF-8
                        try (
                            BufferedReader reader =
                                Files.newBufferedReader(
                                    selectedFile.toPath(),
                                    StandardCharsets.UTF_8
                                )
                        ) {

                            String line;

                            while (
                                (line = reader.readLine()) != null
                            ) {

                                // Tăng số dòng
                                lines++;

                                // Ước lượng số byte đã đọc
                                readBytes +=
                                        line.getBytes(
                                            StandardCharsets.UTF_8
                                        ).length + 1;

                                // Tính %
                                int progress;

                                if (totalBytes == 0) {

                                    progress = 100;

                                } else {

                                    progress =
                                            (int) Math.min(
                                                100,
                                                readBytes * 100
                                                        / totalBytes
                                            );
                                }

                                // Cập nhật tiến độ
                                setProgress(progress);
                            }
                        }

                        return lines;
                    }

                    // ==========================
                    // KHI HOÀN THÀNH
                    // ==========================
                    @Override
                    protected void done() {

                        try {

                            long lineCount = get();

                            lblResult.setText(
                                    "Số dòng: "
                                            + lineCount
                            );

                            JOptionPane.showMessageDialog(
                                    bai5.this,
                                    "Đếm dòng hoàn tất!\n"
                                            + "Tổng số dòng: "
                                            + lineCount,
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Lỗi khi đọc file!"
                            );

                            JOptionPane.showMessageDialog(
                                    bai5.this,
                                    "Không thể đọc file!\n"
                                            + ex.getMessage(),
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                        progressBar.setValue(100);

                        btnCount.setEnabled(true);
                        btnChoose.setEnabled(true);
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
                    }
                }
        );

        // Chạy SwingWorker
        worker.execute();
    }

    // =================================
    // MAIN
    // =================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai5 frame = new bai5();

            frame.setVisible(true);
        });
    }
}