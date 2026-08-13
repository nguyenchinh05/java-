package lab3;

import javax.swing.*;
import java.awt.*;

public class bai7 extends JFrame {

    private JTextField txtSo1;
    private JTextField txtSo2;
    private JTextField txtKetQua;

    private JButton btnCong;
    private JButton btnTru;
    private JButton btnNhan;
    private JButton btnChia;
    private JButton btnClear;

    private JTextArea txtLichSu;

    public bai7() {

        // =========================
        // THIẾT LẬP CỬA SỔ
        // =========================
        setTitle("Máy tính mini");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // =========================
        // TIÊU ĐỀ
        // =========================
        JLabel lblTitle = new JLabel(
                "MÁY TÍNH MINI",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // =========================
        // PANEL NHẬP DỮ LIỆU
        // =========================
        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Số thứ nhất
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputPanel.add(
                new JLabel("Số thứ nhất:"),
                gbc
        );

        gbc.gridx = 1;

        txtSo1 = new JTextField(15);

        inputPanel.add(txtSo1, gbc);

        // Số thứ hai
        gbc.gridx = 0;
        gbc.gridy = 1;

        inputPanel.add(
                new JLabel("Số thứ hai:"),
                gbc
        );

        gbc.gridx = 1;

        txtSo2 = new JTextField(15);

        inputPanel.add(txtSo2, gbc);

        // Kết quả
        gbc.gridx = 0;
        gbc.gridy = 2;

        inputPanel.add(
                new JLabel("Kết quả:"),
                gbc
        );

        gbc.gridx = 1;

        txtKetQua = new JTextField(15);

        // Không cho sửa kết quả
        txtKetQua.setEditable(false);

        inputPanel.add(txtKetQua, gbc);

        // =========================
        // CÁC NÚT PHÉP TÍNH
        // =========================
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 8, 10)
        );

        btnCong = new JButton("Cộng");
        btnTru = new JButton("Trừ");
        btnNhan = new JButton("Nhân");
        btnChia = new JButton("Chia");
        btnClear = new JButton("Clear");

        buttonPanel.add(btnCong);
        buttonPanel.add(btnTru);
        buttonPanel.add(btnNhan);
        buttonPanel.add(btnChia);
        buttonPanel.add(btnClear);

        // Panel chứa phần nhập + button
        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        // =========================
        // LỊCH SỬ PHÉP TÍNH
        // =========================
        JPanel historyPanel = new JPanel(new BorderLayout());

        historyPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Lịch sử phép tính"
                )
        );

        txtLichSu = new JTextArea(8, 35);

        txtLichSu.setEditable(false);

        txtLichSu.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(txtLichSu);

        historyPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                historyPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =========================
        // SỰ KIỆN CỘNG
        // =========================
        btnCong.addActionListener(e -> tinhToan("+"));

        // =========================
        // SỰ KIỆN TRỪ
        // =========================
        btnTru.addActionListener(e -> tinhToan("-"));

        // =========================
        // SỰ KIỆN NHÂN
        // =========================
        btnNhan.addActionListener(e -> tinhToan("*"));

        // =========================
        // SỰ KIỆN CHIA
        // =========================
        btnChia.addActionListener(e -> tinhToan("/"));

        // =========================
        // SỰ KIỆN CLEAR
        // =========================
        btnClear.addActionListener(e -> {

            txtSo1.setText("");
            txtSo2.setText("");
            txtKetQua.setText("");

            txtLichSu.setText("");

            txtSo1.requestFocus();
        });
    }

    // =========================
    // HÀM TÍNH TOÁN
    // =========================
    private void tinhToan(String phepTinh) {

        try {

            // Lấy dữ liệu
            double so1 =
                    Double.parseDouble(
                            txtSo1.getText().trim()
                    );

            double so2 =
                    Double.parseDouble(
                            txtSo2.getText().trim()
                    );

            double ketQua;

            // Kiểm tra phép tính
            switch (phepTinh) {

                case "+":
                    ketQua = so1 + so2;
                    break;

                case "-":
                    ketQua = so1 - so2;
                    break;

                case "*":
                    ketQua = so1 * so2;
                    break;

                case "/":

                    // Kiểm tra chia cho 0
                    if (so2 == 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Không thể chia cho 0!",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    ketQua = so1 / so2;
                    break;

                default:
                    return;
            }

            // Hiển thị kết quả
            txtKetQua.setText(
                    String.valueOf(ketQua)
            );

            // Thêm vào lịch sử
            String lichSu =
                    so1 + " "
                    + phepTinh + " "
                    + so2 + " = "
                    + ketQua;

            txtLichSu.append(
                    lichSu + "\n"
            );

        } catch (NumberFormatException e) {

            // Báo lỗi nhập sai số
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đúng định dạng số!",
                    "Lỗi nhập dữ liệu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai7 form = new bai7();

            form.setVisible(true);
        });
    }
}