package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.ProductoDAO;

public class VentasDeshboard extends JFrame{
    JFrame frame = new JFrame("Salida de Productos");
    frame.setSize(300, 200);
    
    JLabel labelProducto = new JLabel("Seleccione el producto:");
    JComboBox<Producto> comboProductos = new JComboBox<>(model.getProductosActivos().toArray(new Producto[0]));
    
    JLabel labelCantidad = new JLabel("Cantidad a restar:");
    JTextField txtCantidad = new JTextField(10);
    
    JButton btnRestar = new JButton("Restar Inventario");
    btnRestar.addActionListener(e -> {
        Producto productoSeleccionado = (Producto) comboProductos.getSelectedItem();
        int cantidadARestar = Integer.parseInt(txtCantidad.getText());
        restarInventario(productoSeleccionado, cantidadARestar);
    });
    
    // Organizar el layout
    frame.setLayout(new FlowLayout());
    frame.add(labelProducto);
    frame.add(comboProductos);
    frame.add(labelCantidad);
    frame.add(txtCantidad);
    frame.add(btnRestar);
    
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
}
