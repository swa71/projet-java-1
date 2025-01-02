package controller;



import Enumeration.Genre;


import entity.Livre;
import entity.Emprunt;
import service.LivreService;
import service.LivreServiceImp;

import java.util.List;
import java.util.Date;

public class LivreController {

    private LivreService livreService;

    public LivreController() {
        // Initialize the service layer
        this.livreService = new LivreServiceImp();
    }

    // Method to get all livres
    public List<Livre> getAllLivres() {
        return livreService.getAllLivre();
    }

    // Method to get a livre by ID
    public Livre getLivreById(int id) {
        return livreService.getLivreById(id);
    }

    // Method to add a new livre
    public boolean addLivre(int id, String titre, String auteur, Date annee, boolean estDisponible, Genre genre, int nbrEmprunt, Emprunt emprunt) {
        Livre livre = new Livre(id, titre, auteur, annee, estDisponible, genre, nbrEmprunt, emprunt);
        return livreService.addLivre(livre);
    }

    // Method to update an existing livre
    public boolean updateLivre(int id, String titre, String auteur, Date annee, boolean estDisponible, Genre genre, int nbrEmprunt, Emprunt emprunt) {
        Livre livre = new Livre(id, titre, auteur, annee, estDisponible, genre, nbrEmprunt, emprunt);
        return livreService.updateLivre(livre);
    }

    // Method to delete a livre by ID
    public boolean deleteLivre(int id) {
        return livreService.deleteLivre(id);
    }

    // Helper method to display all livres (for debugging or testing)
    public void printAllLivres() {
        List<Livre> livres = getAllLivres();
        for (Livre l : livres) {
            System.out.println("ID: " + l.getId() + ", Titre: " + l.getTitre() + ", Auteur: " + l.getAuteur());
        }
    }
}
