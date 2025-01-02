package service;

import entity.Emprunt;

import java.util.List;

public interface EmpruntService {
  public List<Emprunt> getAllEmprunt();
  public Emprunt getEmpruntById(int id);
  public boolean addEmprunt(Emprunt emprunt);
  public boolean modifyEmprunt(Emprunt emprunt);
  public boolean deleteEmprunt(int id);
}
