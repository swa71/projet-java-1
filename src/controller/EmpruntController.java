package controller;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class EmpruntController {

    private static final String CSV_FILE = "Emprunt.csv";

    // Méthode pour ajouter un emprunt
    public void ajouterEmprunt(DefaultTableModel model, String id, String dateEmprunt, String dateRetour, String membre) {
        String[] empruntData = {id, dateEmprunt, dateRetour, membre};
        model.addRow(empruntData);
        sauvegarderEmprunts(model);
    }

    // Méthode pour modifier un emprunt
    public void modifierEmprunt(DefaultTableModel model, int selectedRow, String id, String dateEmprunt, String dateRetour, String membre) {
        if (selectedRow != -1) {
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(dateEmprunt, selectedRow, 1);
            model.setValueAt(dateRetour, selectedRow, 2);
            model.setValueAt(membre, selectedRow, 3);
            sauvegarderEmprunts(model);
        }
    }

    // Méthode pour supprimer un emprunt
    public void supprimerEmprunt(DefaultTableModel model, int selectedRow) {
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            sauvegarderEmprunts(model);
        }
    }

    // Méthode pour charger les emprunts depuis le fichier CSV
    public void chargerEmprunts(DefaultTableModel model) {
        model.setRowCount(0); // Réinitialiser le modèle
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour sauvegarder les emprunts dans le fichier CSV
    private void sauvegarderEmprunts(DefaultTableModel model) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    row.add(model.getValueAt(i, j).toString());
                }
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

