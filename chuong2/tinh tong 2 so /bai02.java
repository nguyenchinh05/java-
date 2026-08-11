import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class bai02 extends JFrame {

    private JTextField txtSo1, txtSo2;
    private JLabel lblKetQua;

    public bai02() {
        setTitle("Bài 2 - Tính tổng hai số");
        setSize(520, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Màu nền chính
        Color bgColor = new Color(245, 247, 250);
        Color panelColor = Color.WHITE;
        Color primaryColor = new Color(52, 152, 219);
        Color successColor = new Color(46, 204, 113);
        Color dangerColor = new Color(231, 76, 60);

        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font resultFont = new Font("Segoe UI", Font.BOLD, 18);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JLabel lblTitle = new JLabel("TÍNH TỔNG HAI SỐ", SwingConstants.CENTER);
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(primaryColor);
        lblTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Panel trung tâm
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Số thứ nhất
        JLabel lblSo1 = new JLabel("Số thứ nhất:");
        lblSo1.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        centerPanel.add(lblSo1, gbc);

        txtSo1 = new JTextField();
        txtSo1.setFont(textFont);
        txtSo1.setPreferredSize(new Dimension(220, 35));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        centerPanel.add(txtSo1, gbc);

        // Dòng 2: Số thứ hai
        JLabel lblSo2 = new JLabel("Số thứ hai:");
        lblSo2.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        centerPanel.add(lblSo2, gbc);

        txtSo2 = new JTextField();
        txtSo2.setFont(textFont);
        txtSo2.setPreferredSize(new Dimension(220, 35));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        centerPanel.add(txtSo2, gbc);

        // Dòng 3: Kết quả
        JLabel lblKQ = new JLabel("Kết quả:");
        lblKQ.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        centerPanel.add(lblKQ, gbc);

        lblKetQua = new JLabel("0");
        lblKetQua.setFont(resultFont);
        lblKetQua.setForeground(successColor);
        lblKetQua.setOpaque(true);
        lblKetQua.setBackground(new Color(240, 255, 244));
        lblKetQua.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 230, 201), 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        centerPanel.add(lblKetQua, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(bgColor);

        JButton btnTinh = new JButton("Tính tổng");
        JButton btnXoa = new JButton("Xóa");
        JButton btnThoat = new JButton("Thoát");

        styleButton(btnTinh, primaryColor, Color.WHITE, buttonFont);
        styleButton(btnXoa, successColor, Color.WHITE, buttonFont);
        styleButton(btnThoat, dangerColor, Color.WHITE, buttonFont);

        buttonPanel.add(btnTinh);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnThoat);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện nút Tính tổng
        btnTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tinhTong();
            }
        });

        // Sự kiện nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSo1.setText("");
                txtSo2.setText("");
                lblKetQua.setText("0");
                txtSo1.requestFocus();
            }
        });

        // Sự kiện nút Thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(mainPanel);
    }

    private void tinhTong() {
        try {
            double so1 = Double.parseDouble(txtSo1.getText().trim());
            double so2 = Double.parseDouble(txtSo2.getText().trim());
            double tong = so1 + so2;
            lblKetQua.setText(String.valueOf(tong));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đúng 2 số!",
                    "Lỗi nhập dữ liệu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void styleButton(JButton button, Color bg, Color fg, Font font) {
        button.setFont(font);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new bai02().setVisible(true);
            }
        });
    }
}
