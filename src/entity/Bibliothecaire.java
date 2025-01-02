package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bibliothecaire {

  private int id;
  private String nom;
  private String prenom;
  private Date dateNaissance;
  private String telephone;
  private String email;
  private String motDePasse;
  private List<Emprunt> empruntList;

  public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  // Getters et Setters...

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Date getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMotDePasse() {
    return motDePasse;
  }

  public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
  }

  public List<Emprunt> getEmpruntList() {
    return empruntList;
  }

  public void setEmpruntList(List<Emprunt> empruntList) {
    this.empruntList = empruntList;
  }

  // Convertit l'objet en une chaîne CSV délimitée par ";"
  public String toCSVString() {
    return String.format("%d;%s;%s;%s;%s;%s;%s",
      id,
      nom,
      prenom,
      dateFormat.format(dateNaissance),
      telephone,
      email,
      motDePasse);
  }

  // Crée un objet Bibliothecaire à partir d'une chaîne CSV délimitée par ";"
  public static Bibliothecaire fromCSVString(String csv) throws ParseException {
    String[] parts = csv.split(";");
    if (parts.length < 7) throw new IllegalArgumentException("Ligne CSV mal formée");
    Bibliothecaire b = new Bibliothecaire();
    b.setId(Integer.parseInt(parts[0]));
    b.setNom(parts[1]);
    b.setPrenom(parts[2]);
    b.setDateNaissance(dateFormat.parse(parts[3]));
    b.setTelephone(parts[4]);
    b.setEmail(parts[5]);
    b.setMotDePasse(parts[6]);
    return b;
  }
}
