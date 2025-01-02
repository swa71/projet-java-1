package service;

import entity.Emprunt;

import java.util.List;

public class EmpruntServiceImp implements EmpruntService {
  @Override
  public List<Emprunt> getAllEmprunt() {
    return List.of();
  }

  @Override
  public Emprunt getEmpruntById(int id) {
    return null;
  }

  @Override
  public boolean addEmprunt(Emprunt emprunt) {
    return false;
  }

  @Override
  public boolean modifyEmprunt(Emprunt emprunt) {
    return false;
  }

  @Override
  public boolean deleteEmprunt(int id) {
    return false;
  }
}
