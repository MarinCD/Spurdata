package DATA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connectionBDD() throws Exception { // Ajout de l'exception déclarée ici
        String url = "jdbc:mysql://localhost/spursdata2";
        String username = "myroot";
        String password = "root123*";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connexion réussie !");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de la connexion à la BDD.");
            e.printStackTrace();
            throw e; // Relance l'exception pour que le code appelant puisse la gérer
        }

        return connection;
    }
}
