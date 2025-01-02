package controller;

import entity.Bibliothecaire;
import service.BibliothecaireService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;

public class BibliothecaireController {

    private BibliothecaireService bibliothecaireService;
    private DefaultTableModel tableModel;

    public BibliothecaireController(BibliothecaireService bibliothecaireService, DefaultTableModel tableModel) {
        this.bibliothecaireService = bibliothecaireService;
        this.tableModel = tableModel;
        loadBibliothecairesToTable();
    }

    public BibliothecaireController() {

    }

    // Charger les données dans le tableau de la vue
    public void loadBibliothecairesToTable() {
        List<Bibliothecaire> bibliothecaires = bibliothecaireService.getAllBibliothecaire();
        tableModel.setRowCount(0); // Réinitialise le tableau
        for (Bibliothecaire b : bibliothecaires) {
            tableModel.addRow(new Object[]{
                    b.getId(),
                    b.getNom(),
                    b.getPrenom(),
                    b.getDateNaissance(),
                    b.getTelephone(),
                    b.getEmail()
            });
        }
    }

    // Ajouter un nouveau bibliothécaire
    public void addBibliothecaire(String nom, String prenom, String dateNaissance, String telephone, String email, String motDePasse) {
        try {
            Bibliothecaire b = new Bibliothecaire();
            b.setNom(nom);
            b.setPrenom(prenom);
            b.setDateNaissance(Bibliothecaire.dateFormat.parse(dateNaissance));
            b.setTelephone(telephone);
            b.setEmail(email);
            b.setMotDePasse(motDePasse);

            if (bibliothecaireService.addBibliothecaire(b)) {
                JOptionPane.showMessageDialog(null, "Bibliothécaire ajouté avec succès !");
                loadBibliothecairesToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Échec de l'ajout du bibliothécaire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Modifier un bibliothécaire existant
    public void updateBibliothecaire(int id, String nom, String prenom, String dateNaissance, String telephone, String email, String motDePasse) {
        try {
            Bibliothecaire b = bibliothecaireService.getBibliothecaireById(id);
            if (b == null) {
                JOptionPane.showMessageDialog(null, "Bibliothécaire introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            b.setNom(nom);
            b.setPrenom(prenom);
            b.setDateNaissance(Bibliothecaire.dateFormat.parse(dateNaissance));
            b.setTelephone(telephone);
            b.setEmail(email);
            b.setMotDePasse(motDePasse);

            if (bibliothecaireService.updateBibliothecaire(b)) {
                JOptionPane.showMessageDialog(null, "Bibliothécaire mis à jour avec succès !");
                loadBibliothecairesToTable();
            } else {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour du bibliothécaire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Supprimer un bibliothécaire
    public void deleteBibliothecaire(int id) {
        if (bibliothecaireService.deleteBibliothecaire(id)) {
            JOptionPane.showMessageDialog(null, "Bibliothécaire supprimé avec succès !");
            loadBibliothecairesToTable();
        } else {
            JOptionPane.showMessageDialog(null, "Échec de la suppression du bibliothécaire.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
