package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        // Inicializar la conexión con la base de datos
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método de autenticación para verificar usuario y rol
    public String login(String nombreUsuario, String contrasena) {
        String rol = null;
        String sql = "SELECT rol FROM usuarios WHERE nombreUsuario = ? AND contrasena = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rol = rs.getString("rol"); // Obtiene el rol si el usuario existe
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rol; // Devuelve el rol o null si no se encontró
    }

    public void addDummyUsers() {
        String sql = "INSERT INTO usuarios (nombreUsuario, contrasena, rol) VALUES (?, ?, ?)";
        try (Connection connect = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql)) {
            
            // Datos de ejemplo
            String[][] users = {
                {"admin", "admin123", "Administrador"},
                {"empleado1", "empleado1", "Empleado"},
                {"empleado2", "empleado2", "Empleado"},
                {"empleado3", "empleado3", "Empleado"},
                {"empleado4", "empleado4", "Empleado"},
                {"empleado5", "empleado5", "Empleado"},
                {"empleado6", "empleado6", "Empleado"},
                {"empleado7", "empleado7", "Empleado"},
                {"empleado8", "empleado8", "Empleado"},
                {"empleado9", "empleado9", "Empleado"},
            };
    
            for (String[] user : users) {
                stmt.setString(1, user[0]);
                stmt.setString(2, user[1]);
                stmt.setString(3, user[2]);
                stmt.executeUpdate();
            }
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
