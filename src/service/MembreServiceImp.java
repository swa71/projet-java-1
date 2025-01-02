package service;

import entity.Membre;

import java.util.List;

public class MembreServiceImp implements MembreService {
  @Override
  public List<Membre> getAllMembre() {
    return List.of();
  }

  @Override
  public Membre getMembreById(int id) {
    return null;
  }

  @Override
  public boolean addMembre(Membre membre) {
    return false;
  }

  @Override
  public boolean updateMembre(Membre membre) {
    return false;
  }

  @Override
  public boolean deleteMembre(int id) {
    return false;
  }
}
