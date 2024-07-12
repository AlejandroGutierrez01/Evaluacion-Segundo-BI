import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton enviarButton;
    public JPanel loginPanel;

    String url = "jdbc:mysql://localhost:3306/productos_cp";
    String user = "root";
    String password = "123456";

    public Login() {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection conectar = DriverManager.getConnection(url, user, password);){
                    System.out.println("Conectado a la base de datos");

                    String query = "select username, password from usuario";
                    Statement statement = conectar.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next())
                        if(resultSet.getString("username").equals(userText.getText()) && resultSet.getString("password").equals(passwordText.getText())) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Registrar Productos");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setContentPane(new RegistroProd().registroPanel);
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setSize(600, 400);
                            frame.setVisible(true);
                            ((JFrame) SwingUtilities.getWindowAncestor(enviarButton)).dispose();
                    }
                }catch (SQLException e1){
                    System.out.println(e1);
                }
            }
        });
    }
}
