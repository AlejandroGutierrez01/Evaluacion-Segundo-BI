import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BusquedaProd {
    public JPanel buscarPanel;
    private JTextField codigoText;
    private JButton buscarButton;
    private JTextArea resultado;

    String url = "jdbc:mysql://localhost:3306/productos_cp";
    String user = "root";
    String password = "123456";

    public BusquedaProd() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection= DriverManager.getConnection(url,user,password)){
                    System.out.println("Conectado a la base de datos");
                    String query="select * from producto where codigo_producto= '"+ codigoText.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);
                    while(resultSet.next()){
                        resultado.setText(" Codigo: "+ resultSet.getString("codigo_producto")+"\n Nombre: "+ resultSet.getString("nombre")+"\n Descripcion: "+resultSet.getString("descripcion")+"\n Precio: " + resultSet.getString("precio")+"\n Cantidad : " + resultSet.getString("cantidad")+"\n Categoria: " + resultSet.getString("categoria"));
                    }
                }catch (SQLException e1) {
                    System.out.println(e1);
                }
            }
        });
    }
}
