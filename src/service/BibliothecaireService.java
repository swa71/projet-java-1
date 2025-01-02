package service;

import entity.Bibliothecaire;

import java.util.List;

public interface BibliothecaireService {

  public List<Bibliothecaire> getAllBibliothecaire();
  public Bibliothecaire getBibliothecaireById(int id);
  public boolean addBibliothecaire(Bibliothecaire b);
  public boolean updateBibliothecaire(Bibliothecaire b);
  public boolean deleteBibliothecaire(int id);
}
