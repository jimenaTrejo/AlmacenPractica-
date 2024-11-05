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

import model.Producto;
import model.ProductoDAO;

import javax.swing.JFrame;

public class InventarioDeshboard extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductoDAO model;

    public InventarioDeshboard() {
        setTitle("Gestión de Productos");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar el modelo de datos
        model = new ProductoDAO();

        // Crear panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel de entrada de datos
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panelInput.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInput.add(txtNombre);

        panelInput.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelInput.add(txtPrecio);

        mainPanel.add(panelInput, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Agregar Producto");
        JButton btnUpdate = new JButton("Actualizar Producto");
        JButton btnView = new JButton("Ver Productos");

        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnView);

        mainPanel.add(panelButtons, BorderLayout.CENTER);

        // Crear tabla para mostrar productos
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Cantidad"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Acción del botón "Agregar Producto"
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        // Acción del botón "Actualizar Producto"
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        // Acción del botón "Ver Productos"
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProductos();
            }
        });
    }

    private void agregarProducto() {
        try {
            // int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());

            Producto producto = new Producto();
            // producto.setidProducto(id);
            producto.setnombre(nombre);
            producto.setprecio(precio);
            producto.setCantidad(0); // Establece la cantidad en 1

            if (model.addProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto guardado correctamente.");
                limpiarCampos();
                verProductos(); // Actualizar la tabla después de agregar
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el producto.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
        }
    }

    private void actualizarProducto() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());

            Producto producto = new Producto();
            producto.setidProducto(id);
            producto.setnombre(nombre);
            producto.setprecio(precio);
            producto.setCantidad(0);

            if (model.updateProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                limpiarCampos();
                verProductos(); // Actualizar la tabla después de actualizar
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa datos válidos.");
        }
    }

    private void verProductos() {
        List<Producto> productos = model.getAllProductos();
        tableModel.setRowCount(0); // Limpiar la tabla

        for (Producto producto : productos) {
            Object[] rowData = {producto.getidProducto(), producto.getnombre(), producto.getprecio(), producto.getCantidad()};
            tableModel.addRow(rowData);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
