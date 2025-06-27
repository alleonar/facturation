import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseFixtures {

    private static final String URL = "jdbc:sqlite:./db/facturation.db";

    public static void loadFixtures() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false); // pour insérer tout ou rien

            insertEntreprise(conn, 1, "OpenAI France", "123 rue de l'IA", 75000, "Paris", "contact@openai.fr",
                    123456789);
            insertUtilisateur(conn, 1, "jean.dupont@example.com", "Dupont", "Jean", 1122334455, "hashed_password");
            insertConsultation(conn, 1, LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 20), new BigDecimal("550.50"),
                    "Mission IA - Audit", 1, 1);
            insertFormation(conn, 1, LocalDate.of(2025, 2, 15), LocalTime.of(9, 0), LocalTime.of(12, 0),
                    "BTS SIO", "Programmation Java", 1, 1);

            conn.commit();
            System.out.println("Fixtures insérées avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEntreprise(Connection conn, int id, String nom, String adresse, int cp, String ville,
            String mail, int telephone) throws SQLException {
        String sql = "INSERT INTO Entreprise (id_entreprise, nom, adresse, cp, ville, mail, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nom);
            stmt.setString(3, adresse);
            stmt.setInt(4, cp);
            stmt.setString(5, ville);
            stmt.setString(6, mail);
            stmt.setInt(7, telephone);
            stmt.executeUpdate();
        }
    }

    private static void insertUtilisateur(Connection conn, int id, String email, String nom, String prenom,
            int telephone, String password) throws SQLException {
        String sql = "INSERT INTO Utilisateur (id_user, email, nom, prenom, telephone, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, email);
            stmt.setString(3, nom);
            stmt.setString(4, prenom);
            stmt.setInt(5, telephone);
            stmt.setString(6, password);
            stmt.executeUpdate();
        }
    }

    private static void insertConsultation(Connection conn, int id, LocalDate debut, LocalDate fin, BigDecimal tjm,
            String description, int idUser, int idEntreprise) throws SQLException {
        String sql = "INSERT INTO Consultation (id_consultation, debut, fin, TJM, description, id_user, id_entreprise) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setDate(2, Date.valueOf(debut));
            stmt.setDate(3, Date.valueOf(fin));
            stmt.setBigDecimal(4, tjm);
            stmt.setString(5, description);
            stmt.setInt(6, idUser);
            stmt.setInt(7, idEntreprise);
            stmt.executeUpdate();
        }
    }

    private static void insertFormation(Connection conn, int id, LocalDate jour, LocalTime debut, LocalTime fin,
            String classe, String cours, int idUser, int idEntreprise) throws SQLException {
        String sql = "INSERT INTO Formation (id_formation, jour, debut, fin, classe, cours, id_user, id_entreprise) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setDate(2, Date.valueOf(jour));
            stmt.setTime(3, Time.valueOf(debut));
            stmt.setTime(4, Time.valueOf(fin));
            stmt.setString(5, classe);
            stmt.setString(6, cours);
            stmt.setInt(7, idUser);
            stmt.setInt(8, idEntreprise);
            stmt.executeUpdate();
        }
    }
}
