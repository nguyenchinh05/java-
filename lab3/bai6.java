package lab3;

import javax.swing.*;
import java.awt.*;

public class bai6 extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;
    private JCheckBox chkShowPassword;
    private JButton btnLogin;

    public bai6() {

        // ==============================
        // THIẾT LẬP CỬA SỔ
        // ==============================
        setTitle("Đăng nhập hệ thống");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 35, 25, 35)
        );

        // ==============================
        // TIÊU ĐỀ
        // ==============================
        JLabel lblTitle = new JLabel(
                "ĐĂNG NHẬP HỆ THỐNG",
                SwingConstants.CENTER
        );

        lblTitle.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ==============================
        // FORM ĐĂNG NHẬP
        // ==============================
        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ------------------------------
        // Tài khoản
        // ------------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblUsername = new JLabel("Tài khoản:");
        formPanel.add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        txtUsername = new JTextField(18);
        formPanel.add(txtUsername, gbc);

        // ------------------------------
        // Mật khẩu
        // ------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        JLabel lblPassword = new JLabel("Mật khẩu:");
        formPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        txtPassword = new JPasswordField(18);
        formPanel.add(txtPassword, gbc);

        // ------------------------------
        // Hiển thị mật khẩu
        // ------------------------------
        gbc.gridx = 1;
        gbc.gridy = 2;

        chkShowPassword =
                new JCheckBox("Hiển thị mật khẩu");

        formPanel.add(chkShowPassword, gbc);

        // ------------------------------
        // Vai trò
        // ------------------------------
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;

        JLabel lblRole = new JLabel("Vai trò:");
        formPanel.add(lblRole, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        String[] roles = {
                "Admin",
                "User"
        };

        cbRole = new JComboBox<>(roles);

        formPanel.add(cbRole, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // ==============================
        // NÚT ĐĂNG NHẬP
        // ==============================
        JPanel buttonPanel = new JPanel();

        btnLogin = new JButton("Đăng nhập");

        btnLogin.setPreferredSize(
                new Dimension(130, 40)
        );

        btnLogin.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        buttonPanel.add(btnLogin);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ==============================
        // SỰ KIỆN HIỂN THỊ MẬT KHẨU
        // ==============================
        char defaultEchoChar =
                txtPassword.getEchoChar();

        chkShowPassword.addActionListener(e -> {

            if (chkShowPassword.isSelected()) {

                // Hiện mật khẩu
                txtPassword.setEchoChar((char) 0);

            } else {

                // Ẩn mật khẩu
                txtPassword.setEchoChar(
                        defaultEchoChar
                );
            }
        });

        // ==============================
        // SỰ KIỆN ĐĂNG NHẬP
        // ==============================
        btnLogin.addActionListener(e -> dangNhap());

        // Nhấn Enter cũng đăng nhập
        getRootPane().setDefaultButton(btnLogin);
    }

    // ==================================
    // HÀM XỬ LÝ ĐĂNG NHẬP
    // ==================================
    private void dangNhap() {

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        String role =
                cbRole.getSelectedItem().toString();

        // Kiểm tra để trống
        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập tài khoản!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            txtUsername.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập mật khẩu!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.requestFocus();

            return;
        }

        // ==================================
        // KIỂM TRA ADMIN
        // ==================================
        if (username.equals("admin")
                && password.equals("123456")
                && role.equals("Admin")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thành công!\n"
                            + "Chào mừng Admin.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }

        // ==================================
        // KIỂM TRA USER
        // ==================================
        else if (username.equals("user")
                && password.equals("123456")
                && role.equals("User")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thành công!\n"
                            + "Chào mừng User.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }

        // ==================================
        // KIỂM TRA SAI VAI TRÒ
        // ==================================
        else if (username.equals("admin")
                && password.equals("123456")
                && !role.equals("Admin")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Tài khoản admin phải chọn vai trò Admin!",
                    "Sai vai trò",
                    JOptionPane.ERROR_MESSAGE
            );

        } else if (username.equals("user")
                && password.equals("123456")
                && !role.equals("User")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Tài khoản user phải chọn vai trò User!",
                    "Sai vai trò",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        // ==================================
        // SAI TÀI KHOẢN HOẶC MẬT KHẨU
        // ==================================
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Tài khoản hoặc mật khẩu không chính xác!",
                    "Đăng nhập thất bại",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    // ==================================
    // MAIN
    // ==================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            bai6 form = new bai6();

            form.setVisible(true);
        });
    }
}