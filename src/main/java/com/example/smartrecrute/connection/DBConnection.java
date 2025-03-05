package com.example.smartrecrute.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jobconnect";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        createTables();
        return connection;
    }

    public static void createTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS utilisateurs (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nom_utilisateur VARCHAR(100) NOT NULL UNIQUE," +
                "mot_de_passe VARCHAR(255) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "role ENUM('candidat', 'recruteur', 'admin') NOT NULL," +
                "cv VARCHAR(100)  NULL ," +
                "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";




        String createJobsTable = "CREATE TABLE IF NOT EXISTS offres_emploi (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "titre VARCHAR(255) NOT NULL," +
                "description TEXT NOT NULL," +
                "date_publication TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "entreprise VARCHAR(100) NOT NULL," +
                "recruteur_id INT NOT NULL," +
                "FOREIGN KEY (recruteur_id) REFERENCES utilisateurs(id)" +
                ")";

        String createApplicationsTable = "CREATE TABLE IF NOT EXISTS candidatures (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "candidat_id INT NOT NULL," +
                "statut VARCHAR(50) DEFAULT 'En attente'," +
                "date_candidature TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (candidat_id) REFERENCES utilisateurs(id)" +
                ")";

        String createJobApplicationsTable = "CREATE TABLE IF NOT EXISTS offres_emploi_candidatures (" +
                "offre_id INT NOT NULL," +
                "candidature_id INT NOT NULL," +
                "PRIMARY KEY (offre_id, candidature_id)," +
                "FOREIGN KEY (offre_id) REFERENCES offres_emploi(id)," +
                "FOREIGN KEY (candidature_id) REFERENCES candidatures(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")";


        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createUsersTable);
            stmt.executeUpdate(createJobsTable);
            stmt.executeUpdate(createApplicationsTable);
            stmt.executeUpdate(createJobApplicationsTable);
            System.out.println("Tables crees dans db");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
