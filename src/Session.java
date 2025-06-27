import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private static Utilisateur currentUser;

    /*
     * Check if user credentials are valid
     * 
     * Based on email and password
     */
    public static void login(String email, String password) {

        try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
            String sql = "SELECT * FROM Utilisateur WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password); // en prod, utiliser un hash sécurisé
                try (ResultSet rs = stmt.executeQuery()) {

                    Utilisateur loggedUser = new Utilisateur(rs);
                    currentUser = loggedUser;

                    System.out.println("Utilisateur connecté !");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Log out user
     */
    public static void logout() {

        currentUser = null; // add end process or user login form
        System.out.println("Utilisateur déconnecté !");
    }

    /*
     * Check if user is connected
     */
    public static boolean isConnected() {
        boolean connectionStatus = currentUser != null;
        System.out.println("Connecté ? " + connectionStatus);
        return connectionStatus;
    }

    /*
     * Check if user is connected
     */
    public static Utilisateur getCurrentUser() {
        if (currentUser != null) {
            System.out.println("Utilisateur actuel: " + currentUser.getEmail());
        } else {
            System.out.println("Pas d'utilisateur connecté");
        }
        return currentUser;
    }
}
