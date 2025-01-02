package service;

import entity.Livre;

import java.util.List;

public interface LivreService {

  public List<Livre> getAllLivre();
  public Livre getLivreById(int id);
  public boolean addLivre(Livre livre);
  public boolean updateLivre(Livre livre);
  public boolean deleteLivre(int id);
}
