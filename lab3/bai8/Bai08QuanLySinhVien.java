package lab3.bai8;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Bai08QuanLySinhVien extends JFrame {

    private JTextField txtMaSV;
    private JTextField txtHoTen;
    private JTextField txtDiemTB;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private JTable table;

    private studentTableModel tableModel;

    public Bai08QuanLySinhVien() {

        // ==============================
        // THIẾT LẬP JFRAME
        // ==============================
        setTitle("Quản lý sinh viên");
        setSize(850, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        // ==============================
        // PANEL CHÍNH
        // ==============================
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

        // ==============================
        // TIÊU ĐỀ
        // ==============================
        JLabel lblTitle = new JLabel(
                "QUẢN LÝ SINH VIÊN",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        // ==============================
        // PANEL NHẬP THÔNG TIN
        // ==============================
        JPanel inputPanel =
                new JPanel(
                        new GridBagLayout()
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Thông tin sinh viên"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        10,
                        8,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ------------------------------
        // MÃ SINH VIÊN
        // ------------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputPanel.add(
                new JLabel("Mã sinh viên:"),
                gbc
        );

        gbc.gridx = 1;

        txtMaSV =
                new JTextField(18);

        inputPanel.add(
                txtMaSV,
                gbc
        );

        // ------------------------------
        // HỌ TÊN
        // ------------------------------
        gbc.gridx = 2;

        inputPanel.add(
                new JLabel("Họ tên:"),
                gbc
        );

        gbc.gridx = 3;

        txtHoTen =
                new JTextField(18);

        inputPanel.add(
                txtHoTen,
                gbc
        );

        // ------------------------------
        // ĐIỂM TRUNG BÌNH
        // ------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;

        inputPanel.add(
                new JLabel("Điểm trung bình:"),
                gbc
        );

        gbc.gridx = 1;

        txtDiemTB =
                new JTextField(18);

        inputPanel.add(
                txtDiemTB,
                gbc
        );

        // ==============================
        // CÁC NÚT
        // ==============================
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        btnThem =
                new JButton("Thêm");

        btnSua =
                new JButton("Sửa");

        btnXoa =
                new JButton("Xóa");

        btnLamMoi =
                new JButton("Làm mới");

        Dimension buttonSize =
                new Dimension(
                        100,
                        35
                );

        btnThem.setPreferredSize(buttonSize);
        btnSua.setPreferredSize(buttonSize);
        btnXoa.setPreferredSize(buttonSize);
        btnLamMoi.setPreferredSize(buttonSize);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);

        // Panel trên
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // ==============================
        // JTABLE
        // ==============================
        tableModel =
                new studentTableModel();

        table =
                new JTable(tableModel);

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // Không cho sửa trực tiếp trên JTable
        table.setDefaultEditor(
                Object.class,
                null
        );

        // Căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        table.getColumnModel()
                .getColumn(0)
                .setCellRenderer(centerRenderer);

        table.getColumnModel()
                .getColumn(1)
                .setCellRenderer(centerRenderer);

        table.getColumnModel()
                .getColumn(3)
                .setCellRenderer(centerRenderer);

        table.getColumnModel()
                .getColumn(4)
                .setCellRenderer(centerRenderer);

        // Chỉnh độ rộng cột
        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(40);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(250);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(130);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(120);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Danh sách sinh viên"
                )
        );

        // ==============================
        // CENTER
        // ==============================
        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // ==============================
        // SỰ KIỆN
        // ==============================

        btnThem.addActionListener(
                e -> themSinhVien()
        );

        btnSua.addActionListener(
                e -> suaSinhVien()
        );

        btnXoa.addActionListener(
                e -> xoaSinhVien()
        );

        btnLamMoi.addActionListener(
                e -> lamMoi()
        );

        // Khi click vào JTable
        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        hienThiSinhVien();
                    }
                });
    }

    // ====================================
    // THÊM SINH VIÊN
    // ====================================
    private void themSinhVien() {

        try {

            String maSV =
                    txtMaSV.getText().trim();

            String hoTen =
                    txtHoTen.getText().trim();

            String diemText =
                    txtDiemTB.getText().trim();

            // Kiểm tra rỗng
            if (maSV.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập mã sinh viên!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                txtMaSV.requestFocus();

                return;
            }

            if (hoTen.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập họ tên!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                txtHoTen.requestFocus();

                return;
            }

            if (diemText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập điểm trung bình!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                txtDiemTB.requestFocus();

                return;
            }

            // Kiểm tra trùng mã
            if (tableModel.existsMaSV(maSV)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Mã sinh viên đã tồn tại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Chuyển điểm sang số
            double diemTB =
                    Double.parseDouble(diemText);

            // Kiểm tra điểm
            if (diemTB < 0 || diemTB > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "Điểm trung bình phải từ 0 đến 10!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Tạo sinh viên
            student student =
                    new student(
                            maSV,
                            hoTen,
                            diemTB
                    );

            // Thêm vào bảng
            tableModel.addStudent(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Thêm sinh viên thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            lamMoi();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Điểm trung bình phải là số!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ====================================
    // SỬA SINH VIÊN
    // ====================================
    private void suaSinhVien() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn sinh viên cần sửa!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            String maSV =
                    txtMaSV.getText().trim();

            String hoTen =
                    txtHoTen.getText().trim();

            String diemText =
                    txtDiemTB.getText().trim();

            if (maSV.isEmpty()
                    || hoTen.isEmpty()
                    || diemText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập đầy đủ thông tin!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Kiểm tra trùng mã với sinh viên khác
            if (tableModel.existsMaSVExceptRow(
                    maSV,
                    row
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Mã sinh viên đã tồn tại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            double diemTB =
                    Double.parseDouble(diemText);

            if (diemTB < 0 || diemTB > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "Điểm trung bình phải từ 0 đến 10!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            student student =
                    new student(
                            maSV,
                            hoTen,
                            diemTB
                    );

            tableModel.updateStudent(
                    row,
                    student
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật sinh viên thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            lamMoi();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Điểm trung bình phải là số!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ====================================
    // XÓA SINH VIÊN
    // ====================================
    private void xoaSinhVien() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn sinh viên cần xóa!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        student student =
                tableModel.getStudent(row);

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc muốn xóa sinh viên "
                                + student.getHoTen()
                                + "?",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm == JOptionPane.YES_OPTION) {

            tableModel.removeStudent(row);

            JOptionPane.showMessageDialog(
                    this,
                    "Xóa sinh viên thành công!"
            );

            lamMoi();
        }
    }

    // ====================================
    // HIỂN THỊ KHI CLICK TABLE
    // ====================================
    private void hienThiSinhVien() {

        int row =
                table.getSelectedRow();

        if (row != -1) {

            student student =
                    tableModel.getStudent(row);

            txtMaSV.setText(
                    student.getMaSV()
            );

            txtHoTen.setText(
                    student.getHoTen()
            );

            txtDiemTB.setText(
                    String.valueOf(
                            student.getDiemTB()
                    )
            );
        }
    }

    // ====================================
    // LÀM MỚI
    // ====================================
    private void lamMoi() {

        txtMaSV.setText("");
        txtHoTen.setText("");
        txtDiemTB.setText("");

        table.clearSelection();

        txtMaSV.requestFocus();
    }

    // ====================================
    // MAIN
    // ====================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Bai08QuanLySinhVien form =
                    new Bai08QuanLySinhVien();

            form.setVisible(true);
        });
    }
}