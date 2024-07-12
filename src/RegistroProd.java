import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import static java.lang.Integer.parseInt;
import static jdk.internal.math.FloatingDecimal.parseDouble;
import static jdk.internal.math.FloatingDecimal.parseFloat;

public class RegistroProd {
    public JPanel registroPanel;
    private JTextField codigoTex;
    private JTextField nombreText;
    private JTextField descrText;
    private JTextField precioText;
    private JTextField cantidadText;
    private JTextField categoriaText;
    private JButton resgitrarProductoButton;
    private JButton buscarProductoButton;

    String url = "jdbc:mysql://localhost:3306/productos_cp";
    String user = "root";
    String password = "123456";

    public RegistroProd() {
        resgitrarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectar = DriverManager.getConnection(url, user, password)){
                    System.out.println("Conectado a la base de datos");
                    Productos productos = new Productos(codigoTex.getText(),nombreText.getText(),descrText.getText(),parseFloat(precioText.getText()),parseInt(cantidadText.getText()),categoriaText.getText());
                    String query = "INSERT INTO PRODUCTO (codigo_producto, nombre, descripcion, precio, cantidad, categoria) values (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conectar.prepareStatement(query);
                    preparedStatement.setString(1, productos.getCodigo());
                    preparedStatement.setString(2, productos.getNombre());
                    preparedStatement.setString(3, productos.getDescripcion());
                    preparedStatement.setFloat(4,  productos.getPrecio());
                    preparedStatement.setInt(4, productos.getCantidad());
                    preparedStatement.setString(5, productos.getCategoria());
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "Producto creada con exito");
                }
                catch (SQLException e1){
                    System.out.println(e1);
                }
            }
        });
        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Buscar Producto");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new BusquedaProd().buscarPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(600, 400);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarProductoButton)).dispose();
            }
        });
    }
}
