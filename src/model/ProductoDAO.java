package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
public class ProductoDAO {
    private PreparedStatement pst;
    public boolean addProducto(Producto producto){
        boolean state= false; 
        Connection connect = null;


        try {

            connect = ConnectionPool.getInstance().getConnection();
            
            if(connect != null){
                String sql ="INSERT INTO producto (nombre, precio, cantidad) VALUES (?, ?, ?)";
                pst = connect.prepareStatement(sql);
                pst.setString(1, producto.getnombre());
                pst.setDouble(2, producto.getprecio());
                pst.setInt(3, producto.getCantidad()); // Establece la cantidad en 1
                
                int res = pst.executeUpdate();

                state = res > 0;

            }else{
                System.out.println("Error en la conexión");
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
    }finally{
        try{
            ConnectionPool.getInstance().closeConnection(connect);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    return state;
    }

    public boolean deleteProduct(int id){

        boolean state = false;
        Connection connect = null;

        try{

            connect = ConnectionPool.getInstance().getConnection();
            if(connect != null){
                //String sql = "DELETE FROM persona WHERE id=?";
                String sql = "UPDATE producto SET estatus=? WHERE idProducto=?";

                pst = connect.prepareStatement(sql);
                pst.setInt(1, 0);
                pst.setInt(2, id);

                int res = pst.executeUpdate();

                state = res > 0;

            }else{
                System.out.println("Conexión Fallida");
            }


        }catch(Exception ex){

            System.out.println(ex.getMessage());
        
        }finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }


    public boolean updateProducto(Producto producto) {
        boolean result = false;
        String sql = "UPDATE producto SET nombre = ?, precio = ? WHERE idProducto = ?";

        try (Connection connect = ConnectionPool.getInstance().getConnection();
                PreparedStatement pst = connect.prepareStatement(sql)) {

            pst.setString(1, producto.getnombre());
            pst.setDouble(2, producto.getprecio());
            pst.setInt(3, producto.getidProducto());

            result = pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT idProducto, nombre, precio, cantidad FROM producto";

        try (Connection connect = ConnectionPool.getInstance().getConnection();
                PreparedStatement pst = connect.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setidProducto(rs.getInt("idProducto"));
                producto.setnombre(rs.getString("nombre"));
                producto.setprecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }



    public List<Producto> getProductosActivos() {
        List<Producto> productosActivos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE estatus = 1";
        
        try (Connection connect = ConnectionPool.getInstance().getConnection();
                PreparedStatement pst = connect.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setidProducto(rs.getInt("idProducto"));
                producto.setnombre(rs.getString("nombre"));
                producto.setprecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad")); // Asegúrate de tener un método para esto
                productosActivos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productosActivos;
    }

    
    private void restarInventario(Producto producto, int cantidad) {
    if (producto.getCantidad() >= cantidad) {
        // Actualizar la cantidad en la base de datos
        String sql = "UPDATE producto SET cantidad = cantidad - ? WHERE idProducto = ?";
        
        try (Connection connect = ConnectionPool.getInstance().getConnection();
            PreparedStatement pst = connect.prepareStatement(sql)) {
            
            pst.setInt(1, cantidad);
            pst.setInt(2, producto.getidProducto());
            int res = pst.executeUpdate();
            
            if (res > 0) {
                System.out.println("Salida realizada correctamente ");
                // JOptionPane.showMessageDialog(this, "Salida de producto realizada correctamente.");
            } else {
                System.out.println("Error al actualizar el inventario");
                // JOptionPane.showMessageDialog(this, "Error al actualizar el inventario.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error en la operacion");
            // JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
    } else {
        System.out.println("No se puede restar mas de la cantidad disponile en el inventario ");

        // JOptionPane.showMessageDialog(this, "No se puede restar más de la cantidad disponible en inventario.");
    }
}

}

