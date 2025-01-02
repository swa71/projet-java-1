package entity;

import java.util.Date;
import java.util.List;

/**
 * Représente un emprunt dans une bibliothèque.
 * Contient des informations sur la date d'emprunt, la date de retour prévue et effective,
 * ainsi que sur le bibliothécaire, le membre et la liste des livres concernés.
 *
 * <p>Créé par : Rida MANFALOUTI, Akram EL MASFIOUI, Jalal MALOUK, Ohman AIT TALAB</p>
 */
public class Emprunt {

  /**
   * Identifiant unique de l'emprunt.
   */
  private int id;

  /**
   * Date à laquelle l'emprunt a été effectué.
   */
  private Date dateEmprunt;

  /**
   * Date prévue pour le retour des livres empruntés.
   */
  private Date dateRetourPrevue;

  /**
   * Date effective à laquelle les livres ont été retournés.
   */
  private Date dateRetourEffective;

  /**
   * Bibliothécaire responsable de l'emprunt.
   */
  private Bibliothecaire bibliothecaire;

  /**
   * Membre ayant effectué l'emprunt.
   */
  private Membre membre;

  /**
   * Liste des livres empruntés.
   */
  private List<Livre> livreList;

  /**
   * Obtient l'identifiant unique de l'emprunt.
   *
   * @return l'identifiant de l'emprunt.
   */
  public int getId() {
    return id;
  }

  /**
   * Définit l'identifiant unique de l'emprunt.
   *
   * @param id l'identifiant de l'emprunt.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Obtient la date de l'emprunt.
   *
   * @return la date de l'emprunt.
   */
  public Date getDateEmprunt() {
    return dateEmprunt;
  }

  /**
   * Définit la date de l'emprunt.
   *
   * @param dateEmprunt la date de l'emprunt.
   */
  public void setDateEmprunt(Date dateEmprunt) {
    this.dateEmprunt = dateEmprunt;
  }

  /**
   * Obtient la date prévue pour le retour des livres empruntés.
   *
   * @return la date de retour prévue.
   */
  public Date getDateRetourPrevue() {
    return dateRetourPrevue;
  }

  /**
   * Définit la date prévue pour le retour des livres empruntés.
   *
   * @param dateRetourPrevue la date de retour prévue.
   */
  public void setDateRetourPrevue(Date dateRetourPrevue) {
    this.dateRetourPrevue = dateRetourPrevue;
  }

  /**
   * Obtient la date effective de retour des livres empruntés.
   *
   * @return la date de retour effective.
   */
  public Date getDateRetourEffective() {
    return dateRetourEffective;
  }

  /**
   * Définit la date effective de retour des livres empruntés.
   *
   * @param dateRetourEffective la date de retour effective.
   */
  public void setDateRetourEffective(Date dateRetourEffective) {
    this.dateRetourEffective = dateRetourEffective;
  }

  /**
   * Obtient le bibliothécaire responsable de l'emprunt.
   *
   * @return le bibliothécaire.
   */
  public Bibliothecaire getBibliothecaire() {
    return bibliothecaire;
  }

  /**
   * Définit le bibliothécaire responsable de l'emprunt.
   *
   * @param bibliothecaire le bibliothécaire.
   */
  public void setBibliothecaire(Bibliothecaire bibliothecaire) {
    this.bibliothecaire = bibliothecaire;
  }

  /**
   * Obtient le membre ayant effectué l'emprunt.
   *
   * @return le membre.
   */
  public Membre getMembre() {
    return membre;
  }

  /**
   * Définit le membre ayant effectué l'emprunt.
   *
   * @param membre le membre.
   */
  public void setMembre(Membre membre) {
    this.membre = membre;
  }

  /**
   * Obtient la liste des livres empruntés.
   *
   * @return la liste des livres.
   */
  public List<Livre> getLivreList() {
    return livreList;
  }

  /**
   * Définit la liste des livres empruntés.
   *
   * @param livreList la liste des livres.
   */
  public void setLivreList(List<Livre> livreList) {
    this.livreList = livreList;
  }
}
