package lab4;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.List;

public class bai7 extends JFrame {

    private JButton btnChooseFile;
    private JButton btnSearch;

    private JTextField txtKeyword;

    private JLabel lblFile;
    private JLabel lblResult;

    private JTextArea txtAreaResult;

    private File selectedFile;

    public bai7() {

        // =====================================
        // THIẾT LẬP CỬA SỔ
        // =====================================
        setTitle("Bài 7 - Tìm kiếm từ khóa trong file");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // =====================================
        // TIÊU ĐỀ
        // =====================================
        JLabel lblTitle = new JLabel(
                "TÌM KIẾM TỪ KHÓA TRONG FILE VĂN BẢN",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // =====================================
        // NÚT CHỌN FILE
        // =====================================
        btnChooseFile = new JButton("Chọn file .txt");

        btnChooseFile.setPreferredSize(
                new Dimension(130, 35)
        );

        lblFile = new JLabel(
                "Chưa chọn file"
        );

        JPanel filePanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        filePanel.add(btnChooseFile);
        filePanel.add(lblFile);

        // =====================================
        // NHẬP TỪ KHÓA
        // =====================================
        JLabel lblKeyword = new JLabel(
                "Từ khóa:"
        );

        txtKeyword = new JTextField(25);

        btnSearch = new JButton("Tìm");

        btnSearch.setPreferredSize(
                new Dimension(90, 35)
        );

        JPanel searchPanel = new JPanel();

        searchPanel.add(lblKeyword);
        searchPanel.add(txtKeyword);
        searchPanel.add(btnSearch);

        // =====================================
        // LABEL KẾT QUẢ
        // =====================================
        lblResult = new JLabel(
                "Số dòng tìm thấy: 0",
                SwingConstants.CENTER
        );

        lblResult.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        // =====================================
        // TEXTAREA HIỂN THỊ KẾT QUẢ
        // =====================================
        txtAreaResult = new JTextArea();

        txtAreaResult.setEditable(false);

        txtAreaResult.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        txtAreaResult.setLineWrap(true);

        txtAreaResult.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(txtAreaResult);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Các dòng chứa từ khóa"
                )
        );

        // =====================================
        // PANEL PHÍA TRÊN
        // =====================================
        JPanel topPanel = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        topPanel.add(lblTitle);
        topPanel.add(filePanel);
        topPanel.add(searchPanel);
        topPanel.add(lblResult);

        // =====================================
        // PANEL CHÍNH
        // =====================================
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 20, 20
                )
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =====================================
        // SỰ KIỆN
        // =====================================
        btnChooseFile.addActionListener(
                e -> chooseFile()
        );

        btnSearch.addActionListener(
                e -> searchKeyword()
        );
    }

    // =========================================
    // CHỌN FILE
    // =========================================
    private void chooseFile() {

        JFileChooser chooser =
                new JFileChooser();

        // Chỉ cho chọn file txt
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                        "Text files (*.txt)",
                        "txt"
                );

        chooser.setFileFilter(filter);

        int result =
                chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            selectedFile =
                    chooser.getSelectedFile();

            lblFile.setText(
                    "File: "
                            + selectedFile.getAbsolutePath()
            );

            // Reset kết quả cũ
            txtAreaResult.setText("");

            lblResult.setText(
                    "Số dòng tìm thấy: 0"
            );
        }
    }

    // =========================================
    // TÌM TỪ KHÓA
    // =========================================
    private void searchKeyword() {

        // Kiểm tra đã chọn file chưa
        if (selectedFile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn file .txt trước!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Lấy từ khóa
        String keyword =
                txtKeyword.getText().trim();

        // Kiểm tra từ khóa rỗng
        if (keyword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập từ khóa cần tìm!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            txtKeyword.requestFocus();

            return;
        }

        // Reset kết quả
        txtAreaResult.setText("");

        lblResult.setText(
                "Đang tìm kiếm..."
        );

        // Khóa các nút khi đang xử lý
        btnSearch.setEnabled(false);
        btnChooseFile.setEnabled(false);
        txtKeyword.setEnabled(false);

        // Chuyển keyword thành chữ thường
        final String searchWord =
                keyword.toLowerCase();

        // =====================================
        // SWINGWORKER
        // =====================================
        SwingWorker<Integer, String> worker =
                new SwingWorker<>() {

                    @Override
                    protected Integer doInBackground()
                            throws Exception {

                        int count = 0;

                        int lineNumber = 0;

                        // Đọc file UTF-8
                        try (
                            BufferedReader reader =
                                Files.newBufferedReader(
                                    selectedFile.toPath(),
                                    StandardCharsets.UTF_8
                                )
                        ) {

                            String line;

                            while (
                                (line = reader.readLine())
                                        != null
                            ) {

                                lineNumber++;

                                // Không phân biệt hoa/thường
                                if (
                                    line.toLowerCase()
                                        .contains(searchWord)
                                ) {

                                    count++;

                                    // Gửi dòng tìm thấy về process()
                                    publish(
                                        "Dòng "
                                        + lineNumber
                                        + ": "
                                        + line
                                    );
                                }
                            }
                        }

                        return count;
                    }

                    // =================================
                    // CẬP NHẬT TEXTAREA
                    // =================================
                    @Override
                    protected void process(
                            List<String> chunks) {

                        for (String line : chunks) {

                            txtAreaResult.append(
                                    line + "\n"
                            );
                        }
                    }

                    // =================================
                    // KHI TÌM KIẾM XONG
                    // =================================
                    @Override
                    protected void done() {

                        try {

                            int count = get();

                            lblResult.setText(
                                    "Số dòng tìm thấy: "
                                            + count
                            );

                            // Nếu không tìm thấy
                            if (count == 0) {

                                txtAreaResult.setText(
                                        "Không tìm thấy dòng nào chứa từ khóa \""
                                                + keyword
                                                + "\"."
                                );
                            }

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Có lỗi khi đọc file!"
                            );

                            JOptionPane.showMessageDialog(
                                    bai7.this,
                                    "Không thể đọc file!\n"
                                            + ex.getMessage(),
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                        // Bật lại giao diện
                        btnSearch.setEnabled(true);

                        btnChooseFile.setEnabled(true);

                        txtKeyword.setEnabled(true);
                    }
                };

        // Chạy SwingWorker
        worker.execute();
    }

    // =========================================
    // MAIN
    // =========================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai7 frame = new bai7();

            frame.setVisible(true);
        });
    }
}