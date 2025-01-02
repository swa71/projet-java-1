package service;

import entity.Bibliothecaire;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BibliothecaireServiceImp implements BibliothecaireService {

  private ArrayList<Bibliothecaire> bibliothecaireListe = new ArrayList<>();
  private final String fileName = "Bibliothecaire.csv";
  private int currentId = 0;

  public BibliothecaireServiceImp() {
    loadFromFile();
  }

  @Override
  public List<Bibliothecaire> getAllBibliothecaire() {
    return bibliothecaireListe;
  }

  @Override
  public Bibliothecaire getBibliothecaireById(int id) {
    return bibliothecaireListe.stream()
      .filter(b -> b.getId() == id)
      .findFirst()
      .orElse(null);
  }

  @Override
  public boolean addBibliothecaire(Bibliothecaire b) {
    // Incrémente l'ID et assigne-le au nouveau bibliothécaire
    b.setId(++currentId);
    bibliothecaireListe.add(b);
    saveToFile();
    return true;
  }

  @Override
  public boolean updateBibliothecaire(Bibliothecaire b) {
    for (int i = 0; i < bibliothecaireListe.size(); i++) {
      if (bibliothecaireListe.get(i).getId() == b.getId()) {
        bibliothecaireListe.set(i, b);
        saveToFile();
        return true;
      }
    }
    return false; // Bibliothécaire non trouvé
  }

  @Override
  public boolean deleteBibliothecaire(int id) {
    if (bibliothecaireListe.removeIf(b -> b.getId() == id)) {
      saveToFile();
      return true;
    }
    return false; // Bibliothécaire non trouvé
  }

  private void loadFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        Bibliothecaire b = Bibliothecaire.fromCSVString(line);
        bibliothecaireListe.add(b);
        currentId = Math.max(currentId, b.getId()); // Met à jour le compteur d'ID
      }
    } catch (IOException | ParseException e) {
      System.err.println("Erreur lors du chargement du fichier : " + e.getMessage());
    }
  }

  private void saveToFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
      for (Bibliothecaire b : bibliothecaireListe) {
        bw.write(b.toCSVString() + "\n");
      }
    } catch (IOException e) {
      System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
    }
  }
}
