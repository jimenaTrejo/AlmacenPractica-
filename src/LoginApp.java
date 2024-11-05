import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UsuarioDAO; // Asegúrate de importar la clase correctamente
import view.MenuInicio;

public class LoginApp extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;
    private UsuarioDAO usuarioDAO;

    public LoginApp() {
        // Inicialización de la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear instancia del UsuarioDAO
        usuarioDAO = new UsuarioDAO();

        // Panel de inicio de sesión
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Iniciar Sesión");
        panel.add(btnLogin);

        lblMessage = new JLabel("");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMessage);

        add(panel, BorderLayout.CENTER);

        // Acción del botón de inicio de sesión
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
    }

    private void autenticarUsuario() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Llama al método login de UsuarioDAO
        String role = usuarioDAO.login(username, password);

        if (role != null) {
            if (role.equals("Administrador")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
                openAdminDashboard();
            } else if (role.equals("Empleado")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Empleado");
                openEmployeeDashboard();
            }
        } else {
            lblMessage.setText("Usuario o contraseña incorrectos");
        }
    }

    private void openAdminDashboard() {
        new MenuInicio().setVisible(true);
        // new AdminDeshboard().setVisible(true);

        // JFrame adminFrame = new JFrame("Panel de Administrador");
        // adminFrame.setSize(400, 300);
        // adminFrame.setLocationRelativeTo(null);
        // adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // adminFrame.add(new JLabel("Bienvenido al panel de administrador", SwingConstants.CENTER), BorderLayout.CENTER);
        // adminFrame.setVisible(true);
    }

    private void openEmployeeDashboard() {
        JFrame employeeFrame = new JFrame("Panel de Empleado");
        employeeFrame.setSize(400, 300);
        employeeFrame.setLocationRelativeTo(null);
        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeFrame.add(new JLabel("Bienvenido al panel de empleado", SwingConstants.CENTER), BorderLayout.CENTER);
        employeeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
