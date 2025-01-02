package service;

import entity.Membre;

import java.util.List;

public interface MembreService {

  public List<Membre> getAllMembre();
  public Membre getMembreById(int id);
  public boolean addMembre(Membre membre);
  public boolean updateMembre(Membre membre);
  public boolean deleteMembre(int id);
}
