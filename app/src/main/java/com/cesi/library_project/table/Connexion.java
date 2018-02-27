package library_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Connexion {
    
    private static String url = "jdbc:sqlite:/Users/dubs/NetBeansProjects/Library_Project_Java/src/library_project/ProjetJava.db";
    private static Connection connect = null;

    public static Connection Connect() throws ClassNotFoundException, SQLException {

        connect = DriverManager.getConnection(url);
        System.out.println("La connexion à " + url + " a été réalisée avec succès");
        JOptionPane.showMessageDialog(null, "La connexion à la base de données a été réalisée avec succès");
        return connect;
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}