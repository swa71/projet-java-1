package entity;

import Enumeration.Genre;

import java.util.Date;

public class Livre {

  private int id;
  private String titre;
  private String auteur;
  private Date annee;
  private boolean estDisponible;
  private Genre genre;
  private int nbrEmprunt;
  private Emprunt emprunt;

  public Livre(int id, String titre, String auteur, Date annee, boolean estDisponible, Genre genre, int nbrEmprunt, Emprunt emprunt) {
    this.id = id;
    this.titre = titre;
    this.auteur = auteur;
    this.annee = annee;
    this.estDisponible = estDisponible;
    this.genre = genre;
    this.nbrEmprunt = nbrEmprunt;
    this.emprunt = emprunt;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuteur() {
    return auteur;
  }

  public void setAuteur(String auteur) {
    this.auteur = auteur;
  }

  public Date getAnnee() {
    return annee;
  }

  public void setAnnee(Date annee) {
    this.annee = annee;
  }

  public boolean isEstDisponible() {
    return estDisponible;
  }

  public void setEstDisponible(boolean estDisponible) {
    this.estDisponible = estDisponible;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public int getNbrEmprunt() {
    return nbrEmprunt;
  }

  public void setNbrEmprunt(int nbrEmprunt) {
    this.nbrEmprunt = nbrEmprunt;
  }

  public Emprunt getEmprunt() {
    return emprunt;
  }

  public void setEmprunt(Emprunt emprunt) {
    this.emprunt = emprunt;
  }
}
