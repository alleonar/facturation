import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void connect() {
        // connection string
        var url = "jdbc:sqlite:./db/facturation.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Connection established");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void initDatabase() {

        String url = "jdbc:sqlite:./db/facturation.db";

        try (Connection conn = DriverManager.getConnection(url)) {

            // Lire tout le fichier SQL dans une seule chaîne
            String sql = Files.readString(Paths.get("./db/schema.sql"));

            // Séparer les requêtes au niveau des point-virgules
            String[] commandes = sql.split(";");

            try (Statement stmt = conn.createStatement()) {
                for (String commande : commandes) {
                    commande = commande.trim();
                    if (!commande.isEmpty()) {
                        stmt.execute(commande);
                        System.out.println("Exécuté : " + commande);
                    }
                }
            }
            System.out.println("Script SQL exécuté avec succès.");

        } catch (IOException e) {
            System.out.println("Erreur lecture fichier SQL : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
        initDatabase();
    }
}