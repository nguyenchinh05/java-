package lab4;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.List;

public class bai8 extends JFrame {

    // =============================
    // CÁC THÀNH PHẦN GIAO DIỆN
    // =============================
    private JButton btnChooseFile;
    private JButton btnReadFile;

    private JLabel lblFile;
    private JLabel lblAverage;
    private JLabel lblHighest;
    private JLabel lblStatus;

    private JTable table;
    private DefaultTableModel tableModel;

    private JProgressBar progressBar;

    // File CSV đã chọn
    private File selectedFile;

    // =============================
    // CONSTRUCTOR
    // =============================
    public bai8() {

        // =============================
        // THIẾT LẬP JFRAME
        // =============================
        setTitle("Bài 8 - Đọc file CSV điểm sinh viên");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // =============================
        // TIÊU ĐỀ
        // =============================
        JLabel lblTitle = new JLabel(
                "ĐỌC FILE CSV ĐIỂM SINH VIÊN",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        // =============================
        // NÚT CHỌN FILE
        // =============================
        btnChooseFile = new JButton("Chọn file CSV");

        btnChooseFile.setPreferredSize(
                new Dimension(130, 35)
        );

        btnReadFile = new JButton("Đọc dữ liệu");

        btnReadFile.setPreferredSize(
                new Dimension(130, 35)
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnChooseFile);
        buttonPanel.add(btnReadFile);

        // =============================
        // HIỂN THỊ ĐƯỜNG DẪN FILE
        // =============================
        lblFile = new JLabel(
                "File: Chưa chọn file",
                SwingConstants.CENTER
        );

        // =============================
        // PANEL PHÍA TRÊN
        // =============================
        JPanel topPanel = new JPanel(
                new GridLayout(3, 1, 5, 5)
        );

        topPanel.add(lblTitle);
        topPanel.add(buttonPanel);
        topPanel.add(lblFile);

        // =============================
        // TABLE
        // =============================
        String[] columns = {
                "STT",
                "Mã sinh viên",
                "Họ tên",
                "Điểm"
        };

        tableModel = new DefaultTableModel(
                columns,
                0
        ) {

            // Không cho sửa trực tiếp trong JTable
            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        table = new JTable(tableModel);

        table.setRowHeight(28);

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        // Độ rộng cột
        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(130);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(300);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(100);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Danh sách sinh viên"
                )
        );

        // =============================
        // PROGRESS BAR
        // =============================
        progressBar = new JProgressBar();

        progressBar.setIndeterminate(false);

        progressBar.setStringPainted(true);

        progressBar.setString("Chưa xử lý");

        // =============================
        // THỐNG KÊ
        // =============================
        lblAverage = new JLabel(
                "Điểm trung bình: 0",
                SwingConstants.CENTER
        );

        lblHighest = new JLabel(
                "Sinh viên điểm cao nhất: Chưa có",
                SwingConstants.CENTER
        );

        lblStatus = new JLabel(
                "Trạng thái: Chưa đọc dữ liệu",
                SwingConstants.CENTER
        );

        lblAverage.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblHighest.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        JPanel statisticPanel = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        statisticPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Thống kê"
                )
        );

        statisticPanel.add(progressBar);
        statisticPanel.add(lblAverage);
        statisticPanel.add(lblHighest);
        statisticPanel.add(lblStatus);

        // =============================
        // PANEL CHÍNH
        // =============================
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        20,
                        20
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

        mainPanel.add(
                statisticPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =============================
        // SỰ KIỆN
        // =============================
        btnChooseFile.addActionListener(
                e -> chooseFile()
        );

        btnReadFile.addActionListener(
                e -> readCSV()
        );
    }

    // =====================================
    // CHỌN FILE CSV
    // =====================================
    private void chooseFile() {

        JFileChooser chooser =
                new JFileChooser();

        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                        "CSV files (*.csv)",
                        "csv"
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

            // Reset dữ liệu cũ
            tableModel.setRowCount(0);

            lblAverage.setText(
                    "Điểm trung bình: 0"
            );

            lblHighest.setText(
                    "Sinh viên điểm cao nhất: Chưa có"
            );

            lblStatus.setText(
                    "Trạng thái: Đã chọn file"
            );

            progressBar.setIndeterminate(false);
            progressBar.setValue(0);
            progressBar.setString("0%");
        }
    }

    // =====================================
    // ĐỌC FILE CSV
    // =====================================
    private void readCSV() {

        // Kiểm tra file
        if (selectedFile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn file CSV trước!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        lblAverage.setText(
                "Điểm trung bình: Đang tính..."
        );

        lblHighest.setText(
                "Sinh viên điểm cao nhất: Đang tìm..."
        );

        lblStatus.setText(
                "Trạng thái: Đang đọc file..."
        );

        // Khóa nút
        btnChooseFile.setEnabled(false);
        btnReadFile.setEnabled(false);

        // Do không biết chính xác số dòng trước khi đọc
        // nên cho progress chạy liên tục
        progressBar.setIndeterminate(true);

        progressBar.setString(
                "Đang đọc dữ liệu..."
        );

        // Lưu file hiện tại
        final File fileToRead = selectedFile;

        // =====================================
        // SWINGWORKER
        // =====================================
        SwingWorker<ThongKe, Object[]> worker =
                new SwingWorker<>() {

                    @Override
                    protected ThongKe doInBackground()
                            throws Exception {

                        int count = 0;

                        double totalScore = 0;

                        double highestScore =
                                Double.NEGATIVE_INFINITY;

                        String highestMaSV = "";

                        String highestName = "";

                        try (
                            BufferedReader reader =
                                Files.newBufferedReader(
                                    fileToRead.toPath(),
                                    StandardCharsets.UTF_8
                                )
                        ) {

                            String line;

                            // =========================
                            // BỎ QUA DÒNG TIÊU ĐỀ
                            // MaSV,HoTen,Diem
                            // =========================
                            String header =
                                    reader.readLine();

                            // Nếu file trống
                            if (header == null) {

                                return new ThongKe(
                                        0,
                                        0,
                                        "",
                                        "",
                                        0
                                );
                            }

                            // =========================
                            // ĐỌC TỪNG DÒNG
                            // =========================
                            while (
                                (line = reader.readLine())
                                        != null
                            ) {

                                // Bỏ qua dòng trống
                                if (line.trim().isEmpty()) {
                                    continue;
                                }

                                /*
                                 * CSV dạng:
                                 *
                                 * SV01,Nguyen Van A,8.5
                                 *
                                 * split thành tối đa 3 phần
                                 */
                                String[] data =
                                        line.split(",", 3);

                                // Kiểm tra dữ liệu
                                if (data.length != 3) {
                                    continue;
                                }

                                String maSV =
                                        data[0].trim();

                                String hoTen =
                                        data[1].trim();

                                double diem;

                                try {

                                    diem =
                                        Double.parseDouble(
                                            data[2].trim()
                                        );

                                } catch (
                                    NumberFormatException ex
                                ) {

                                    // Dòng bị sai điểm
                                    // thì bỏ qua
                                    continue;
                                }

                                count++;

                                totalScore += diem;

                                // =====================
                                // TÌM ĐIỂM CAO NHẤT
                                // =====================
                                if (diem > highestScore) {

                                    highestScore = diem;

                                    highestMaSV = maSV;

                                    highestName = hoTen;
                                }

                                // =====================
                                // GỬI DỮ LIỆU VỀ TABLE
                                // =====================
                                publish(
                                    new Object[]{
                                        count,
                                        maSV,
                                        hoTen,
                                        diem
                                    }
                                );
                            }
                        }

                        // =========================
                        // TÍNH ĐIỂM TRUNG BÌNH
                        // =========================
                        double average = 0;

                        if (count > 0) {

                            average =
                                    totalScore / count;
                        }

                        return new ThongKe(
                                count,
                                average,
                                highestMaSV,
                                highestName,
                                highestScore
                        );
                    }

                    // =================================
                    // ĐƯA SINH VIÊN LÊN JTABLE
                    // =================================
                    @Override
                    protected void process(
                            List<Object[]> chunks) {

                        for (Object[] row : chunks) {

                            tableModel.addRow(row);
                        }
                    }

                    // =================================
                    // KHI ĐỌC FILE XONG
                    // =================================
                    @Override
                    protected void done() {

                        try {

                            ThongKe thongKe = get();

                            // Không có sinh viên
                            if (thongKe.soLuong == 0) {

                                lblAverage.setText(
                                        "Điểm trung bình: 0"
                                );

                                lblHighest.setText(
                                        "Sinh viên điểm cao nhất: Không có"
                                );

                                lblStatus.setText(
                                        "Trạng thái: File không có dữ liệu sinh viên"
                                );

                            } else {

                                // Điểm trung bình
                                lblAverage.setText(
                                        String.format(
                                            "Điểm trung bình: %.2f",
                                            thongKe.diemTrungBinh
                                        )
                                );

                                // Sinh viên cao nhất
                                lblHighest.setText(
                                        "Sinh viên điểm cao nhất: "
                                                + thongKe.maSVCaoNhat
                                                + " - "
                                                + thongKe.hoTenCaoNhat
                                                + " - Điểm: "
                                                + thongKe.diemCaoNhat
                                );

                                lblStatus.setText(
                                        "Trạng thái: Đọc thành công "
                                                + thongKe.soLuong
                                                + " sinh viên"
                                );
                            }

                        } catch (Exception ex) {

                            lblStatus.setText(
                                    "Trạng thái: Có lỗi khi đọc file!"
                            );

                            JOptionPane.showMessageDialog(
                                    bai8.this,
                                    "Không thể đọc file CSV!\n"
                                            + ex.getMessage(),
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                        // Dừng progress
                        progressBar.setIndeterminate(false);

                        progressBar.setValue(100);

                        progressBar.setString("100%");

                        // Bật lại nút
                        btnChooseFile.setEnabled(true);
                        btnReadFile.setEnabled(true);
                    }
                };

        // Chạy SwingWorker
        worker.execute();
    }

    // =====================================
    // CLASS LƯU KẾT QUẢ THỐNG KÊ
    // =====================================
    private static class ThongKe {

        int soLuong;

        double diemTrungBinh;

        String maSVCaoNhat;

        String hoTenCaoNhat;

        double diemCaoNhat;

        public ThongKe(
                int soLuong,
                double diemTrungBinh,
                String maSVCaoNhat,
                String hoTenCaoNhat,
                double diemCaoNhat) {

            this.soLuong = soLuong;

            this.diemTrungBinh =
                    diemTrungBinh;

            this.maSVCaoNhat =
                    maSVCaoNhat;

            this.hoTenCaoNhat =
                    hoTenCaoNhat;

            this.diemCaoNhat =
                    diemCaoNhat;
        }
    }

    // =====================================
    // MAIN
    // =====================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai8 frame = new bai8();

            frame.setVisible(true);
        });
    }
}