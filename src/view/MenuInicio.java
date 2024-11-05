package view;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.InventarioDeshboard;
import model.Producto;
import model.ProductoDAO;

import javax.swing.JFrame;

public class MenuInicio extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductoDAO model;

    public MenuInicio() {
        setTitle("Menu de inicio");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar el modelo de datos
        model = new ProductoDAO();

        // Crear panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);


        // Panel para los botones
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnInventario = new JButton("Inventario");
        JButton btnVentas = new JButton("Ventas");
        JButton btnHistorial = new JButton("Historial");

        panelButtons.add(btnInventario);
        panelButtons.add(btnVentas);
        panelButtons.add(btnHistorial);

        mainPanel.add(panelButtons, BorderLayout.CENTER);

        // Crear tabla para mostrar productos
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio"}, 0);
        table = new JTable(tableModel);
        // JScrollPane scrollPane = new JScrollPane(table);
        // scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        // mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Acción del botón "Agregar Producto"
        btnInventario.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                new InventarioDeshboard().setVisible(true);
            }
        });

        // Acción del botón "Actualizar Producto"
        btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentasDeshboard().setVisible(true);
                // actualizarProducto();
            }
        });

        // Acción del botón "Ver Productos"
        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistorialDeshboard().setVisible(true);
            }
        });
    }

    private void agregarProducto() {
        
    }

    private void actualizarProducto() {
        
    }

    private void verProductos() {
    
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
