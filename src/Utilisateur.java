import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {

    // Attributes
    private int idUser;
    private String email;
    private String nom;
    private String prenom;
    private int telephone;
    private String password;

    // ResultSet constructor
    public Utilisateur(ResultSet rs) throws SQLException {
        this.idUser = rs.getInt("id_user");
        this.email = rs.getString("email");
        this.nom = rs.getString("nom");
        this.prenom = rs.getString("prenom");
        this.telephone = rs.getInt("telephone");
        this.password = rs.getString("password");
    }

    // Getters & setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
